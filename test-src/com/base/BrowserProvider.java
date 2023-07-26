package com.base;

import org.testng.log4testng.Logger;

/**
 * Class which provides the run-time object for Browser Classes
 */
public class BrowserProvider {
	private Logger log = Logger.getLogger(BrowserProvider.class);

	
	public IBrowser getBrowserInstance(String browserName) {
		if (browserName == null) {
			log.info("The Browser Name is provided as Null,Please provide the correct BrowserName in testNg.xml");
			return null;
		}
		if (browserName.equalsIgnoreCase("chrome")) {
			log.info("The Browser Name is provided as Chrome");
			return new ChromeBrowser();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			log.info("The Browser Name is provided as Firefox");
			return new FirefoxBrowser();
		}
		log.error("browser : " + browserName
				+ " is invalid, Launching Firefox as browser of choice..");
		return new FirefoxBrowser();
	}
}