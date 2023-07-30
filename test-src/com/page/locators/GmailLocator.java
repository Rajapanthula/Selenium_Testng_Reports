package com.page.locators;

import org.openqa.selenium.By;

public interface GmailLocator {
	By Password = By.xpath("//input[@id='pass']");
	By Username = By.xpath("//input[@autocomplete='username']");
	By LoginGmail = By.xpath("//button[@name='login']");
	By Next = By.xpath("//span[contains(text(),'Next')]");

	By Signin = By.xpath("//a[contains(text(),'Sign in')]");
}
