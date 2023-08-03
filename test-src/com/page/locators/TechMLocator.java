package com.page.locators;

import org.openqa.selenium.By;

public interface TechMLocator {
	By Password = By.xpath("//input[@id='pass']");
	By Username = By.xpath("//input[@id='email']");
	By LoginFB = By.xpath("//button[@name='login']");
	By USER_MAIN = By.xpath("//a[contains(@href,'UserManager.aspx')]");

}
