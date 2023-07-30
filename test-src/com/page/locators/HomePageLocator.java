package com.page.locators;

import org.openqa.selenium.By;

public interface HomePageLocator {
	By SearchText = By.xpath("//textarea[@id='APjFqb']");
	By Searc_Button = By.xpath("(//div[@id='res']//a)[1]/div");
	By First_Search = By.xpath("//div[@id='Alh6id']//li[1]");
	By USER_MAIN = By.xpath("//a[contains(@href,'UserManager.aspx')]");

}
