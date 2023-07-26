package com.base;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;

public class FirefoxBrowser implements IBrowser {
	ConfigManager sys = new ConfigManager();
	WebDriver driver;
	FirefoxProfile firefoxProfile;
	private Logger log = Logger.getLogger(FirefoxBrowser.class);
	String fileSeperator = System.getProperty("file.separator");

	public WebDriver init() {
		System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
		log.info("Launching firefox with a new profile");
		driver = FirefoxDriver();
		return driver;
	}

	public WebDriver FirefoxDriver() {

		driver = new FirefoxDriver();
		return driver;
	}
}
