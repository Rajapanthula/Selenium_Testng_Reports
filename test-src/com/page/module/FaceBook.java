package com.page.module;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.FaceBookLocator;
import com.page.locators.HomePageLocator;

public class FaceBook extends CommonFunctionalities implements FaceBookLocator {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public FaceBook(WebDriver driver) {
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

	public void Home() {
		safeClick(Home, LONGWAIT);
	}

	public void LogoutToFB() {
		safeClick(SignOutImage, LONGWAIT);
		safeClick(LogOut, LONGWAIT);

	}

	public void Search(String value) {
		safeType(Search, value, LONGWAIT);
		safeClick(FirstSearch, LONGWAIT);
		safeClick(ClickONPersis, LONGWAIT);

	}

}
