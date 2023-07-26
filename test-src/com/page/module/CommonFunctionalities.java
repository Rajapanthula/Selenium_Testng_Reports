package com.page.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.datamanager.ExcelManagerFillo;
import com.selenium.SafeActions;

public abstract class CommonFunctionalities extends SafeActions {

	private WebDriver driver;
	Logger log = Logger.getLogger(getClass());
	String studentCoursePointsFromStudentProfile;
	String studentAcheivementsFromStudentProfile;
	String profilePoints[] = new String[2];
	static ExcelManagerFillo emf;

	public CommonFunctionalities(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public static Map<String, String> getDataFromShet(List<String> titles, String file, String sheet) {
		emf = new ExcelManagerFillo(file);
		Map<String, String> map = new HashMap<String, String>();
		for (String string : titles) {
			map.put(string, emf.getExcelColumnData(sheet, string).get(0));
		}
		emf.recordset.close();
		emf.con.close();
		return map;
	}

	public static ArrayList<String> getUnitAndActivityDetails(String fileName, String sheetName, String TestName) {
		ArrayList<String> all_product_list = new ArrayList<>();
		try {
			Fillo fillo = new Fillo();
			Connection connection;
			connection = fillo.getConnection(fileName);
			String strQuery = "Select UnitSelector,ActivityType from TestCases where TestID='" + TestName + "'";
			Recordset recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				all_product_list.add(recordset.getField("UnitSelector"));
				all_product_list.add(recordset.getField("ActivityType"));
			}
			connection.close();
		} catch (FilloException e) {
			e.printStackTrace();
		}
		return all_product_list;
	}

	public void NavigateToURL(String URL) throws Exception {

		navigateToURLandRetrivePageLoadTime(URL, LONGWAIT);
		waitForPageToLoad();
	}
}
