package com.testsuite;

import static com.Reports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.datamanager.ConfigManager;
import com.datamanager.ExcelManagerFillo;
import com.page.module.FaceBook;
import com.page.module.Gmail;
import com.page.module.HomePage;
import com.page.module.LoginPage;
import com.page.module.flipkart;

public class TestOne extends BaseSetup {
	ConfigManager sys;
	HomePage HomePage;
	FaceBook facebook;
	flipkart flipkart;
	Gmail gmail;
	ExcelManagerFillo ExcelManagerFillo = new ExcelManagerFillo("Data\\DataSheet.xlsx");

	@Test(dataProvider = "UserAccess")
	public void UserAccessCreation(Method method, String name, String username, String password, String search_key)
			throws Exception {
		startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
		HomePage = new HomePage(getDriver());
		facebook = new FaceBook(getDriver());
		flipkart = new flipkart(getDriver());
		gmail = new Gmail(getDriver());

		HomePage.NavigateToURL("https://google.com");
		HomePage.searchPage(search_key);
		HomePage.ClickFirst();
		if (name.equals("flipart")) {
			flipkart.EnterCredentials(username, password);
			flipkart.close();
			flipkart.searchItem();
		}
		if (name.equals("gmail")) {
			gmail.clickonSignin();
			gmail.EnterCredentials(username, password);
		}
		if (name.equals("facebook")) {
			facebook.EnterCredentials(username, password);
			facebook.LoginToFB();
		}
	}

	@DataProvider(name = "UserAccess")
	public Object[][] AllActivity(ITestContext context) {
		Object[][] products = ExcelManagerFillo.getExcelSheetData("UserAccess");
		return products;
	}

}
