package com.macys.azure.jms.relay;

//import org.apache.log4j.Logger;

/**
 * Instances of this class relay a message to an Azure Storage Queue.
 *  
 * @author Chris Joakim, Microsoft
 * @date   2017/04/07
 */

public class StorageQueueRelayer extends BaseRelayer implements IMessageRelay {

	// Constants
	//private static final Logger logger = Logger.getLogger(StorageQueueRelayer.class);
	
	public StorageQueueRelayer() {
		
		super();
	}
	
	@Override
	public boolean relayMessage(String msg) throws Exception {

		throw new Exception("This class is not yet implemented");
	}

}
