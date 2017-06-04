package com.macys.azure.jms.relay;

import org.apache.log4j.Logger;

/**
 * This is the abstract base class for the several concrete Relayer classes.
 *  
 * @author Chris Joakim, Microsoft
 * @date   2017/04/07
 */

public abstract class BaseRelayer implements IMessageRelay {
	
	// Constants
	private static final Logger logger = Logger.getLogger(BaseRelayer.class);

	
	public BaseRelayer() {
		
		super();
	}
	
	/**
	 * Define a action where all the subclass must implement
	 * 
	 * @param msg, the message to be relayed
	 * @return boolean to indicate success or fail of the operation
	 * @throws Exception
	 * 
	 */
	public boolean relayMessage(String msg) throws Exception {
        logger.error("Subclasses should implement this 'relayMessage' message.");
		throw new Exception("Subclasses should implement this 'relayMessage' message.");
	}
}
