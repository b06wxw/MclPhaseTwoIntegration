package com.macys.azure.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Instances of this class are used to obtain configuration values for the other classes
 * in this project.  The configuration values typically come from environment variables
 * per the "Twelve-Factor App" design; see http://12factor.net.
 * 
 * @author Chris Joakim, Microsoft
 * @author Julian Wei    adding comments
 * @author Wangshui Wei, review 
 * @date   2017/04/07
 */



public class ConfigManager extends Object {
	
	// Constants:
	private static final Logger logger   = Logger.getLogger(ConfigManager.class);
	private  static ConfigManager INSTANCE = null;
	
	private ConfigManager() {
		
		super();
	}
	
	/**
	 * 
	 * @param resourceName
	 * @return String which represents the text co
	 */
	public static synchronized String getResourceAsString(String resourceName) {
		
		InputStream is = null;
		StringBuilder out = new StringBuilder();

		try {
			ClassLoader classLoader = ConfigManager.class.getClassLoader();
			is = classLoader.getResourceAsStream(resourceName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
		} 
		catch (IOException e) {
			logger.error("fail to build resource string for " + resourceName);
		} 
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return out.toString();
	}
	
	/**
	 * Standard Sington pattern to return only one instance
	 * @return
	 */
	
	public static synchronized ConfigManager getInstance() {
		if (INSTANCE == null) {
		INSTANCE = new ConfigManager();
		}
		return INSTANCE;
	}
	
	
	
	
	
	/**
	 * 
	 * @param name of the enviornment variable
	 * @param default return Value if no explict match 
	 * @return the matching key
	 */
	public static synchronized String envVar(String name, String defaultValue) {
		
		String value = envVars().get(name);
		if (value == null) {
			return defaultValue;
		}
		else {
			return value;
		}
	}
	
	/**
	 * A special case for envVar method, return null if no explict matching
	 * 
	 * it is essentially env(String Name , String Null)
	 * @param name
	 * @return the matching 
	 */
    public static synchronized String envVar(String name) {
		
		return envVar(name, null);
	}
	
	
	
	/**
	 * 
	 * @return  name/value pairs as map for enviornment variables configured at Machine level
	 * 
	 */
	private static synchronized Map<String, String> envVars() {
		
		return System.getenv();
	}
	
	
	
}
