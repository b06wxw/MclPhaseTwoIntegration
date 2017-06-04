package com.macys.azure.util;

//import java.awt.PageAttributes.MediaType;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import com.macys.azure.jms.relay.IMessageRelay;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * This class implements Azure ServiceBus operations.
 * 
 * @author Chris Joakim, Microsoft
 * @date   2017/04/07
 */

public class APIUtil implements IMessageRelay  {

	// Constants:
	private final static Logger logger = Logger.getLogger(APIUtil.class);
	
	// Instance variables:
	//private static String restAPIUrl  = "http://localhost:3000/orders/update?";
	private static String restAPIUrl = null;
	private static APIUtil INSTANCE = null;
	
	
	private APIUtil() {
		
		super();
		
	}
	
    public static APIUtil getAPIClientHandle() {
    	
			if (INSTANCE == null ) {
				INSTANCE = new APIUtil();
			}
			
			setAPIConfigurations();
			return INSTANCE;     
    }
    
	private static void setAPIConfigurations() {
		restAPIUrl = ConfigManager.envVar(IMessageRelay.API_DESTINATION_URL);
	}
	
    
    
    public void sendMessageViaAPI(String msg) {
    	try {

    		 ClientConfig config = new DefaultClientConfig();
    		 
    		   Client client = Client.create(config);
    		 
    		   WebResource webResource = client.resource(UriBuilder.fromUri(restAPIUrl).build());
    		 
    
    		   MultivaluedMap formData = new MultivaluedMapImpl( );
    		 
    		   //Hard code here		   
    		   formData.add("name1", "val1");
    		   //hard code here 
    		   formData.add("name2", "val2");
    		 
    		   ClientResponse response = webResource.post(ClientResponse.class, formData);
    		 
    		   System.out.println("Response" + response.getEntity(String.class));


    	  } catch (Exception e) {
    		logger.error("error when sending  message. to API destination at " + restAPIUrl);
    		logger.error("The message being set out is  " + msg);
    		e.printStackTrace();

    	  }

    }
}
