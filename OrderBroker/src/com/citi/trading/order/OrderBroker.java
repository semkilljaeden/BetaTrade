package com.citi.trading.order;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.TextMessage;

import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.ClOrdID;
import quickfix.field.LastShares;
import quickfix.field.OrdStatus;

import com.citi.trading.Trade;
import com.citi.trading.jms.PointToPointClient;
import com.citi.trading.mq.TradeMessenger;

public class OrderBroker
    implements MessageListener, AutoCloseable
{
    public static boolean MOCK_QUICK_FIX = false;
    public static int FAIL_ONCE_PER = 5;     // 0 is don't fail, 1 is always
    public static boolean FAIL_AT_RANDOM = true; //TODO // close to ONCE_PER but unpredictable
    public static int PARTIAL_ONCE_PER = 7;  // 0 is don't do partials, 1 is always
    public static boolean PARTIAL_AT_RANDOM = true; //TODO // close to ONCE_PER but unpredictable
    
    private static Map<Character,Trade.Result> responseCodes = new HashMap<> ();
    static
    {
        responseCodes.put (OrdStatus.PARTIALLY_FILLED, Trade.Result.PARTIALLY_FILLED);
        responseCodes.put (OrdStatus.FILLED, Trade.Result.FILLED);
        responseCodes.put (OrdStatus.DONE_FOR_DAY, Trade.Result.DONE_FOR_DAY);
        responseCodes.put (OrdStatus.CANCELED, Trade.Result.CANCELED);
        responseCodes.put (OrdStatus.REJECTED, Trade.Result.REJECTED);
    }
    
    private static Logger LOGGER = 
        Logger.getLogger (OrderBroker.class.getName ());
    private static int requestIDSeq = 0;
    private static int requestCount = 0;
    private static int responseCount = 0;

    private OrderManager orderManager = 
        new OrderManager (new FixResponseHandler ());
    private PointToPointClient replyClient;
    private QueueSender sender;
    
    private Map<String,Trade> outstandingTrades = new HashMap<> ();
    private Map<String,Integer> forcedPartialTrades = new HashMap<> ();
    
    private Timer timer = new Timer ();
    
    public OrderBroker ()
    {
        try
        {
            replyClient = new PointToPointClient (TradeMessenger.RESPONSE_QUEUE);
            sender = replyClient.openToSend ();
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.SEVERE, 
                "Failed to connect to reply queue", ex);
        }
    }
    
    public void close ()
    {
        System.out.println ("Total requests:  " + requestCount);
        System.out.println ("Total responses: " + responseCount);
        
        if (orderManager != null)
            orderManager.close ();
        
        if (replyClient != null)
            replyClient.close ();
    }
    
    public void sendTradeRequest (String requestID, Trade trade)
    {
        outstandingTrades.put (requestID, trade);
        ++requestCount;
        
        if (FAIL_ONCE_PER != 0 && requestCount % FAIL_ONCE_PER == 0)
        {
            sendTradeResponse (requestID, Trade.Result.REJECTED, trade.getSize ());
            return;
        }
        
        if (PARTIAL_ONCE_PER != 0 && requestCount % PARTIAL_ONCE_PER == 0)
            forcedPartialTrades.put (requestID, trade.getSize () * 3 / 5);
        
        if (MOCK_QUICK_FIX)
            sendTradeResponse (requestID, Trade.Result.FILLED, trade.getSize ());
        else
            orderManager.getEngine ().sendNewOrder (requestID, trade.isBuy (),
                trade.getStock (), trade.getPrice (), trade.getSize ());
    }
    
    public void sendTradeResponse 
        (String requestID, Trade.Result result, int shares)
    {
        final SecureRandom GENERATOR = new SecureRandom ();
        
        Trade trade = outstandingTrades.get (requestID);
        if (trade == null)
        {
            LOGGER.warning 
                ("Received response with unrecognized request ID: " + 
                    requestID);
            return;
        }
        
        outstandingTrades.remove (requestID);
        
        trade.setResult (result);
        trade.setSize (shares);
        trade.setToNow ();

        if (forcedPartialTrades.containsKey (requestID))
        {
            if (trade.getResult () == Trade.Result.FILLED)
            {
                trade.setSize (forcedPartialTrades.get (requestID));
                trade.setResult (Trade.Result.PARTIALLY_FILLED);
            }
            
            forcedPartialTrades.remove (requestID);
        }
        
        double factor = GENERATOR.nextDouble ();
        int delayInMSec = (int) (factor * factor * factor * 10000) + 500;
            
        class DelayedResponse
            extends TimerTask
        {
            private Trade trade;
            private String requestID;
            
            public DelayedResponse (Trade trade, String requestID)
            {
                this.trade = trade;
                this.requestID = requestID;
            }
            
            @Override
            public void run ()
            {
                try
                {
                    Message reply = replyClient.getSession ().createTextMessage 
                        (TradeMessenger.tradeToXML (trade));
                    reply.setJMSCorrelationID (requestID);
                    sender.send (reply);
                    ++responseCount;
                }
                catch (Exception ex)
                {
                    LOGGER.log (Level.SEVERE, 
                        "Failed to send response to confirm requested trade", ex);
                }
            }
        }
        timer.schedule (new DelayedResponse (trade, requestID), delayInMSec);
    }
    
    private class FixResponseHandler
        extends FixEngine
    {
        @Override
        public void onMessage 
                (quickfix.fix42.ExecutionReport message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue 
        {
            OrdStatus ordStatus = new OrdStatus();
            message.get(ordStatus);
            if (ordStatus.getValue () == OrdStatus.NEW)
                return;

            ClOrdID orderID = new ClOrdID ();
            message.get(orderID);
            String requestID = 
                orderID.getValue ().replace ("EQUITY-TRADER-", "");
            
            LastShares ls = new LastShares();
            message.get(ls);
            
            sendTradeResponse (requestID, 
                responseCodes.get (ordStatus.getValue ()), 
                (int) ls.getValue ());
        }
    }
    
    /**
    Take incoming trade messages, break out the Trade content,
    and {@link #makeTrade place the trade with the OrderManager}.
    */
    @Override
    public void onMessage (Message message)
    {
        LOGGER.log (Level.INFO, "Received trade request.");
        
        try
        {
            if (!(message instanceof TextMessage))
            {
                LOGGER.log (Level.WARNING, 
                    "Received non-text message -- ignoring.");
                return;
            }
            
            Trade trade = TradeMessenger.parseTradeMessage ((TextMessage) message);
            String requestID = message.getJMSCorrelationID ();
            if (requestID == null)
            {
                requestID = "OrderBroker." + ++requestIDSeq;
                
                LOGGER.log (Level.WARNING, 
                    "No correlation ID found in trade request; " +
                    "no response message will be sent to reply queue. " +
                    "Trade ID is " + trade.getId ());
            }
            
            sendTradeRequest (requestID, trade);
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.SEVERE, "Unable to process trade request", ex);
        }
    }
    
    /**
    Examples:
      Defaults all around: use QuickFixJ, fail every 5th time, and do a partial fill every 7 trades:
      java com.citi.trading.order.OrderBroker 
      
      Mock QuickFixJ, fail every 5th time;
      java com.citi.trading.order.OrderBroker mock 5
      
      Mock QuickFixJ, fail every 5th time, partial every 3rd time;
      java com.citi.trading.order.OrderBroker mock 5  3
      
      Mock QuickFixJ, never fail, partial every 3rd time;
      java com.citi.trading.order.OrderBroker mock 0 3
    */
    public static void main (String[] args)
    {
        try ( TradeMessenger service = new TradeMessenger (); )
        {
            MOCK_QUICK_FIX = 
                args.length > 0 && args[0].equalsIgnoreCase ("mock");
            FAIL_ONCE_PER = args.length > 1 ? Integer.parseInt (args[1]) : 5;
            //FAIL_AT_RANDOM = args.length > 2 && args[2].equalsIgnoreCase ("random");
            PARTIAL_ONCE_PER = args.length > 2 ? Integer.parseInt (args[2]) : 7;
            //PARTIAL_AT_RANDOM = args.length > 4 && args[4].equalsIgnoreCase ("random");
            
            QueueReceiver receiver = service.openToReceive ();
            receiver.setMessageListener (new OrderBroker ());
            System.out.println ("Listening for trade messages on OrderBroker queue.");
            System.out.println ("Hit ENTER to quit ...");
            System.in.read ();
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.SEVERE, "OrderBroker crashed!", ex);
        }
    }
}
