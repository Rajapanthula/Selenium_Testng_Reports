package com.selenium;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.Reports.TimeOuts;
import com.datamanager.ConfigManager;

public class Sync implements TimeOuts {
	private Logger log = Logger.getLogger(Sync.class);
	ConfigManager app = new ConfigManager("App");
	private WebDriver driver;

	public Sync(WebDriver driver) {
		this.driver = driver;
	}

	public void nullifyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify implicitlyWait()
	}

	public void setImplicitWait(int waitTimeInSeconds) {
		driver.manage().timeouts().implicitlyWait(waitTimeInSeconds, TimeUnit.SECONDS);
	}

	public boolean waitForJavaScriptCondition(final String javaScript, int timeOutInSeconds) {
		boolean jscondition = false;
		nullifyImplicitWait();
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driverObject) {
					return (Boolean) ((JavascriptExecutor) driverObject).executeScript(javaScript);
				}
			});
			jscondition = (Boolean) ((JavascriptExecutor) driver).executeScript(javaScript);
		} catch (Exception e) {
			log.error("js condition not satisfied.." + " - " + e);
			Assert.fail("js condition not satisfied..");
		}
		setImplicitWait(IMPLICITWAIT);
		return jscondition;
	}

	public boolean isElementPresent(By locator, int waitTime) {
		boolean bFlag = false;
		nullifyImplicitWait();
		log.info("Waiting for presence of element " + locator);
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (driver.findElement(locator).isDisplayed() || driver.findElement(locator).isEnabled()) {
				bFlag = true;
				log.info("Element " + locator + " is displayed");
			}
		} catch (NoSuchElementException e) {
			log.info("Element " + locator + " was not found in DOM in time - " + waitTime
					+ " Seconds - NoSuchElementException");
		} catch (TimeoutException e) {
			log.info("Element " + locator + " was not displayed in time - " + waitTime + " Seconds"
					+ " - TimeoutException");
		} catch (Exception e) {
			log.error("Element " + locator + " is not found - " + e);
			Assert.fail("Element " + locator + " is not displayed");
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	public boolean waitUntilClickable(By locator, int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		try {
			log.info("Waiting until element " + locator + " is clickable");
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable(locator));

			if (driver.findElement((locator)).isDisplayed()) {
				bFlag = true;
				log.info("Element " + locator + " is displayed and is clickable");
			}
		}

		catch (NoSuchElementException e) {
			log.error("Unable to find the " + locator + " in DOM in time " + waitTime
					+ " Seconds - NoSuchElementException");
			Assert.fail("Unable to find the " + locator + " in DOM in time " + waitTime
					+ " Seconds - NoSuchElementException");
		} catch (TimeoutException e) {
			log.error("Element " + locator + "was not displayed in time - " + waitTime + " Seconds - TimeoutException");
			Assert.fail(
					"Element " + locator + " was not displayed in time - " + waitTime + " Seconds - TimeoutException");
		} catch (Exception e) {
			log.error("Element " + locator + "was not displayed - " + e);
			Assert.fail("Element " + locator + " was not displayed");
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	public boolean isElementClickable(By locator, int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		try {
			log.info("Waiting until element " + locator + " is clickable");
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable(locator));

			if (driver.findElement((locator)).isDisplayed()) {
				bFlag = true;
				log.info("Element " + locator + " is displayed and is clickable");
			}
		}

		catch (NoSuchElementException e) {
			log.error("Element " + locator + " was not displayed in time - " + waitTime
					+ " Seconds - NoSuchElementException");
		} catch (TimeoutException e) {
			log.error("Element " + locator + " was not found in time - " + waitTime + " Seconds - TimeoutException");
		} catch (Exception e) {
			log.error("Element " + locator + " was not found - " + e);
			Assert.fail("Element " + locator + " was not found");
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	public boolean isElementClickable(WebElement element, int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		try {
			log.info("Waiting until element " + element + " is clickable");
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable(element));

			if (element.isDisplayed()) {
				bFlag = true;
				log.info("Element " + element.toString() + " is displayed and is clickable");
			}
		}

		catch (NoSuchElementException e) {
			log.error("Element " + element.toString() + " was not displayed in time - " + waitTime
					+ " Seconds - NoSuchElementException");
		} catch (TimeoutException e) {
			log.error("Element " + element.toString() + " was not found in time - " + waitTime
					+ " Seconds - TimeoutException");
		} catch (Exception e) {
			log.error("Element " + element.toString() + " was not found - " + e);
			Assert.fail("Element " + element.toString() + " was not found");
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	public boolean isElementVisible(By locator, int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		log.info("Waiting until element " + locator + " is visible");
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			setImplicitWait(IMPLICITWAIT);
			if (driver.findElement((locator)).isDisplayed()) {
				bFlag = true;
				log.info("Element " + locator + " is displayed");
			}
		} catch (NoSuchElementException e) {
			log.info("Element " + locator + " was not displayed in time - " + waitTime
					+ " Seconds - NoSuchElementException");
		} catch (TimeoutException e) {
			log.info("Element " + locator + " was not displayed in time - " + waitTime + " Seconds - TimeoutException");
		}

		catch (Exception e) {
			log.error("Element " + locator + " was not displayed - " + e);
			Assert.fail("Element " + locator + " was not displayed");
		}
		return bFlag;
	}

	public void waitForPageToLoad(final String text) throws Exception {
		log.info("Waiting for page to be loaded...");
		nullifyImplicitWait();
		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.partialLinkText(text));
			}
		});
		setImplicitWait(IMPLICITWAIT);
	}

	public boolean waitForPageToLoad(int timeOutInSeconds) {
		log.info("Waiting for page to be loaded...");
		boolean isPageLoadComplete = false;
		nullifyImplicitWait(); // nullify implicitlyWait()
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driverObject) {
					return (Boolean) ((String) ((JavascriptExecutor) driver)
							.executeScript("return document.readyState")).equals("complete");
				}
			});
			isPageLoadComplete = (Boolean) ((String) ((JavascriptExecutor) driver)
					.executeScript("return document.readyState")).equals("complete");
		} catch (Exception e) {
			log.error("Unable to load web page in time - " + timeOutInSeconds + " Seconds - " + e);
			Assert.fail("unable to load webpage in time - " + timeOutInSeconds);
		}
		setImplicitWait(IMPLICITWAIT);
		return isPageLoadComplete;
	}

	public void waitForPageToLoad() {
		log.info("Waiting for page to be loaded...");
		try {
			int waitTime = 0;
			boolean isPageLoadComplete = false;
			do {

				isPageLoadComplete = ((String) ((JavascriptExecutor) driver)
						.executeScript("return document.readyState")).equals("complete");
				System.out.print(".");
				Thread.sleep(500);
				waitTime++;
				if (waitTime > 500) {
					break;
				}
			} while (!isPageLoadComplete);

			if (!isPageLoadComplete) {

				log.error("Unable to load webpage with in default timeout 250 seconds");
				Assert.fail("unable to load webpage with in default timeout 250 seconds");
			}
		} catch (Exception e) {
			log.error("Unable to load web page - " + e);
			Assert.fail("unable to load webpage");
		}

	}

	public boolean isElementPresentWithoutWait(By locator) {
		return driver.findElements(locator).size() > 0;
	}

	public boolean isElementPresent(By locator) {
		log.info("Waiting for presence of element " + locator);
		setImplicitWait(IMPLICITWAIT);
		return driver.findElements(locator).size() > 0;
	}

	public boolean isElementEnabled(By locator) {
		log.info("Verifying if element " + locator + " is enabled");
		boolean isEnabled = false;
		setImplicitWait(IMPLICITWAIT);
		try {
			if (isElementPresent(locator)) {
				isEnabled = driver.findElement(locator).isEnabled();
			}
		} catch (Exception e) {
			log.error("Exception occured while determining state of " + locator + " - " + e);
		}
		return isEnabled;
	}

	public boolean isElementSelected(By locator) {
		log.info("Verifying if element " + locator + " is selected");
		boolean isSelected = false;
		setImplicitWait(IMPLICITWAIT);
		try {
			if (isElementPresent(locator)) {
				isSelected = driver.findElement(locator).isSelected();
			}
		} catch (Exception e) {
			log.error("Exception occured while determining state of " + locator + " - " + e);
		}
		return isSelected;
	}

	public int getWaitTime(int[] optionalWaitArray) {
		if (optionalWaitArray.length <= 0) {
			return MEDIUMWAIT;
		} else {
			return optionalWaitArray[0];
		}
	}

	public boolean isElementDisplayed(By locator) {
		log.info("Verifying if element " + locator + " is displayed");
		boolean isDisplayed = false;
		setImplicitWait(IMPLICITWAIT);

		try {
			if (isElementPresent(locator)) {
				isDisplayed = driver.findElement(locator).isDisplayed();
			}
		} catch (Exception e) {
			log.error("Exception occured while determining state of " + locator + " - " + e);
		}
		return isDisplayed;
	}

	public File getTheNewestFile(String filePath) {
		File theNewestFile = null;
		File dir = new File(filePath);
		File[] files = dir.listFiles();

		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}

		return theNewestFile;
	}
}