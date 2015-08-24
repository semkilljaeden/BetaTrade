package com.citi.trading.mq;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.citi.trading.Trade;
import com.citi.trading.jms.PointToPointClient;

public class TradeMessenger
    extends PointToPointClient
{
    public static final String REQUEST_QUEUE = "OrderBroker";
    public static final String RESPONSE_QUEUE = "OrderBroker_Reply";
    
    private static Logger LOGGER = 
        Logger.getLogger (TradeMessenger.class.getName ());
    
    private static JAXBContext context;
    
    static 
    {
        try
        {
            context = JAXBContext.newInstance (Trade.class);
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.SEVERE, "Couldn't create JAXB context!", ex);
        }
    }
    
    /**
    Sets default queue name of "OrderBroker".
    */
    public TradeMessenger ()
    {
        super (REQUEST_QUEUE);
    }

    /**
    Provide your own queue name.
    */
    public TradeMessenger (String queueName)
    {
        super (queueName);
    }
    
    /**
    Provides an XML representation of the given trade, suitable for use 
    as the body of a JMS message.
    */
    public static String tradeToXML (Trade trade)
    {
        try ( StringWriter out = new StringWriter (); )
        {
            Marshaller marshaller = context.createMarshaller ();
            marshaller.marshal (trade, out);
            return out.toString ();
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.WARNING, "Couldn't build Trade message.", ex);
        }
        
        return null;
    }
    
    /**
    Factory method for a JMS text message with the contents of the 
    given trade represented in XML.
    */
    public TextMessage createTradeMessage (Trade trade)
    {
        try
        {
            return session.createTextMessage (tradeToXML (trade));
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.WARNING, "Couldn't build Trade message.", ex);
        }
        
        return null;
    }
    
    /**
    Parses an e XML representation of a trade, and returns the Trade object.
    */
    public static Trade tradeFromXML (String text)
    {
        try ( StringReader in = new StringReader (text); )
        {
            Unmarshaller unmarshaller = context.createUnmarshaller ();
            return (Trade) unmarshaller.unmarshal (in);
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.WARNING, "Couldn't parse Trade message.", ex);
        }
        
        return null;
    }
    
    /**
    Parses a JMS text message for the XML representation of a trade,
    and returns the Trade object.
    */
    public static Trade parseTradeMessage (TextMessage message)
    {
        try
        {
            return tradeFromXML (message.getText ());
        }
        catch (Exception ex)
        {
            LOGGER.log (Level.WARNING, "Couldn't parse Trade message.", ex);
        }
        
        return null;
    }
}
