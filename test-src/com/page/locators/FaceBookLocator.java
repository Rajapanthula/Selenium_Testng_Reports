package com.page.locators;

import org.openqa.selenium.By;

public interface FaceBookLocator {
	By Password = By.xpath("//input[@id='pass']");
	By Username = By.xpath("//input[@id='email']");
	By LoginFB = By.xpath("//button[@name='login']");
	By USER_MAIN = By.xpath("//a[contains(@href,'UserManager.aspx')]");
	
	
	

	By SignOutImage = By.xpath("//div[@aria-label='Account Controls and Settings']/span");
	By Home = By.xpath("//a[@aria-label='Home']");
	By LogOut = By.xpath("//span[contains(text(),'Log Out')]");
	By Search = By.xpath("//input[@aria-label='Search Facebook']");
	By FirstSearch = By.xpath("(//ul[@role='listbox']//span)[4]");
	By ClickONPersis = By.xpath("//a[contains(text(),'Persistent Systems 2016 Selected Grads.')]");
	
	
}
