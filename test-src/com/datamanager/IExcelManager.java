package com.datamanager;

import java.util.List;

public interface IExcelManager {
	public List<String> getExcelColumnData(String sheetName, int columnIndex);

	public List<String> getExcelColumnData(String sheetName, String columnName);

	public String getExcelCellData(String sheetName, int columnIndex, int rowIndex);

	public String getExcelCellData(String sheetName, String columnName, int rowIndex);

	public String[][] getExcelSheetData(String sheetName);

	public int getRowCount(String sheetName);

	public int getColumnCount(String SheetName);

	public void addExcelRowData(String sheetName, String[] rowData);

	void addExcelRowData(String sheetName, List<String> rowData);
}
