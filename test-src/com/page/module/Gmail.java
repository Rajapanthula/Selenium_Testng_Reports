package com.page.module;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;
import com.page.locators.FaceBookLocator;
import com.page.locators.GmailLocator;
import com.page.locators.HomePageLocator;

public class Gmail extends CommonFunctionalities implements GmailLocator {

	ConfigManager appData = new ConfigManager("Sys");

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());

	public Gmail(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickonSignin() {
		safeClick(Signin, LONGWAIT);
	}

	public void EnterCredentials(String username, String password) {
		safeType(Username, username, LONGWAIT);
		safeClick(Next, LONGWAIT);
		safeType(Password, password, LONGWAIT);
		safeClick(Next, LONGWAIT);
	}

	public void clickInbox() {
		safeClick(Inbox, LONGWAIT);
	}

	public void clickSent() {
		safeClick(Sent, LONGWAIT);
	}

	public void clickCompse() {
		safeClick(Compose, LONGWAIT);
		safeClick(ComposeClose, LONGWAIT);
	}

	public void Logout() {
		safeClick(SignOutImage, LONGWAIT);
		safeClick(Signout, LONGWAIT);
	}
}
