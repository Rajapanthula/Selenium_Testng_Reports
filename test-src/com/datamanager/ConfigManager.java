package com.datamanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.log4testng.Logger;

public class ConfigManager {
	private Properties properties = new Properties();
	private String configFileName;
	private Logger log = Logger.getLogger(ConfigManager.class);
	private String fileSeperator = System.getProperty("file.separator");

	public ConfigManager() {
		configFileName = "Sys";
	}

	public ConfigManager(String configname) {
		configFileName = configname;
	}

	public String getProperty(String key) {
		String value = "";
		if (key != "") {
			loadProperties();
			try {
				if (!properties.getProperty(key).trim().isEmpty())
					value = properties.getProperty(key).trim();
			} catch (NullPointerException e) {
				Assert.fail("Key - '" + key + "' does not exist or not given a value in " + configFileName
						+ ".properties file \n");
			}
		} else {
			log.error("key cannot be null.. ");
			Assert.fail("key cannot be null.. ");
		}
		return value;
	}

	private void loadProperties() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(getConfigFilePath(configFileName));
			properties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			log.error("Cannot find configuration file - " + configFileName + ".properties" + " at "
					+ getConfigFilePath(configFileName));
			Assert.fail("Cannot find configuration file - " + configFileName + ".properties" + " at "
					+ getConfigFilePath(configFileName));
		} catch (IOException e) {
			log.error("Cannot read configuration file - " + " at " + getConfigFilePath(configFileName));
			Assert.fail("Cannot read configuration file - " + " at " + getConfigFilePath(configFileName));
		}
	}

	public String getConfigFilePath(String File) {
		String configFilePath;
		configFilePath = getConfigFolderPath() + fileSeperator + File.toLowerCase() + ".properties";
		return configFilePath;
	}

	public String getConfigFolderPath() {
		return System.getProperty("user.dir") + fileSeperator + "ConfigFiles";
	}

}
