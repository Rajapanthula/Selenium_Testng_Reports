package com.page.module;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.FaceBookLocator;
import com.page.locators.HomePageLocator;
import com.page.locators.WiproLocator;

public class Wipro extends CommonFunctionalities implements WiproLocator {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public Wipro(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void EnterCredentials(String username, String password) {
		safeType(Username, username, LONGWAIT);
		safeType(Password, password, LONGWAIT);
	}

	public void LoginToFB() {
		safeClick(LoginFB, LONGWAIT);
	}
}
