package com.macys.azure.jms.relay;

import org.apache.log4j.Logger;

/**
 * Instances of this class relay a message to Azure EventHub.
 *  
 * @author Chris Joakim, Microsoft
 * @date   2017/04/07
 */

public class EventHubRelayer extends BaseRelayer implements IMessageRelay {

	// Constants
	private static final Logger logger = Logger.getLogger(EventHubRelayer.class);
	
	public EventHubRelayer() {
		
		super();
	}
	
	@Override
	public boolean relayMessage(String msg) throws Exception {
	    logger.error("Subclasses should implement this 'relayMessage' message.");
		throw new Exception("This class is not yet implemented");
	}

}
