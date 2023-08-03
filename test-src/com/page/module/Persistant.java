package com.page.module;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.FaceBookLocator;
import com.page.locators.HomePageLocator;
import com.page.locators.PersistantLocator;

public class Persistant extends CommonFunctionalities implements PersistantLocator {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public Persistant(WebDriver driver) {
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
