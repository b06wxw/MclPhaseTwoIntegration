package com.macys.azure.jms.relay;



import org.apache.log4j.Logger;
import com.macys.azure.util.APIUtil;
import com.macys.azure.util.RocketMQUtil;

/**
 * This is class implements a source and sink model, where this class will be running as the
 * Scalable pipe (broker)  between the message source and destination
 * The message source will be RocketMQ at Alibaba JST
 * The destination will be the Azure Service Bus abstract behind the reset API 
 * @author Wangshui Wei POC
 * @author Julian Wei, Adding comments and exception handling
 *
 */

public class RocketMQToAPIRepayer extends BaseRelayer {
	// Constants:
	private final static Logger logger = Logger.getLogger(RocketMQToAPIRepayer.class);
	
	
	/**
	 * 
	 * @param args passed from command line
	 */
	public static void main(String[] args) {
	
	try {
		RocketMQToAPIRepayer instance = new RocketMQToAPIRepayer();
		instance.relayMessage("API_MODE");
	}
	catch (Exception e) {
		System.out.println(e);
		logger.error("Message when processing RocketMQ Relay");
	}
	}
	
	
	/**
	 *This is the service entry point where source and destination handles are created
	 * The argument is no needed at this time 
	 */
	
	public  boolean relayMessage(String msg) throws Exception {

		// Use the singleton pattern to create one handle per JVM
		// The util currently only support one location, so the configuration is inside the util class itself
		RocketMQUtil mqUtil = RocketMQUtil.getMQClientHandle();
		APIUtil      apiUtil = APIUtil.getAPIClientHandle();
		//Listen to the messages and send out to the Azure service bus behind
		mqUtil.listenAndConsumeMessages(apiUtil);
		return true;
	}

}
