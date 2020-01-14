/**
 * 
 */
package com.cogmento.common;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * @author Harini
 *
 */
public class Config {
	private static Properties config = new Properties();;
	/*
	 * Loads the external config file {user.home}/com/freecrm/qa/config.properties.
	 * If not available, loads the config file from the build classpath.
	 */
	static {
		String userHomeDir = FileUtils.getUserDirectory().getAbsolutePath();
		String path = userHomeDir + "/com/cogmento/qa/config/";
		String file = "config.properties";
		// load properties from file system - user directory
		try {
			FileInputStream ip = new FileInputStream(path + file);
			config.load(ip);
		} catch (Exception e) {
			// if properties not externalized, load from classpath
			try {
				config.load(Config.class.getClassLoader().getResourceAsStream(file));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public static String getProperty(String key) {
		return config.getProperty(key);
	}
}
