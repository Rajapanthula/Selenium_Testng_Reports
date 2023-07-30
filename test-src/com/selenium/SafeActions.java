/*************************************** PURPOSE **********************************

 - This class contains all UserAction methods
 */

package com.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.Reports.UtilityMethods;
import com.datamanager.ConfigManager;

public class SafeActions extends Sync {

	WebDriver driver;
	Logger log = Logger.getLogger(SafeActions.class);
	ConfigManager sys = new ConfigManager();

	public SafeActions(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void safeClick(By locator, int... optionWaitTime) {
		int waitTime = 0;
		try {
			waitTime = getWaitTime(optionWaitTime);
			if (waitUntilClickable(locator, waitTime)) {
				WebElement element = driver.findElement(locator);
				setHighlight(element);
				element.click();
				log.info("Clicked on the element " + locator);
			} else {
				log.error("Unable to find element " + locator + "in time - " + waitTime + " Seconds");
				Assert.fail("Unable to find element " + locator + "in time - " + waitTime + " Seconds");
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
			Assert.fail("Element with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
		} catch (NoSuchElementException e) {
			log.error("Element " + locator + " was not found in DOM in time - " + waitTime + " Seconds"
					+ " - NoSuchElementException");
			Assert.fail("Element " + locator + " was not found in DOM in time - " + waitTime + " Seconds"
					+ " - NoSuchElementException");
		} catch (Exception e) {
			log.error("Element " + locator + " was not clickable" + " - " + e);
			Assert.fail("Element " + locator + " was not found on the web page");
		}
	}

	public void safeClick(WebElement element, int... optionWaitTime) {
		@SuppressWarnings("unused")
		int waitTime = 0;
		try {
			waitTime = getWaitTime(optionWaitTime);

			setHighlight(element);
			element.click();

		} catch (Exception e) {
			log.error("Element " + element + " was not clickable" + " - " + e);
			Assert.fail("Element " + element + " was not found on the web page");
		}
	}

	public void safeActionsClick(By locator, int waitTime) {
		try {
			if (isElementVisible(locator, waitTime)) {
				WebElement element = driver.findElement(locator);
				setHighlight(element);
				Actions builder = new Actions(driver);
				builder.moveToElement(element).click().build().perform();
				log.info("Clicked on element " + locator);
			} else {
				log.error("Element " + locator + " was not visible to click in time - " + waitTime + " Seconds");
				Assert.fail("Element " + locator + " was not visible to click in time - " + waitTime + " Seconds");
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
			Assert.fail("Element with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
		} catch (NoSuchElementException e) {
			log.error("Element " + locator + " was not found in DOM in time - " + waitTime + " Seconds"
					+ " - NoSuchElementException");
			Assert.fail("Element " + locator + " was not found in DOM in time - " + waitTime + " Seconds"
					+ " - NoSuchElementException");
		} catch (Exception e) {
			log.error("Unable to click the cursor on " + locator + " - " + e);
			Assert.fail("Element " + locator + " was not visible on the web page");
		}
	}

	public void safeType(By locator, String text, int... optionWaitTime) {
		int waitTime = 0;
		try {
			waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locator, waitTime)) {
				scrollIntoElementView(locator);
				WebElement element = driver.findElement(locator);
				setHighlight(element);
				element.sendKeys(text);
				log.info("Entered - '" + text + " in the element - " + locator);
			} else {
				log.error("Unable to enter " + text + " in field " + locator + " in time - " + waitTime + " Seconds");
				Assert.fail("Unable to enter " + text + " in field " + locator + " in time - " + waitTime + " Seconds");
			}
		} catch (StaleElementReferenceException e) {
			log.error("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
			Assert.fail("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
		} catch (NoSuchElementException e) {
			log.error("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document in time - " + waitTime + " - NoSuchElementException");
			Assert.fail("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document in time - " + waitTime + " - NoSuchElementException");
		} catch (Exception e) {
			log.error("Unable to enter '" + text + "' text in field with locator -" + locator + " - " + e);
			Assert.fail("Unable to enter '" + text + "' text in field with locator -" + locator);
		}
	}

	public void safeClearAndType(String locato, String text, int... optionWaitTime) {
		int waitTime = 0;
		try {
			waitTime = getWaitTime(optionWaitTime);
			By locator = By.xpath(locato);
			if (driver.findElement((locator)).isDisplayed()) {
				scrollIntoElementView(locator);
				WebElement element = driver.findElement(locator);
				setHighlight(element);
				element.clear();
				element.sendKeys(text);
				log.info("Entered - '" + text + " in the element - " + locator);
			}
		} catch (Exception e) {
			String myString = locato.substring(1, locato.length() - 4);
			By locator1 = By.xpath(myString);
			if (driver.findElement((locator1)).isDisplayed()) {
				scrollIntoElementView(locator1);
				WebElement element = driver.findElement(locator1);
				setHighlight(element);
				element.clear();
				element.sendKeys(text);
				log.info("Entered - '" + text + " in the element - " + locator1);
			}
		}
	}

	public void safeActionType(By locator, String text, int... optionWaitTime) {
		int waitTime = 0;
		try {
			waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locator, waitTime)) {
				// scrollIntoElementView(locator);
				Actions actions = new Actions(driver);
				WebElement element = driver.findElement(locator);
				actions.sendKeys(element, text);
				setHighlight(element);
				// element.sendKeys(text);
				log.info("Entered - '" + text + " in the element - " + locator);
			} else {
				log.error("Unable to enter " + text + " in field " + locator + " in time - " + waitTime + " Seconds");
				Assert.fail("Unable to enter " + text + " in field " + locator + " in time - " + waitTime + " Seconds");
			}
		} catch (StaleElementReferenceException e) {
			log.error("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
			Assert.fail("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document - StaleElementReferenceException");
		} catch (NoSuchElementException e) {
			log.error("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document in time - " + waitTime + " - NoSuchElementException");
			Assert.fail("Text " + text + " to be entered in the field with " + locator
					+ " is not attached to the page document in time - " + waitTime + " - NoSuchElementException");
		} catch (Exception e) {
			log.error("Unable to enter '" + text + "' text in field with locator -" + locator + " - " + e);
			Assert.fail("Unable to enter '" + text + "' text in field with locator -" + locator);
		}
	}

	public void safeCheck(By locator, int... optionWaitTime) {
		int waitTime = 0;
		try {
			waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locator, waitTime)) {
				scrollIntoElementView(locator);
				WebElement checkBox = driver.findElement(locator);
				setHighlight(checkBox);
				if (checkBox.isSelected())
					log.info("CheckBox " + locator + "is already selected");
				else {
					checkBox.click();
					log.info("Checkbox with locator '" + locator + "'' " + "is selected");
				}
			} else {
				log.error("Element " + locator + " was not found in DOM in time - " + waitTime);
				Assert.fail("Element " + locator + " was not found in DOM in time - " + waitTime);
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locator + " is not attached to the page document in time - " + waitTime);
			Assert.fail("Element with " + locator + " is not attached to the page document in time - " + waitTime);
		} catch (NoSuchElementException e) {
			log.error(
					"Element " + locator + " was not found in DOM in time - " + waitTime + " - NoSuchElementException");
			Assert.fail(
					"Element " + locator + " was not found in DOM in time - " + waitTime + " - NoSuchElementException");
		} catch (Exception e) {
			log.error("Unable to check the checkbox with locator '" + locator + "' - " + e);
			Assert.fail("Element " + locator + " was not found in DOM in time");
		}
	}

	public String safeGetText(WebElement locator, int waitTime) {
		String sValue = null;
		try {
			if (locator.isDisplayed()) {
				sValue = locator.getText();
			} else {
				Assert.fail("Unable to find element " + locator + " in time - " + waitTime);
			}

		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locator
					+ "is not attached to the page document - StaleElementReferenceException");
			Assert.fail("Element with " + locator
					+ "is not attached to the page document - StaleElementReferenceException");
		} catch (NoSuchElementException e) {
			log.error(
					"Element " + locator + " was not found in DOM in time - " + waitTime + " - NoSuchElementException");
			Assert.fail(
					"Element " + locator + " was not found in DOM in time - " + waitTime + " - NoSuchElementException");
		} catch (Exception e) {
			log.error("Unable to get the text from the element " + locator + " - " + e);
			Assert.fail("Unable to find element " + locator);
		}
		return sValue;
	}

	public void scrollIntoElementView(By locator) {
		try {
			// WebElement element = ;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locator + "is not attached to the page document");
			Assert.fail("Element with " + locator + "is not attached to the page document");
		} catch (NoSuchElementException e) {
			log.error("Element " + locator + " was not found in DOM");
			Assert.fail("Element " + locator + " was not found in DOM");
		} catch (Exception e) {
			log.error("Unable to scroll the page to find " + locator + " - " + e);
			Assert.fail("Unable to scroll the page to find " + locator);
		}
	}

	public void setHighlight(WebElement element) {
		if (sys.getProperty("HighlightElements").equalsIgnoreCase("true")) {
			String attributevalue = "border:3px solid red;";
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String getattrib = element.getAttribute("style");
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				log.error("Sleep interrupted - ");
			}
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
		}
	}

	public void refresh() {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			log.error("Some exception occured while refreshing the page, exception message: " + e);
			Assert.fail("Some exception occured while refreshing the page,");
		}
	}

	public String getCurrentURL() {
		String sUrl = null;
		try {
			sUrl = driver.getCurrentUrl();
		} catch (Exception e) {
			log.error("Some exception occured while retriving current url, exception message: " + e);
			Assert.fail("Some exception occured while retriving current url");
		}

		return sUrl;
	}

	public String getTitle() {
		String sTitle = null;
		try {
			sTitle = driver.getTitle();
		} catch (Exception e) {
			log.error("Some exception occured while retriving title of the web page, exception message: " + e);
			Assert.fail("Some exception occured while retriving title of the web page");
		}
		return sTitle;
	}

	public List<WebElement> LocatorWebElements(By Locator) {
		List<WebElement> list = null;
		try {
			list = driver.findElements(Locator);
		} catch (Exception e) {
			log.error("Some exception occured while retriving web elements of a locator, exception message: " + e);
			Assert.fail("Some exception occured while retriving web elements of a locator");
		}
		return list;
	}

	public void navigateToURLandRetrivePageLoadTime(String url, int timeOutInSeconds) throws Exception {
		long start = System.currentTimeMillis();
		driver.get(url);
		waitForPageToLoad(timeOutInSeconds);
		long finish = System.currentTimeMillis();
		long totalTime = finish - start;
	}

}
