package com.citi.trading.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
Encapsulation of common JMS objects and practice: simplifies the process of
sending messages to or receiving messages from a single queue.

@author Will Provost
*/
/*
Copyright 2003-2014 by Will Provost.
All rights reserved by Capstone Courseware.
*/
public class PointToPointClient
    implements AutoCloseable
{
    protected QueueConnectionFactory factory;
    protected Queue queue;
    protected QueueConnection connection;
    protected QueueSession session;

    /**
    Prepends "dynamicQueues/" to the given queue name.
    Always uses the single "ConnectionFactory".
    Looks up both objects in a prepared JNDI context and stows them in protected
    fields.
    */
    public PointToPointClient (String queueName)
    {
        Context context = null;
        try
        {
            Properties props = new Properties();
            props.setProperty (Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty (Context.PROVIDER_URL, 
                "tcp://localhost:61616");

            context = new InitialContext (props);
            factory = (QueueConnectionFactory) context.lookup 
                ("ConnectionFactory");
            queue = (Queue) context.lookup ("dynamicQueues/" + queueName);
        }
        catch (Exception ex)
        {
            System.out.println ("Couldn't find JMS objects:");
            ex.printStackTrace ();
        }
        finally
        {
            if (context != null) try { context.close (); } catch (Exception ex) {}
        }
    }
    
    /**
    For use from Java EE components; prepends "java:comp/env/" to the given 
    queue name.  Looks up both objects in a default JNDI context and 
    stows them in protected fields.
    */
    public PointToPointClient (String queueName, String factoryName)
    {
        Context context = null;
        try
        {
            context = new InitialContext ();
            factory = (QueueConnectionFactory) context.lookup 
                ("java:comp/env/" + factoryName);
            queue = (Queue) context.lookup ("java:comp/env/" + queueName);
            System.out.println ("Found factory and queue in JNDI environment.");
        }
        catch (Exception ex)
        {
            System.out.println ("Couldn't find JMS objects:");
            ex.printStackTrace ();
        }
        finally
        {
            if (context != null) try { context.close (); } catch (Exception ex) {}
        }
    }
    
    /**
    Accessor for the queue object.
    */
    public Queue getQueue ()
    {
        return queue;
    }
    
    /**
    Accessor for the factory object.
    */
    public QueueConnectionFactory getFactory ()
    {
        return factory;
    }

    /**
    Accessor for the connection object.
    */
    public QueueConnection getConnection ()
    {
        return connection;
    }
    
    /**
    Accessor for the session object.
    */
    public QueueSession getSession ()
    {
        return session;
    }
    
    /**
    Initializes a connection and a session.
    Defaults to non-transactional, auto-acknowledge mode.
    */
    public void open ()
        throws JMSException
    {
        open (false, Session.AUTO_ACKNOWLEDGE);
    }
    
    /**
    Initializes a connection and a session.
    */
    public void open (boolean transacted, int acknowledgementMode)
        throws JMSException
    {
        if (connection != null)
            throw new java.lang.IllegalStateException ("Connection already open.");
            
        try
        {
            connection = factory.createQueueConnection ();
            session = connection.createQueueSession 
                (transacted, acknowledgementMode);
        }
        catch (JMSException ex)
        {
            if (connection != null) 
                close ();
            throw ex;
        }
    }

    /**
    Initializes a connection and a session; then creates and returns a sender.
    Defaults to non-transactional, auto-acknowledge mode.
    */
    public QueueSender openToSend ()
        throws JMSException
    {
        return openToSend (false, Session.AUTO_ACKNOWLEDGE);
    }

    /**
    Initializes a connection and a session; then creates and returns a sender.
    */
    public QueueSender openToSend (boolean transacted, int acknowledgementMode)
        throws JMSException
    {
        open (transacted, acknowledgementMode);
                    
        try
        {
            return session.createSender (queue);
        }
        catch (JMSException ex)
        {
            if (connection != null) 
                close ();
            throw ex;
        }
    }

    /**
    Initializes a connection and a session; then creates and returns a receiver.
    Defaults to non-transactional, auto-acknowledge mode.
    */
    public QueueReceiver openToReceive ()
        throws JMSException
    {
        return openToReceive (false, Session.AUTO_ACKNOWLEDGE);
    }

    /**
    Initializes a connection and a session; then creates and returns a receiver.
    */
    public QueueReceiver openToReceive 
            (boolean transacted, int acknowledgementMode)
        throws JMSException
    {
        return openToReceive (transacted, acknowledgementMode, null);
    }

    /**
    Initializes a connection and a session; then creates and returns a receiver.
    Sets a selector on the receiver.
    */
    public QueueReceiver openToReceive 
            (boolean transacted, int acknowledgementMode, String selector)
        throws JMSException
    {
        open (transacted, acknowledgementMode);
            
        try
        {
            connection.start ();
            return session.createReceiver (queue, selector);
        }
        catch (JMSException ex)
        {
            if (connection != null) 
                close ();
            throw ex;
        }
    }
    
    /**
    Initializes a connection and a session; then creates and returns a browser.
    */
    public QueueBrowser openToBrowse ()
        throws JMSException
    {
        if (connection != null)
            throw new java.lang.IllegalStateException ("Connection already open.");
            
        try
        {
            connection = factory.createQueueConnection ();
            session = connection.createQueueSession 
                (false, Session.AUTO_ACKNOWLEDGE);
            connection.start ();
            return session.createBrowser (queue);
        }
        catch (JMSException ex)
        {
            if (connection != null) 
                close ();
            throw ex;
        }
    }
    
    /**
    Closes the connection; this object could then be re-used for more actions
    on the same queue.
    */
    public void close ()
    {
        if (connection == null)
            throw new java.lang.IllegalStateException ("Connection not open.");
        
        try
        {
            connection.close ();
            connection = null;
        }
        catch (Exception ex) {}
    }
}
