package com.page.locators;

import org.openqa.selenium.By;

public interface HomePageLocator {
	By SearchText = By.xpath("//textarea[@id='APjFqb']");
	By Searc_Button = By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@value='Google Search']");
	By USER_MAIN = By.xpath("//a[contains(@href,'UserManager.aspx')]");

}
