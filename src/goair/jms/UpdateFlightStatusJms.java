package goair.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class UpdateFlightStatusJms implements MessageListener{

	public static Logger sLogger = Logger.getLogger(UpdateFlightStatusJms.class);

	private TopicConnection connection;
	private TopicSession session;
	private Topic topic;
	@SuppressWarnings("unused")
	private MessageConsumer consumer;
	
	public static final String topicName = "UpdateFlightStatus";
	
	public UpdateFlightStatusJms() {
		try
		{
		    Properties properties = new Properties();
		    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		    properties.put(Context.PROVIDER_URL, "localhost");
		    
			InitialContext jndi = new InitialContext(properties);
			TopicConnectionFactory conFactory = (TopicConnectionFactory) jndi
	                .lookup("TopicConnectionFactory");
			connection = conFactory.createTopicConnection();
			
			session = connection.createTopicSession( false, Session.AUTO_ACKNOWLEDGE );
			
			try
			{
				topic = (Topic)jndi.lookup(topicName);
			}
			catch(NamingException NE1)
			{
				System.out.println("NamingException: "+NE1+ " : Continuing anyway...");
			}
			
			if( null == topic )
			{
				topic = session.createTopic(topicName);
				jndi.bind(topicName, topic);
			}
			
			System.out.println("Making a update flight status request....");
			connection.start();
		}
		catch(NamingException NE)
		{
			System.out.println("Naming Exception: "+NE);
		}
		catch(JMSException JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
			JMSE.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(Message message) {
		
	}

	public void updateFlightStatus() throws JMSException
	{
		MessageProducer MP = session.createProducer(topic);
		TextMessage TM = session.createTextMessage("updateFlightStatus");
		
		Topic replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
	}
}
