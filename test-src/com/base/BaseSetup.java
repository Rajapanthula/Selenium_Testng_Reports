package com.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.log4testng.Logger;

import com.Reports.TimeOuts;
import com.Reports.UtilityMethods;
import com.datamanager.ConfigManager;

public class BaseSetup extends Thread implements TimeOuts {
	public WebDriver driver;
	private Logger log = Logger.getLogger(BaseSetup.class);
	ConfigManager sys = new ConfigManager();
	BrowserProvider browserProvider = new BrowserProvider();
	private boolean isReportFolderCreated = true;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
		UtilityMethods.disableWarning();
		if (isReportFolderCreated) {
			UtilityMethods.clearTasks();
			isReportFolderCreated = false;
		}
		log.info("<h2>--------------------SuiteRunner Log-------------------------<h2>");
	}

	@BeforeMethod(alwaysRun = true)
	public void initializeBaseSetup(ITestContext context) {
//		initiateDriver("firefox", "68", "windows", "10");
		initiateDriver("chrome", "64", "windows", "10");
	}

	public void initiateDriver(String browserType, String browserVersion, String OSName, @Optional String OSVersion) {

		log.info("Browser name present in config file :" + browserType);

		log.info("-----------------STARTED RUNNING SELENIUM TESTS ON LOCAL MACHINE------------------");
		setDriver(browserType);
	}

	private void setDriver(String browserName) {
		driver = browserProvider.getBrowserInstance(browserName).init();
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
		if (driver != null) {
			driver.quit();
		}
	}
}
