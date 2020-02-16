package generic.automation;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelIterator {
	private String flFile;
	private int intSheetNumber;
	private int intIndex = 0;
	private int intNoOfRow;
	private int intNoOfColumn;
	private XSSFSheet sheet;
	private XSSFWorkbook workbook;
	private FileInputStream excelFileIS;

	public ExcelIterator(String strExcelFile, int intSheetno, boolean ignoreHeaderRow) {
		this.flFile = strExcelFile;
		this.intSheetNumber = intSheetno;

		try {
			this.excelFileIS = new FileInputStream(this.flFile);
			this.workbook = new XSSFWorkbook(this.excelFileIS);
			this.excelFileIS.close();
			this.sheet = this.workbook.getSheetAt(this.intSheetNumber);
			this.intNoOfRow = this.sheet.getPhysicalNumberOfRows();
			if (ignoreHeaderRow) {
				++this.intIndex;
			}
		} catch (Exception var5) {
			var5.printStackTrace();
		}

	}

	public boolean isDone() {
		return this.intIndex < this.intNoOfRow;
	}

	public String getColumn(int clmNo) {
		String strCellvalue = "";
		Row row = null;
		Cell cell = null;
		row = this.sheet.getRow(this.intIndex);
		this.intNoOfColumn = row.getPhysicalNumberOfCells();

		try {
			cell = row.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				strCellvalue = "";
			} else {
				strCellvalue = cell.toString().trim();
			}

			strCellvalue = cell.toString();
			return strCellvalue;
		} catch (Exception var6) {
			var6.printStackTrace();
			return strCellvalue;
		}
	}

	public String getColumn(String columnHeader) {
		Row headerRow = this.sheet.getRow(0);
		Row testDataRow = this.sheet.getRow(this.getCurrentRowNumber());
		int clmNo = 0;
		String cellValue = "";

		try {
			do {
				String currentHeader = "";
				Cell headerCell = headerRow.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
				if (headerCell == null) {
					currentHeader = "";
				} else {
					currentHeader = headerCell.toString().trim();
				}

				if (currentHeader.equalsIgnoreCase(columnHeader)) {
					Cell testDataCell = testDataRow.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
					if (testDataCell == null) {
						cellValue = "";
					} else {
						cellValue = testDataCell.toString().trim();
					}
				}

				++clmNo;
			} while (clmNo < headerRow.getLastCellNum());
		} catch (Exception var9) {
			;
		}

		return cellValue;
	}

	public void next() {
		++this.intIndex;
	}

	public int getCurrentRowNumber() {
		return this.intIndex;
	}

	public int getNumberOfRows() {
		return this.intNoOfRow;
	}

	public int getNumberOfColumnForCurrentRow() {
		return this.intNoOfColumn;
	}
}
