package com.macys.azure.util;

import java.io.UnsupportedEncodingException;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import com.macys.azure.jms.relay.IMessageRelay;



/**
 * This class implements Azure ServiceBus operations.
 * @author Brain May
 * @author Wangshui Wei, move configuration variable to using enviornment variable
 * @date   2017/05/07
 */

public class RocketMQUtil implements IMessageRelay {

	// Constants:
	private final static Logger logger = Logger.getLogger(RocketMQUtil.class);
	

	
	// Configuration variable to from environment variable
	private static String _groupName = null;		
	private static String _topic = null;
	private static String _tag = null;
	private static String _nameServer = null;
	private static MQConsumer _consumer = null;
	
	
	private static RocketMQUtil INSTANCE ;
	
	
	protected RocketMQUtil() {
		
		super();
		
	}
	
 
	
	/**
	 * Standard Singleton pattern to allow only one instance per JVM
	 * 
	 * @return RocketMQUtil
	 */
    public static RocketMQUtil getMQClientHandle() {
    	
			if (INSTANCE == null ) {
				INSTANCE = new RocketMQUtil();
			}
			//Setup Rocket MQ configurations
			setQueueConfigurations();
			
			return INSTANCE;
    }
    
	 /**
	  * Help method which load the configuration variables 
	  * 
	  */
    private static void setQueueConfigurations() {
	   	 _groupName = ConfigManager.envVar(IMessageRelay.ROCKET_MQ_GROUP_NAME);
		 _topic =  ConfigManager.envVar(IMessageRelay.ROCKET_MQ_TOPIC_NAME);
		_tag = ConfigManager.envVar(IMessageRelay.ROCKET_MQ_TAG_NAME);
		 //nameServer = "192.168.177.129:9876";
		 _nameServer =  ConfigManager.envVar(IMessageRelay.ROCKET_MQ_SERVER_URL);
   	
   }
    
    /**
     * This is the producer which pumping message into a RocketMQ
     * @param o
     */
    public   void sendMessageToRocketMQ(String o) {
    	
    // Build message
	Message msg = new Message(_topic, _tag, o.getBytes());
	
	// TODO Place message on queue
	try {
		MQProducer _producer = new DefaultMQProducer(_groupName);
		((DefaultMQProducer) _producer).setNamesrvAddr(_nameServer);
		_producer.start();
		SendResult rslt = _producer.send(msg);
		if (!(rslt.getSendStatus() == SendStatus.SEND_OK)) {
			logger.error("Could not send message to queue - {}");
			logger.error ( "" + rslt.getSendStatus().toString() );
		}
	} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
		logger.error("Failed to place message onto queue");
		logger.error(e.toString());
	}
}
    
    /**
     * This is a constant running message listener which listen to the messages and sent to destination via the APITUIL
     * The Message destination is abstracted behind the APIUtil
     * 
     * @param destinationAPIClient
     */
    
    public void listenAndConsumeMessages(APIUtil destinationAPIClient ) {
  
    		_consumer = new DefaultMQPushConsumer(_groupName);
    		((DefaultMQPushConsumer) _consumer).setNamesrvAddr(_nameServer);
    		
    		try {
    			((DefaultMQPushConsumer)_consumer).subscribe(_topic, _tag);
    			((DefaultMQPushConsumer)_consumer).registerMessageListener(new MessageListenerConcurrently() 
    				{ public ConsumeConcurrentlyStatus consumeMessage(final List<MessageExt> msgs,
    		            final ConsumeConcurrentlyContext context) {
    				
    					logger.debug("Received {} message" +  msgs.size());
    					String s;
    					for (MessageExt m : msgs) {
    						try {
    							s = new String(m.getBody(), "UTF-8");
    							//This is the where message are sending to cloud via REST API
    							destinationAPIClient.sendMessageViaAPI(s);
    							logger.info("{}"+ s);
    						} catch (UnsupportedEncodingException e) {
    							logger.error("Could not convert bytes to string");
    							continue;
    						}
    					}
    				
    					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    				}
    			});
    			((DefaultMQPushConsumer)_consumer).start();
    		} catch (MQClientException e) {
    			logger.error(e.toString());
    			//throw new Exception("Failed to configure and start consumer");
    			System.out.println("rocket MQ start has exception" + e);
    		}
    		catch (Throwable e)
    		{
    			logger.error(e.toString());
    			System.out.println("rocket MQ start has exception" + e);
    		}    		  		    

    }
}
