# Rocket MQ to azure service bus relay

Read a RocketMQ queue, and relay each message to an Azure service Bus (ASB).
Implements a source and sink model, where
  The message SOURCE will be RocketMQ at Alibaba JST
   The destination will be the Azure Service Bus abstracted behind the rest API

RocketMQToAPIRelayer is the process entry point (MAIN method). The class can be run as 
stand along app.  

ConfigManager.java is the help class for dealing with configuration
APIUtil.java       is the help class which abstract Rest API access (Sink)
RocketMQUtil.java  is the help class which abstract Listener of push messages (source)

The project have many other future relayer implenmentation for future reference only, they are not currently implemented.


## Configuration

This project uses only environment variables for all configuration values.
These values aren't hard-coded in the codebase, nor are they specified in
property files.  The use of environment variables is more secure, and it
follow the **Twelve-Factor App** design; see https://12factor.net



## Setup - Azure 

In Azure Portal (https://portal.azure.com) create an Azure ServiceBus
PaaS service in your subscription.

Under Settings -> Shared Access Policies, select 'RootManageSharedAccessKey'.
Set the following environment variables on your workstation computer
with these Azure Portal values.

```
AZURE_SERVICEBUS_ACCESS_KEY=<your-key>
AZURE_SERVICEBUS_CONN_STRING=<your-connection-string>
AZURE_SERVICEBUS_KEY_NAME=RootManageSharedAccessKey
AZURE_SERVICEBUS_NAMESPACE=<your-namespace>
AZURE_SERVICEBUS_QUEUE=relay
```

## Setup - Development Workstation

- Java 8 JDK installed.
- Apache Maven installed.
- Python 3 installed.  Used to determine the Java classpath; see file 'classpath.sh'
- Eclipse IDE - optional.
- Apache ActiveMQ.  Used as a testing JMS server.
- git installed, and "git clone" this repository to your workstation.

The code in this project also uses the following **environment variables**.
    // Configuration variables for RocketMQ
	public static String ROCKET_MQ_GROUP_NAME  = "ROCKET_MQ_GROUP_NAME";
	public static String ROCKET_MQ_TOPIC_NAME  =  "ROCKET_MQ_TOPIC_NAME";
	public static String ROCKET_MQ_TAG_NAME  =  "ROCKET_MQ_TAG_NAME";
	public static String ROCKET_MQ_SERVER_URL  = "ROCKET_MQ_SERVER_URL";
	public static String API_DESTINATION_URL = "API_DESTINATION_URL";
Those are the name of the environmental variables defined in IMessageReplay.java


The default values are appropriate for a new, locally-running, ActiveMQ installation.


## RocketMQ 
This code assumes RocketMQ, and queues is already exist
The details for those RocketMQ queue are specified via the configuration of environnment variables


