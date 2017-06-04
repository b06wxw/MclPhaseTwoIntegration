package com.macys.azure.jms.relay;

import org.apache.log4j.Logger;

import com.macys.azure.util.*;;

/**
 * Instances of this class relay a message to Azure ServiceBus.
 *  
 * @author Chris Joakim, Microsoft
 * @date   2017/04/07
 */

public class ServiceBusRelayer extends BaseRelayer implements IMessageRelay {

	// Constants
	private static final Logger logger = Logger.getLogger(ServiceBusRelayer.class);
	
	// Instance variables:
	private ServicebusUtil sbUtil = null;
	
	public ServiceBusRelayer() {
		
		super();
		this.sbUtil = new ServicebusUtil();
	}
	
	@Override
	public boolean relayMessage(String msg) throws Exception {
		try {
		sbUtil.putMessageOnQueue(msg);
		}
		catch (Exception e) {
			 logger.error("Subclasses should implement this 'relayMessage' message.");
			 return false;
		}
		return true;
	}

}
