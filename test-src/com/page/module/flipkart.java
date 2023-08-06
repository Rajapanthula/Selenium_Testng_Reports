package com.page.module;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.FaceBookLocator;
import com.page.locators.FlipkartLocator;
import com.page.locators.HomePageLocator;

public class flipkart extends CommonFunctionalities implements FlipkartLocator {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public flipkart(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void EnterCredentials(String username, String password) {
		if (isElementPresent(Username))
			safeType(Username, username, LONGWAIT);
		else
			safeClick(Login, LONGWAIT);
		
		safeType(Username, username, LONGWAIT);
	}

	public void close() {
		if (isElementPresent(close))
			safeClick(close, LONGWAIT);

	}

	public void searchItem() {
		safeType(Searchitem, "phone", LONGWAIT);
		safeClick(SearchButton, LONGWAIT);

	}
}
