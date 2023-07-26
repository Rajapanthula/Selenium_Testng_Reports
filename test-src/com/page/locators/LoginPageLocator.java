package com.page.locators;

import org.openqa.selenium.By;

public interface LoginPageLocator {
	By REGION = By.xpath("//td[contains(text(),'%s')]/following-sibling::td[%s]");
	By ADMIN_MAIN = By.xpath("(//a[contains(text(),'Admin')])[1]");
	By USER_MAIN = By.xpath("//a[contains(@href,'UserManager.aspx')]");

}
