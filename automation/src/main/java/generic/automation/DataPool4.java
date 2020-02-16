package generic.automation;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataPool4 {
	private FileInputStream excelFileIS;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Row headerRow;
	private Row testDataRow;

	public Hashtable<String, Hashtable<String, String>> loadTestData(String testDataFilePath) {
		Hashtable objDataProvider = null;
		try {
			this.excelFileIS = new FileInputStream(testDataFilePath);
			this.workbook = new XSSFWorkbook(this.excelFileIS);
			this.sheet = this.workbook.getSheetAt(0);
			this.headerRow = this.sheet.getRow(0);
			this.testDataRow = this.sheet.getRow(1);
			int lastRowNumber = this.sheet.getLastRowNum();
			int rowIndex = 0;
			String bufferCell = "";
			String strRMID = "";

			for (objDataProvider = new Hashtable(); rowIndex <= lastRowNumber; ++rowIndex) {
				bufferCell = this.getCellValue(this.sheet.getRow(rowIndex), 0);
				if (bufferCell.trim().equalsIgnoreCase("RMID")) {
					this.headerRow = this.sheet.getRow(rowIndex);
					this.testDataRow = this.sheet.getRow(rowIndex + 1);
					strRMID = this.getCellValue(this.testDataRow, 0);
					Hashtable<String, String> dataValueSet = new Hashtable();
					int clmNo = 0;
					while (true) {
						String header = "";
						String testData = "";
						header = this.getCellValue(this.headerRow, clmNo);
						testData = this.getCellValue(this.testDataRow, clmNo);
						if (!header.equals("")) {
							dataValueSet.put(header, testData);
						}
						++clmNo;
						if (clmNo >= this.headerRow.getLastCellNum()) {
							objDataProvider.put(strRMID, dataValueSet);
							break;
						}
					}
				}

				++rowIndex;
			}

			this.excelFileIS.close();
		} catch (Exception var11) {
			var11.printStackTrace();
		}

		return objDataProvider;
	}

	public Object[][] loadTestData(String testCaseID, String testDataFilePath) {
		ArrayList<Hashtable<String, String>> hashTableList = new ArrayList();
		Object[][] objDataProvider = null;
//		int headerRowCount = 0;
//		int lastRowNumber = 0;
		String bufferCell = "";

		try {
			this.excelFileIS = new FileInputStream(testDataFilePath);
			this.workbook = new XSSFWorkbook(this.excelFileIS);
			this.sheet = this.workbook.getSheetAt(0);
			this.headerRow = this.sheet.getRow(0);
			this.testDataRow = this.sheet.getRow(1);
			int lastRowNumber = this.sheet.getLastRowNum();

			label132 : for (int rowIndex = 0; rowIndex <= lastRowNumber; ++rowIndex) {
				String cellData = this.getCellValue(this.sheet.getRow(rowIndex), 0);
				if (cellData.equalsIgnoreCase(testCaseID)) {
					int headerRowCount = rowIndex - 1;
					bufferCell = this.getCellValue(this.sheet.getRow(rowIndex), 0);

					while (true) {
						if (rowIndex > lastRowNumber || bufferCell.equalsIgnoreCase("RM ID")) {
							break label132;
						}

						if (bufferCell.equalsIgnoreCase(testCaseID)) {
							Logger logger = Logger.getLogger(Utilities.class);
							this.headerRow = this.sheet.getRow(headerRowCount);
							this.testDataRow = this.sheet.getRow(rowIndex);
							Hashtable<String, String> dataValueSet = new Hashtable();
							int clmNo = 0;

							while (true) {
								String header = "";
								String testData = "";
								header = this.getCellValue(this.headerRow, clmNo);
								testData = this.getCellValue(this.testDataRow, clmNo);
								if (!header.equals("")) {
									dataValueSet.put(header, testData);
								}

								++clmNo;
								if (clmNo >= this.headerRow.getLastCellNum()) {
									hashTableList.add(dataValueSet);
									dataValueSet = null;
									boolean var25 = false;
									break;
								}
							}
						}

						++rowIndex;
						if (rowIndex > lastRowNumber) {
							bufferCell = "";
						} else {
							bufferCell = this.getCellValue(this.sheet.getRow(rowIndex), 0);
						}
					}
				}
			}

			objDataProvider = new Object[hashTableList.size()][2];
			int rowCount = 0;

			for (Iterator var24 = hashTableList.iterator(); var24.hasNext(); ++rowCount) {
				Hashtable<String, String> hashTable = (Hashtable) var24.next();
				objDataProvider[rowCount][0] = "Run" + (rowCount + 1);
				objDataProvider[rowCount][1] = hashTable;
			}

			this.excelFileIS.close();
		} catch (Exception var18) {
			var18.printStackTrace();
		} finally {
			hashTableList = null;
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