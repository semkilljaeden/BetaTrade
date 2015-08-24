package quickfix;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class TradeHandler implements QuickfixInterface {
	private TradeMessenger sender;
	private TradeMessenger receiver;
	private Queue<TextMessage> responseQueue;
	public TradeHandler() {
		sender = new TradeMessenger();
		receiver = new TradeMessenger(TradeMessenger.RESPONSE_QUEUE);
		/**
		 * sender thread
		 */
	}
	
	public void sendOrder(final Trade trade) {
		QueueSender qSender;
		try {
			qSender = sender.openToSend();
			TextMessage message = sender.createTradeMessage(trade);
			String uUIDString = UUID.randomUUID().toString();
			message.setJMSCorrelationID(uUIDString);
			qSender.send(message);
			listenToReply();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void listenToReply() {
        new Thread() {
            public void run() {
                /* block of code which need to execute via thread */
        		QueueReceiver qReceiver;
        		try {
        			qReceiver = receiver.openToReceive();
        			TextMessage m = (TextMessage) qReceiver.receive(1000 * 60 * 20);
        			responseQueue.add(m);
        			System.out.println(m.getText());
        		} catch (JMSException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            }
        }.start();

	}
}
