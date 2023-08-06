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
import com.page.module.Persistant;
import com.page.module.TechM;
import com.page.module.Wipro;
import com.page.module.flipkart;

public class TestOne extends BaseSetup {
	ConfigManager sys;
	HomePage HomePage;
	FaceBook facebook;
	flipkart flipkart;
	Gmail gmail;
	TechM techM;
	Wipro wipro;
	Persistant persis;
	ExcelManagerFillo ExcelManagerFillo = new ExcelManagerFillo("Data\\DataSheet.xlsx");

	@Test(dataProvider = "UserAccess")
	public void UserAccessCreation(Method method, String name, String username, String password, String search_key)
			throws Exception {
		startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
		HomePage = new HomePage(getDriver());
		facebook = new FaceBook(getDriver());
		flipkart = new flipkart(getDriver());
		techM = new TechM(getDriver());
		wipro = new Wipro(getDriver());
		persis = new Persistant(getDriver());
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
			gmail.clickInbox();
			gmail.clickSent();
			gmail.clickCompse();
			gmail.Logout();
			
		}
		if (name.equals("facebook")) {
			facebook.EnterCredentials(username, password);
			facebook.LoginToFB();
			facebook.Search("Persistant");
			facebook.Home();
			facebook.LogoutToFB();
		}
		if (name.equals("persistent systems")) {
			persis.EnterCredentials(username, password);
		}
		if (name.equals("tech mahindra")) {
			techM.EnterCredentials(username, password);
		}
		if (name.equals("wipro")) {
			wipro.EnterCredentials(username, password);
			wipro.LoginToFB();
		}
	}

	@DataProvider(name = "UserAccess")
	public Object[][] AllActivity(ITestContext context) {
		Object[][] products = ExcelManagerFillo.getExcelSheetData("UserAccess");
		return products;
	}

}
