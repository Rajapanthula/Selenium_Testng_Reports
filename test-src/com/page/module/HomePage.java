package com.page.module;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.HomePageLocator;

public class HomePage extends CommonFunctionalities implements HomePageLocator {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void searchPage(String search_key) throws InterruptedException {
		safeType(SearchText, search_key, LONGWAIT);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);
		Thread.sleep(1000);
		action.sendKeys(Keys.TAB);
		safeClick(Searc_Button, LONGWAIT);
	}
}
