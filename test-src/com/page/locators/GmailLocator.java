package com.page.locators;

import org.openqa.selenium.By;

public interface GmailLocator {
	By Password = By.xpath("//input[@type='password']");
	By Username = By.xpath("//input[@autocomplete='username']");
	By LoginGmail = By.xpath("//button[@name='login']");
	By Next = By.xpath("//span[contains(text(),'Next')]");

	By Signin = By.xpath("//a[contains(text(),'Sign in')]");
	By Inbox = By.xpath("//a[contains(text(),'Inbox')]");
	By Sent = By.xpath("(//a[contains(text(),'Sent')])[1]");
	By Compose = By.xpath("//div[contains(text(),'Compose')]");
	By ComposeClose = By.xpath("//img[@aria-label='Save & close']");	
	By SignOutImage = By.xpath("//img[@class='gb_n gbii']");
	By Signout = By.xpath("//div[contains(text(),'Sign out')]");
	
}
