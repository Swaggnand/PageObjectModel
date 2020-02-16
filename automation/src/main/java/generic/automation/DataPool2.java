package generic.automation;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataPool2 {
	private FileInputStream excelFile;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Row headerRow;
	private Row testDataRow;
	private XSSFCell cell;

	public Hashtable<Object,Object> loadTestData(String testDataFilePath) {
		Hashtable<Object, Object> objDataProvider = null;
		System.out.println("1");
		try {
			excelFile = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(excelFile);
			sheet = workbook.getSheetAt(0);
			int lastRowNumber = sheet.getLastRowNum();
			int rowIndex = 0;
			String bufferCell = "";
			String strRMIDVal = "";
			objDataProvider = new Hashtable<Object, Object>();
			for (rowIndex = 0; rowIndex <= lastRowNumber; rowIndex++) {
				headerRow = sheet.getRow(rowIndex);
				testDataRow = sheet.getRow(rowIndex + 1);
				bufferCell = headerRow.getCell(0, Row.CREATE_NULL_AS_BLANK).toString();
				if (bufferCell.trim().equalsIgnoreCase("RMID")) {
					headerRow = sheet.getRow(rowIndex);
					testDataRow = sheet.getRow(rowIndex + 1);
					strRMIDVal = getCellValue(testDataRow, 0);
					Hashtable<String, String> dataValueSet = new Hashtable<String, String>();
					int clmCount = headerRow.getLastCellNum();
					for (int clmNo = 0; clmNo <= clmCount; clmNo++) {
						String header = "";
						String testData = "";
						header = headerRow.getCell(clmNo, Row.CREATE_NULL_AS_BLANK).toString();
						testData = testDataRow.getCell(clmNo, Row.CREATE_NULL_AS_BLANK).toString();
						if (!header.equals("")) {
							dataValueSet.put(header, testData);
						}
						if (clmNo >= clmCount) {
							objDataProvider.put(strRMIDVal, dataValueSet);
//							break;
						}
					}
				}
				
			}
			excelFile.close();
		} catch (Exception var11) {
			var11.printStackTrace();
		}
		return objDataProvider;
	}
	
	private String getCellValue(Row testDataRow, int columnNumber) {
		if (testDataRow == null) {
			return "";
		} else {
			Cell testDataCell = testDataRow.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
			return testDataCell == null ? "" : testDataCell.toString().trim();
		}
	}
}