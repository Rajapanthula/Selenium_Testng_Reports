/*************************************** PURPOSE **********************************

 - This class contains all Generic methods which are not related to specific application
*/

package com.Reports;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.datamanager.ConfigManager;

public class UtilityMethods {

	static ConfigManager sys = new ConfigManager();
	static ConfigManager comp = new ConfigManager("Cmp");
	static Logger log = Logger.getLogger(UtilityMethods.class);
	static Thread thread;
	// ip address with regular expression
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static String machineName() {
		String sComputername = null;
		try {
			sComputername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log.error("Unable to get the hostname..." + e.getCause());
		}
		return sComputername;
	}

	public static String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	public static String getCurrentDateTime(String strFormate) {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(strFormate);
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	public static String getCurrentDateTime(int backDay) {

		Calendar currentDate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("MM/dd/YYY");
		currentDate.add(Calendar.DATE, backDay);
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;

	}

	public static int getRandomNumber() {
		Random rand = new Random();
		int numNoRange = rand.nextInt();
		return numNoRange;
	}

	public static int getRandomNumber(int max) {

		Random r = new Random();
		int randomNum = r.nextInt((max + 1) - 1) + 1;
		return randomNum;
	}

	public static int getOneinTwoRandomly(int a, int b) {
		Random r = new Random();
		int c = r.nextBoolean() ? a : b;
		return c;
	}

	public static long getRandomNumberMillis() {
		return System.currentTimeMillis();
	}

	public static int getWaitTime(String WaitType) {
		int iSecondsToWait = 15;
		String wait;
		if (WaitType != null && !WaitType.equalsIgnoreCase("")) {
			wait = sys.getProperty(WaitType);
		} else {
			log.error("WaitType cannot be empty...defaulting to MEDIUM WAIT");
			wait = sys.getProperty("WAIT.MEDIUM");
		}
		try {
			iSecondsToWait = Integer.parseInt(wait);
		} catch (NumberFormatException e) {
			log.error("Please check the config file. Wait values must be a number...defaulting to 15 seconds");
		}
		return iSecondsToWait;
	}

	public static void verifyPopUp() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			log.error("balloon popup thread Interrupted" + e.getStackTrace());
		}
	}

	public static String getFileSeperator() {
		return System.getProperty("file.separator");
	}

	public static void clearTasks() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer32.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Thread.sleep(4000);
			log.info("Processes successfully closed");
		} catch (IOException e) {
			log.info("Processes already closed/Warning: Permissions are not there to close");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String get4DRandomNumber() {
		String millis = String.valueOf(System.currentTimeMillis() % 100000);
		return millis;

	}

	public static String get5DRandomNumber() {
		String millis = String.valueOf(System.currentTimeMillis() % 1000000);
		return millis;

	}

	public static void disableWarning() {
		System.err.close();
		System.setErr(System.out);
	}
}
