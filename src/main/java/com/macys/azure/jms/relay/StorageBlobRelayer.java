package com.macys.azure.jms.relay;

//import org.apache.log4j.Logger;

/**
 * Instances of this class relay a message to an Azure Storage Blob.
 *  
 * @author Chris Joakim, Microsoft
 * @date   2017/04/07
 */

public class StorageBlobRelayer extends BaseRelayer implements IMessageRelay {

	// Constants
	//private static final Logger logger = Logger.getLogger(StorageBlobRelayer.class);
	
	public StorageBlobRelayer() {
		
		super();
	}
	
	@Override
	public boolean relayMessage(String msg) throws Exception {

		throw new Exception("This class is not yet implemented");
	}

}
