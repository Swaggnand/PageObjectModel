package generic.automation;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;

public class CustomReporterHelper extends Pojo {
	String strExcelFilePath = "";
	private XSSFWorkbook workbook;
	private XSSFSheet summarySheet;
	private XSSFRow summaryCurrentRow;
	int intSummarySheetRowCounter = 0;
	private XSSFSheet testResultSheet;
	private XSSFRow testResultSheetCurrentRow;
	private XSSFRow welComeRow;
	int intTestResultRowCounter = 0;
	int intSkipSheetRowCounter = 0;
	String server = "";
	String version = "";
	String developer = "Undefined";
	String customError = "";
	String appFailure = "";
	String seleniumFailure = "";
	String groups = "Undefined";
	String description = "Undefined";

	public void startExcelReport(ITestResult result, String status) {
		try {
			String moduleName = result.getTestContext().getName();
			String completeTestClassPath = result.getTestClass().getName();
			String packageName = completeTestClassPath.substring(0, completeTestClassPath.lastIndexOf("."));
			String testClassName = result.getTestClass().getRealClass().getSimpleName();
			String methodName = result.getMethod().getMethodName();
			String[] temp = methodName.split("_");
			String RMID = temp[1];
			double executionTime = (double) (result.getEndMillis() - result.getStartMillis()) / 1000.0D;
			Pojo objPojo = (Pojo) result.getInstance();
			this.server = objPojo.getServer();
			this.version = objPojo.getVersion();
			this.groups = objPojo.getGroupName();
			this.description = objPojo.getDescription();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String reportDate = dateFormat.format(date).toString();
			this.strExcelFilePath = System.getProperty("user.dir") + "/target/custom-reports/ExcelReports_ver-"
					+ objPojo.getVersion() + "_env-" + objPojo.getServer().replace(":", "-") + "_" + 

reportDate
					+ ".xlsx";
			File reportFile = new File(this.strExcelFilePath);
			String exception;
			if (!reportFile.exists()) {
				(new File(System.getProperty("user.dir") + "/target/custom-reports")).mkdir();
				reportFile.createNewFile();
				exception = System.getProperty("user.dir")
						+ 

"/src/main/resources/externalResources/Custom_Design_Files/Excel/reportTemplet.xlsx";
				this.workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(exception));
				this.summarySheet = this.workbook.getSheet("TestSummary");
				this.intSummarySheetRowCounter = 0;
				this.summaryCurrentRow = this.summarySheet.getRow(this.intSummarySheetRowCounter);
				this.summarySheet.setPrintGridlines(false);
				this.summarySheet.setDisplayGridlines(false);
				this.summaryCurrentRow.setHeightInPoints(35.0F);
				this.intSummarySheetRowCounter = 1;
				this.summaryCurrentRow = this.summarySheet.getRow(this.intSummarySheetRowCounter);
				String i_logo_path = System.getProperty("user.dir") + 

"/src/test/resources/testData/AllImageDoc/i.JPG";
				String ecw_logo_path = System.getProperty("user.dir")
						+ "/src/test/resources/testData/AllImageDoc/ecw.JPG";
				InputStream inputStream = new FileInputStream(ecw_logo_path);
				InputStream inputStream2 = new FileInputStream(i_logo_path);
				byte[] bytes = IOUtils.toByteArray(inputStream);
				byte[] bytes2 = IOUtils.toByteArray(inputStream2);
				int pictureIdx = this.workbook.addPicture(bytes, 6);
				int pictureIdx2 = this.workbook.addPicture(bytes2, 6);
				inputStream.close();
				inputStream2.close();
				CreationHelper helper = this.workbook.getCreationHelper();
				CreationHelper helper2 = this.workbook.getCreationHelper();
				Drawing drawing = this.summarySheet.createDrawingPatriarch();
				Drawing drawing2 = this.summarySheet.createDrawingPatriarch();
				ClientAnchor anchor = helper.createClientAnchor();
				ClientAnchor anchor2 = helper2.createClientAnchor();
				anchor.setCol1(5);
				anchor.setRow1(0);
				anchor2.setCol1(10);
				anchor2.setRow1(0);
				Picture pict = drawing.createPicture(anchor, pictureIdx);
				Picture pict2 = drawing2.createPicture(anchor2, pictureIdx2);
				pict.resize();
				pict2.resize();
				this.testResultSheet = this.workbook.createSheet("TestResult");
				this.welComeRow = this.testResultSheet.createRow(0);
				this.welcomeRow(this.welComeRow);
				this.testResultSheetCurrentRow = this.testResultSheet.createRow(1);
				this.createHeaderRow(this.testResultSheetCurrentRow);
			} else {
				this.workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream

(this.strExcelFilePath));
				this.summarySheet = this.workbook.getSheet("TestSummary");
				this.intSummarySheetRowCounter = 1;
				this.summaryCurrentRow = this.summarySheet.getRow(this.intSummarySheetRowCounter);
				this.testResultSheet = this.workbook.getSheet("TestResult");
			}

			this.workbook.setSheetOrder("TestResult", 0);
			this.workbook.setSheetOrder("TestSummary", 1);
			exception = "";
			this.customError = "";
			if (status.equalsIgnoreCase("pass") || status.equalsIgnoreCase("fail")
					|| status.equalsIgnoreCase("skipped")) {
				this.intTestResultRowCounter = this.testResultSheet.getLastRowNum() + 1;
				this.testResultSheetCurrentRow = this.testResultSheet.createRow(this.intTestResultRowCounter);
				if (status.equalsIgnoreCase("fail")) {
					exception = result.getThrowable().getClass().getName() + ":" + result.getThrowable

().getMessage();
					this.customError = objPojo.getCustomException();
					System.out.println("Exception for +" + RMID + " - " + exception);
					System.out.println("Exception for M +" + RMID + " - " + result.getThrowable().getMessage

());
				}

				if (status.equalsIgnoreCase("skipped")) {
					exception = "Pre-Condition Failed";
				}

				this.developer = objPojo.getResourceName();
				this.addExecutionResultInTestResultSheet(objPojo.getServer(), objPojo.getVersion(), RMID, 

packageName,
						testClassName, methodName, exception, String.valueOf(executionTime), status, 

moduleName,
						this.developer, this.customError, objPojo.getGroupName(), this.description);
			}

			this.updateSummarySheet(status);
			this.endExcelReport();
		} catch (Exception var34) {
			var34.printStackTrace();
		}

	}

	public void createHeaderRow(XSSFRow headerRow) {
		this.createHeaderCell(headerRow, 0, "Sr. No.");
		this.createHeaderCell(headerRow, 1, "RMID");
		this.createHeaderCell(headerRow, 2, "Result");
		this.createHeaderCell(headerRow, 3, "App Failure Type");
		this.createHeaderCell(headerRow, 5, "Selenium Failure");
		this.createHeaderCell(headerRow, 6, "Selenium Exception");
		this.createHeaderCell(headerRow, 4, "Application Exception");
		this.createHeaderCell(headerRow, 7, "Module Name");
		this.createHeaderCell(headerRow, 8, "Execution Time");
		this.createHeaderCell(headerRow, 9, "Module");
		this.createHeaderCell(headerRow, 10, "Class Name");
		this.createHeaderCell(headerRow, 11, "Method Name");
		this.createHeaderCell(headerRow, 12, "Developer");
		this.createHeaderCell(headerRow, 13, "Time Stamp");
		this.createHeaderCell(headerRow, 14, "Server");
		this.createHeaderCell(headerRow, 15, "Version");
		this.createHeaderCell(headerRow, 16, "Groups");
		this.createHeaderCell(headerRow, 17, "Description");
	}

	public void autoSetColumnWidth() {
		this.testResultSheet.autoSizeColumn(0);
		this.testResultSheet.autoSizeColumn(1);
		this.testResultSheet.autoSizeColumn(2);
		this.testResultSheet.setColumnWidth(3, 3840);
		this.testResultSheet.setColumnWidth(4, 3840);
		this.testResultSheet.setColumnWidth(5, 3840);
		this.testResultSheet.setColumnWidth(6, 3584);
		this.testResultSheet.setColumnWidth(7, 3584);
		this.testResultSheet.setColumnWidth(8, 3584);
		this.testResultSheet.setColumnWidth(9, 3584);
		this.testResultSheet.setColumnWidth(10, 3584);
		this.testResultSheet.setColumnWidth(11, 3584);
		this.testResultSheet.setColumnWidth(12, 3584);
		this.testResultSheet.setColumnWidth(13, 3584);
		this.testResultSheet.setColumnWidth(14, 3584);
		this.testResultSheet.setColumnWidth(15, 3584);
		this.testResultSheet.setColumnWidth(16, 3584);
		this.testResultSheet.setColumnWidth(17, 3584);
	}

	public void welcomeRow(XSSFRow headerRow) {
		this.createWelcomeHeaderCell(headerRow, 0, "Server ");
		this.createWelcomeHeaderCellWithoutFormat(headerRow, 1, this.server);
		this.createWelcomeHeaderCell(headerRow, 2, "Version ");
		this.createWelcomeHeaderCellWithoutFormat(headerRow, 3, this.version);
	}

	public void addExecutionResultInTestResultSheet(String server, String version, String RMID, String module,
			String className, String methodName, String exception, String executionTime, String result,
			String moduleName, String developer, String customError, String groups, String description) {
		this.appFailure = "";
		this.seleniumFailure = "";
		if (result.toLowerCase().equals("fail")) {
			if (!exception.contains("org.openqa.selenium.ElementNotVisible")
					&& !exception.contains("org.openqa.selenium.InvalidElementStateException")) {
				if (exception.contains("org.openqa.selenium.TimeoutException")) {
					this.appFailure = "fail - Application Freeze";
					customError = "Timeout & NoSuchElement  Exception";
					this.seleniumFailure = "";
					exception = "";
				} else if (exception.contains("org.openqa.selenium.StaleElementReferenceException")) {
					this.appFailure = "fail - Application Freeze";
					customError = "StaleElementReferenceException";
					this.seleniumFailure = "";
					exception = "";
				} else if (exception.contains("org.openqa.selenium.UnhandledAlertException")) {
					this.appFailure = "fail - Application Side Issue";
					customError = "UnhandledAlertException";
					this.seleniumFailure = "";
					exception = "";
				} else if (exception.contains("NoAlertPresentException")) {
					this.appFailure = "fail - Application Side Issue";
					customError = "NoAlertPresentException";
					this.seleniumFailure = "";
					exception = "";
				} else if (exception.contains("java.lang.AssertionError")) {
					this.appFailure = "fail - Assertion Error";
					this.seleniumFailure = "";
					exception = "";
					if (customError.equals("")) {
						customError = "Verification Failed";
					}
				} else if (!exception.contains("NoSuchElementException") && !exception.contains

("ElementNotFound")) {
					if (!exception.contains("java.lang.ArrayIndexOutOfBoundsException")
							&& !exception.contains("java.lang.ClassCastException")
							&& !exception.contains("java.lang.NullPointerException")
							&& !exception.contains("java.lang.NumberFormatException")) {
						if (exception.contains("WebDriverException")) {
							this.seleniumFailure = "fail - WebDriver Exception";
							this.appFailure = "";
							customError = "";
						} else if (exception.contains("java.lang.RuntimeException")
								|| exception.contains

("org.openqa.selenium.NoSuchWindowException")) {
							this.seleniumFailure = "fail - Tool Limitations";
							this.appFailure = "";
							customError = "";
						}
					} else {
						this.seleniumFailure = "fail - Developer Side Issue";
						this.appFailure = "";
						if (!customError.contains("File") && !customError.contains("File")) {
							customError = "";
						}
					}
				} else {
					this.appFailure = "fail - UI Change/Element Changed";
					customError = "NoSuchElement Exception";
					this.seleniumFailure = "";
					exception = "";
				}
			} else {
				this.appFailure = "fail - Application Freeze";
				customError = "ElementNotVisibleException";
				this.seleniumFailure = "";
				exception = "";
			}
		}

		this.createSheetCellRightAlliged(this.testResultSheetCurrentRow, 0,
				String.valueOf(this.intTestResultRowCounter));
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 1, RMID);
		this.createResultCell(this.testResultSheetCurrentRow, 2, result);
		this.createSheetCell(this.testResultSheetCurrentRow, 3, this.appFailure);
		this.createSheetCell(this.testResultSheetCurrentRow, 5, this.seleniumFailure);
		this.createSheetCell(this.testResultSheetCurrentRow, 6, exception);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 4, customError);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 7, moduleName);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 8, executionTime);
		this.createSheetCell(this.testResultSheetCurrentRow, 9, module);
		this.createSheetCell(this.testResultSheetCurrentRow, 10, className);
		this.createSheetCell(this.testResultSheetCurrentRow, 11, methodName);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 12, developer);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 13, this.getCurrentDateTime());
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 14, server);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 15, version);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 16, groups);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 17, description);
	}

	public void updateSummarySheet(String status) {
		int skippedCount;
		if (status.equalsIgnoreCase("pass")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(0).getNumericCellValue() + 1;
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 0, String.valueOf(skippedCount));
		} else if (status.equalsIgnoreCase("fail")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(1).getNumericCellValue() + 1;
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 1, String.valueOf(skippedCount));
		} else if (status.equalsIgnoreCase("skipped")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(2).getNumericCellValue() + 1;
			System.out.println(" I am writing and count is " + skippedCount);
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 2, String.valueOf(skippedCount));
		}

		skippedCount = (int) this.summaryCurrentRow.getCell(3).getNumericCellValue() + 1;
		this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 3, String.valueOf(skippedCount));
	}

	public void endExcelReport() throws IOException {
		this.autoSetColumnWidth();
		FileOutputStream fileOutputStream = new FileOutputStream(this.strExcelFilePath);
		this.workbook.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	public void createSheetCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleLeftAlliged());
	}

	public void createSheetCellCenterAlliged(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleCenterAlliged());
	}

	public void createSheetCellCenterAlligedNumeric(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue((double) Integer.parseInt(value));
		cell.setCellStyle(this.getCellStyleCenterAlliged());
		cell.getSheet().setPrintGridlines(false);
		cell.getSheet().setDisplayGridlines(false);
	}

	public void createSheetCellRightAlliged(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleRightAlliged());
	}

	public void createSheetPassStepCell(XSSFRow row, int cellNumber) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("PASS"));
		cell.setCellStyle(this.getPassCellStyle());
	}

	public void createResultCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		System.out.println("Result ****************:     " + value.toLowerCase().trim());
		String var5;
		switch ((var5 = value.toLowerCase().trim()).hashCode()) {
			case -1988746795 :
				if (var5.equals("fail - Application Side Issue")) {
					cell.setCellStyle(this.getFailCellStyle());
				}
				break;
			case -1643861444 :
				if (var5.equals("fail - Application Freeze")) {
					cell.setCellStyle(this.getFailCellStyle());
				}
				break;
			case 3135262 :
				if (var5.equals("fail")) {
					cell.setCellStyle(this.getFailCellStyle());
				}
				break;
			case 3433489 :
				if (var5.equals("pass")) {
					cell.setCellStyle(this.getPassCellStyle());
				}
				break;
			case 154432742 :
				if (var5.equals("fail - Tool Limitations")) {
					cell.setCellStyle(this.getSeleniumSideFailCellStyle());
				}
				break;
			case 1534758069 :
				if (var5.equals("fail - Assertion Error")) {
					cell.setCellStyle(this.getFailCellStyle());
				}
				break;
			case 1872143336 :
				if (var5.equals("fail - UI Change/Element Changed")) {
					cell.setCellStyle(this.getFailCellStyle());
				}
				break;
			case 1879267419 :
				if (var5.equals("fail - Developer Side Issue")) {
					cell.setCellStyle(this.getSeleniumSideFailCellStyle());
				}
				break;
			case 2147444528 :
				if (var5.equals("skipped")) {
					cell.setCellStyle(this.getSkippedCellStyle());
				}
		}

	}

	public void createHeaderCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getHeaderCellStyle());
	}

	public void createWelcomeHeaderCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getWelcomeCellStyle());
	}

	public void createWelcomeHeaderCellWithoutFormat(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getWelcomeCellStyleWOformat());
	}

	private XSSFCellStyle getCellStyleLeftAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getCellStyleCenterAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		return cellStyle;
	}

	private XSSFCellStyle getCellStyleRightAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 3);
		return cellStyle;
	}

	private XSSFCellStyle getPassCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.GREEN);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getFailCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.RED);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getSeleniumSideFailCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.ORANGE);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getSkippedCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment((short) 2);
		XSSFColor myColor = new XSSFColor(Color.LIGHT_GRAY);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getHeaderCellStyle() {
		XSSFFont headerFont = this.workbook.createFont();
		headerFont.setBoldweight((short) 700);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(headerFont);
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setFillForegroundColor((short) 22);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getWelcomeCellStyle() {
		XSSFFont headerFont = this.workbook.createFont();
		headerFont.setBoldweight((short) 700);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(headerFont);
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setFillPattern((short) 1);
		XSSFColor myColor = new XSSFColor(Color.LIGHT_GRAY);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private XSSFCellStyle getWelcomeCellStyleWOformat() {
		XSSFFont headerFont = this.workbook.createFont();
		headerFont.setBoldweight((short) 700);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(headerFont);
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setFillPattern((short) 1);
		XSSFColor myColor = new XSSFColor(Color.WHITE);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	public void createSummaryLinkCell(XSSFRow row, int cellNumber, String value, String nevigationSheet) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		CreationHelper createHelper = this.workbook.getCreationHelper();
		Hyperlink link = createHelper.createHyperlink(2);
		link.setAddress("'" + nevigationSheet + "'!A1");
		cell.setHyperlink(link);
		cell.setCellStyle(this.getHlinkCellStyle());
	}

	private XSSFCellStyle getHlinkCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		Font hlink_font = this.workbook.createFont();
		hlink_font.setUnderline((byte) 1);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_font.setBoldweight((short) 700);
		hlink_font.setFontName("Arial");
		cellStyle.setFont(hlink_font);
		cellStyle.setAlignment((short) 2);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setFillForegroundColor((short) 22);
		cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	private String getCurrentDateTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		return sdfDate.format(now);
	}

	public void createXMLTest(ITestResult result, String status) {
		String line2 = "";
		Pojo objPojo = (Pojo) result.getInstance();
		String exception = "";
		String completeTestClassPath = result.getTestClass().getName();
		String methodName = result.getMethod().getMethodName();
		BufferedWriter bw = null;
		FileWriter fw = null;
		String myFilePath = System.getProperty("user.dir") + "\\target\\custom-reports" + "createXMLTest.txt";

		try {
			fw = new FileWriter(myFilePath, true);
			bw = new BufferedWriter(fw);
			String className2 = "<class name = \"" + completeTestClassPath + "\"" + "> \r\n" + "<methods> \r\n"
					+ "<include name=" + "\"" + methodName + "\"" + "/> " + System.lineSeparator() + 

"</methods> \r\n"
					+ "</class>";
			bw.append(className2);
			bw.flush();
			bw.close();
			System.out.println("createXMLTest Done");
		} catch (IOException var20) {
			var20.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}

				if (fw != null) {
					fw.close();
				}
			} catch (IOException var19) {
				var19.printStackTrace();
			}

		}

	}

}