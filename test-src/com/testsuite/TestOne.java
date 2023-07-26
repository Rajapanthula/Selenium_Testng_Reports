package com.testsuite;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.datamanager.ConfigManager;
import com.datamanager.ExcelManagerFillo;
import com.page.module.HomePage;
import com.page.module.LoginPage;

public class TestOne extends BaseSetup {
	ConfigManager sys;
	HomePage HomePage;
	ExcelManagerFillo ExcelManagerFillo = new ExcelManagerFillo("Data\\DataSheet.xlsx");

	@Test(dataProvider = "UserAccess")
	public void UserAccessCreation(String name, String username, String password, String search_key) throws Exception {
		
//		startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "Invalid Login Scenario with invalid username and password.");
		HomePage = new HomePage(getDriver());
		HomePage.NavigateToURL("https://google.com");
		HomePage.searchPage(search_key);

	}

	@DataProvider(name = "UserAccess")
	public Object[][] AllActivity(ITestContext context) {
		Object[][] products = ExcelManagerFillo.getExcelSheetData("UserAccess");
		return products;
	}

}
