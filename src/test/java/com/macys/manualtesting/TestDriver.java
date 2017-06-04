package com.macys.manualtesting;

import com.macys.azure.jms.relay.RocketMQToAPIRepayer;

public class TestDriver {

	public static void main(String[] args) {
	
		System.out.println("This is a test");
		// Test the rest API where message will be sending to Azure Service Bus
		//APIUtil testingHandle = APIUtil.getAPIClientHandle();	
		//testingHandle.sendMessageViaAPI("test");
		
		//Test Reading message out of RocketMQ
		try {
			RocketMQToAPIRepayer instance = new RocketMQToAPIRepayer();
			instance.relayMessage("API_MODE");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
