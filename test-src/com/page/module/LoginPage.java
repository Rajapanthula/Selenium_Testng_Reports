package com.page.module;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.HomePageLocator;

public class LoginPage extends CommonFunctionalities implements HomePageLocator {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void Login(String username, String password) {

	}

}
