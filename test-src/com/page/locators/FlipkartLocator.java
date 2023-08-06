package com.page.locators;

import org.openqa.selenium.By;

public interface FlipkartLocator {
	By Searchitem = By.xpath("//input[@autocomplete='off']");
	By Username = By.xpath("(//input[@autocomplete='off'])[2]");
	By close = By.xpath("//button[@class='_2KpZ6l _2doB4z']");
	By SearchButton = By.xpath("//button[@class='L0Z3Pu']");
	
	
	By Login = By.xpath("//a[contains(text(),'Login')]");

	
}
