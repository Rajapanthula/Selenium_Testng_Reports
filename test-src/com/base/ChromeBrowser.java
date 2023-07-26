package com.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;

public class ChromeBrowser implements IBrowser {
	ConfigManager sys = new ConfigManager();
	private Logger log = Logger.getLogger(ChromeBrowser.class);
	private WebDriver driver;
	String fileSeperator = System.getProperty("file.separator");

	public WebDriver init() {

		driver = initChromeDriver();

		return driver;
	}

	private WebDriver initChromeDriver() {
		log.info("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", getDriverPath());
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type", "start-maximized");
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--use-fake-ui-for-media-stream");
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-component-update"));
		options.addArguments(Arrays.asList("--always-authorize-plugins", "--allow-outdated-plugins"));
		log.info("chrome driver initialized..");
		return new ChromeDriver(options);
	}

	private WebDriver initChromeDriver(String UserDataPath, String ProfileName) {
		log.info("Launching google chrome with specified profile - " + ProfileName);
		System.setProperty("webdriver.chrome.driver", getDriverPath());
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.prompt_for_download", false);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type", "start-maximized");
		options.setExperimentalOption("prefs", prefs);
		return new ChromeDriver(options);
	}

	public String getDriverPath() {

		String chromeLocation = System.getProperty("user.dir") + fileSeperator + "Drivers" + fileSeperator;

		if (System.getProperty("os.name").toLowerCase().contains("windows"))
			chromeLocation = chromeLocation + "chromedriver.exe";
		else if (System.getProperty("os.name").toLowerCase().contains("mac"))
			chromeLocation = chromeLocation + "chromedriver";

		return chromeLocation;

	}

}
