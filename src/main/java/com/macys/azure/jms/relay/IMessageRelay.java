package com.macys.azure.jms.relay;

/**
 * This interface defines constant values used in this package and message relay functionality.
 *  
 * @author Chris Joakim, Microsoft
 * @author Julian Wei MST Adding Configuration Variables
 * @date   2017/04/07
 */

public interface IMessageRelay {

	// Environment variable names:
	public static String JMSRELAY_HOST        = "JMSRELAY_HOST";
	public static String JMSRELAY_PASS        = "JMSRELAY_PASS";
	public static String JMSRELAY_PORT        = "JMSRELAY_PORT";
	public static String JMSRELAY_QUEUE       = "JMSRELAY_QUEUE";
	public static String JMSRELAY_USER        = "JMSRELAY_USER";
	
	public static String AZURE_SERVICEBUS_NAMESPACE   = "AZURE_SERVICEBUS_NAMESPACE";
	public static String AZURE_SERVICEBUS_KEY_NAME    = "AZURE_SERVICEBUS_KEY_NAME";
	public static String AZURE_SERVICEBUS_ACCESS_KEY  = "AZURE_SERVICEBUS_ACCESS_KEY";
	public static String AZURE_SERVICEBUS_QUEUE       = "AZURE_SERVICEBUS_QUEUE";
	
	// Default values:
	public static String DEFAULT_AMQ_HOST     = "localhost";
	public static String DEFAULT_AMQ_PASS     = "admin";
	public static String DEFAULT_AMQ_PORT     = "61616";
	public static String DEFAULT_AMQ_USER     = "admin";
	public static String DEFAULT_INPUT_QUEUE  = "relay";
	public static String DEFAULT_OUTPUT_QUEUE = "relay";
	
	// Other constants:
	public static int    IO_READER            = 1;
	public static int    IO_WRITER            = 2;
	
	public static String RELAYER_STORAGEBLOB  = "storageblob";
	public static String RELAYER_STORAGEQUEUE = "storagequeue";
	public static String RELAYER_EVENTHUB     = "eventhub";
	public static String RELAYER_SERVICEBUS   = "servicebus";
	
	// Configuration variables for RocketMQ
	public static String ROCKET_MQ_GROUP_NAME  = "ROCKET_MQ_GROUP_NAME";
	public static String ROCKET_MQ_TOPIC_NAME  =  "ROCKET_MQ_TOPIC_NAME";
	public static String ROCKET_MQ_TAG_NAME  =  "ROCKET_MQ_TAG_NAME";
	public static String ROCKET_MQ_SERVER_URL  = "ROCKET_MQ_SERVER_URL";
	
	// Configuration variables for API URL
	public static String API_DESTINATION_URL = "API_DESTINATION_URL";
	

	
}
