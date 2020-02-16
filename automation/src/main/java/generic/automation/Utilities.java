package generic.automation;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.util.Log;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * @ScriptName : Utilities
 * @Description : This class contains utilities function
 * @Author :Automation Tester
 */

public class Utilities {
	private Pojo objPojo;

	public Utilities(Pojo pojo) {
		this.objPojo = pojo;
	}

	// added by Pranay on 3 Jan 2017
	String current_RMID = "";

	// added by Pranay on 3 Jan 2017
	public String getCurrent_RMID() {
		return current_RMID;
	}

	// added by Pranay on 3 Jan 2017
	public void setCurrent_RMID(String current_RMID) {
		this.current_RMID = current_RMID;
	}

	/**
	 * @Method : getRequiredDate
	 * @Description : This method will give require date
	 * @param :
	 *            incrfementDateByDays Number by which user want increase date
	 * @param :
	 *            sExpectedDateFormat - User expected date format eg. 9 april
	 *            2014 --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 * @param :
	 *            timeZoneId - Time Zone
	 * @author : Automation Tester
	 */
	public String getRequiredDate(String incrementDays, String expectedDateFormat, String timeZoneId) {
		try {
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if (timeZoneId != null && !timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			if (incrementDays != null && !incrementDays.equals(""))
				calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(incrementDays));
			Date date = calendar.getTime();
			String formattedDate = dateFormat.format(date);
			return formattedDate;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : getRequiredTime
	 * @Description : This method will give require time
	 * @param :
	 *            incrementMin - increment in time by minute
	 * @param :
	 *            sExpectedDateFormat - User expected date format eg. 9 april
	 *            2014 --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 * @param :
	 *            timeZoneId - Time Zone
	 * @author : Automation Tester
	 */
	public String getRequiredTime(String incrementMin, String expectedDateFormat, String timeZoneId) {
		try {
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if (timeZoneId != null && !timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			if (incrementMin != null && !incrementMin.equals(""))
				calendar.add(Calendar.MINUTE, Integer.parseInt(incrementMin));
			Date time = calendar.getTime();
			String formattedTime = dateFormat.format(time);
			return formattedTime;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/*
	 * 
	 * NOTE : If you want future date : Pass No of Future day 's value as
	 * positive int i.e. 5 If you want Back date : Pass No of Future day 's
	 * value as Negative . i.e. -5
	 * 
	 * @Method : getFutureDateInSpecifiedFormat
	 * 
	 * @Description : This method takes parameter of your required DateFormat
	 * Type Like: dd-mm-YYYY OR DD.MM.YYYY and in return it will give you Future
	 * date in specified date format
	 * 
	 * @param1 : dateFormat like : dd-MM-YYYY
	 * 
	 * @param2 : No Of Future Day from now
	 * 
	 * @author : Pranay
	 * 
	 * i.e. : if you call getFutureDateInSpecifiedFormat("DD-MM-YYYY",5) then it
	 * will return future date (current date + next 5 days)
	 * 
	 * NOTE : If you want future date : Pass No of Future day 's value as
	 * positive int i.e. 5 If you want Back date : Pass No of Future day 's
	 * value as Negative . i.e. -5
	 * 
	 */
	public String getFutureOrBackDateInSpecifiedFormat(String dateFormat, int NoOfFutureDay) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, NoOfFutureDay);
		String futureDate = new SimpleDateFormat(dateFormat).format(c.getTime()).toString();
		return futureDate;
	}

	/**
	 * @Method : getRequiredTime
	 * @Description : This method will give require time
	 * @param :
	 *            incrementMin - increment in time by minute
	 * @param :
	 *            sExpectedDateFormat - User expected date format eg. 9 april
	 *            2014 --- dd/MM/yyyy -> 09/04/2015, dd-MM-yyyy -> 09-04-2015
	 * @param :
	 *            timeZoneId - Time Zone
	 * @author : Automation Tester
	 */
	public String getCurrentTimeZone() {
		try {
			Calendar calendar = Calendar.getInstance();
			long milliDiff = calendar.get(Calendar.ZONE_OFFSET);
			// Got local offset, now loop through available timezone id(s).
			String[] ids = TimeZone.getAvailableIDs();
			String timeZone = null;
			for (String id : ids) {
				TimeZone tz = TimeZone.getTimeZone(id);
				if (tz.getRawOffset() == milliDiff) {
					// Found a match.
					timeZone = id;
					break;
				}
			}
			return timeZone;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : getFormatedDate
	 * @Description : This method will converted date into excepted date format
	 * @author : Automation Tester
	 */
	public String getFormatedDate(String date, String originalDateFormat, String expectedDateFormat) {
		try {
			DateFormat inputFormatter = new SimpleDateFormat(originalDateFormat);
			Date originalDate = inputFormatter.parse(date);
			DateFormat outputFormatter = new SimpleDateFormat(expectedDateFormat);
			String expectedDate = outputFormatter.format(originalDate);
			return expectedDate;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	public String getRequiredTimeForWorkingHour(String incrementMin, String expectedDateFormat) {
		try {
			LocalTime startOfficeHours = new LocalTime(7, 0);
			LocalTime endOfficeHours = new LocalTime(20, 0);
			Interval officeHoursToday = new Interval(startOfficeHours.toDateTimeToday(),
					endOfficeHours.toDateTimeToday());
			DateTime currentTime = new DateTime();
			DateTimeFormatter outputFormat = new DateTimeFormatterBuilder().appendPattern(expectedDateFormat)
					.toFormatter();
			if (officeHoursToday.contains(currentTime))
				return currentTime.toString(outputFormat);
			else
				return "08:00 am";
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : copyFileUsingStream
	 * @Description : copy files
	 * @param :
	 *            Soure file path
	 * @param :
	 *            destination file path
	 * @author :Automation Tester
	 */
	public void copyFileUsingStream(String sourceFilePath, String destinationFilePath) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(new File(sourceFilePath));
			outputStream = new FileOutputStream(new File(destinationFilePath));
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
		} catch (Exception exception) {
			exception.printStackTrace();

		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}
		}
	}

	/**
	 * @Method: loadDynamicDataPool
	 * @Description: this method load the data pool for the test script.
	 * @author Automation Tester @CreationDate: @ModifiedDate:
	 */
	@SuppressWarnings("resource")
	public Hashtable<String, String> loadDynamicDataPool(String strFilePath, int intRowNum) {
		try {
			// create full path for the test data file
			String testDataFilePath = System.getProperty("user.dir") + strFilePath + ".xlsx";
			Hashtable<String, String> dataPoolHashTable = new Hashtable<String, String>();

			FileInputStream excelFileIS;
			XSSFWorkbook workbook;
			XSSFSheet sheet;
			org.apache.poi.ss.usermodel.Row headerRow;
			org.apache.poi.ss.usermodel.Row testDataRow;
			int clmNo = 0;

			excelFileIS = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(excelFileIS);
			sheet = workbook.getSheetAt(0);
			headerRow = sheet.getRow(intRowNum - 2);
			testDataRow = sheet.getRow((intRowNum - 1));

			do {
				String header = "", testData = "";
				org.apache.poi.ss.usermodel.Cell headerCell = headerRow.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
				if (headerCell == null)
					header = "";
				else
					header = headerCell.toString().trim();

				org.apache.poi.ss.usermodel.Cell testDataCell = testDataRow.getCell(clmNo, 

Row.RETURN_BLANK_AS_NULL);
				if (testDataCell == null)
					testData = "";
				else
					testData = testDataCell.toString().trim();

				if (!header.equals(""))
					dataPoolHashTable.put(header, testData);

				clmNo++;
			} while (clmNo < headerRow.getLastCellNum());
			excelFileIS.close();

			return dataPoolHashTable;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param :
	 *            Step - Step description, resultLog - result log pass/fail
	 *            (true/false), includeMobile - result for mobile(true/false)
	 * @author :Automation Tester
	 */
	@Step("{0}")
	public void logReporter(String step, boolean resultLog) {
		String strLog = step;
		this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param :
	 *            Step - Step description, inputValue - Input value, resultLog -
	 *            result log pass/fail (true/false), includeMobile - result for
	 *            mobile(true/false)
	 * @author : Automation Tester
	 */
	@Step("{0} - {1}")
	public void logReporter(String step, String inputValue, boolean resultLog) {
		String strLog = step + "|| Input Value : " + inputValue;
		this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param :
	 *            Step - Step description, expectedValue - verification point
	 *            expected value, actualValue - verification point actual value,
	 *            resultLog - result log pass/fail (true/false), includeMobile -
	 *            result for mobile(true/false)
	 * @author :Automation Tester
	 */
	@Step("{0} - {1} - {2}")
	public void logReporter(String step, String expectedValue, String actualValue, boolean resultLog) {
		String strLog = step + " || Expected Result : " + expectedValue + " || Actual Result : " + actualValue;
		this.addAssertTakeScreenShot(step, strLog, "", expectedValue, actualValue, resultLog);
	}

	/**
	 * @Method : addAssertTakeScreenShot
	 * @Description :
	 * @param :
	 * @author : Automation Tester
	 */
	public void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue,
			String actualValue, boolean resultLog) {
		System.out.println("Step Description--> " + strLog);
		final Logger logger = Logger.getLogger(Utilities.class);
		String fileName = getDateInSpecifiedFormat("dd_MMM_yyyy_HH_mm_ss") + "_RMID_" + current_RMID + ".png";
		String fileWithPath = System.getProperty("user.dir") + "\\target\\surefire-reports" + "\\ScreenShot\\"
				+ fileName;
		if (resultLog) {
			Reporter.log("Step Description--> " + strLog);
			logger.info("Step Description--> " + strLog);
			Assert.assertTrue(true);
		} else {
			Reporter.log("Step Description--> " + strLog);
			logger.error("Step Description--> " + strLog);
			this.takeScreenShot(objPojo.getDriver(), fileWithPath);
			Assert.assertTrue(false);
		}
	}

	/**
	 * @Method : fileToByte
	 * @Description : Converts image file to byte array for allure.
	 * @author :Automation Tester
	 * @throws :
	 *             IOException
	 */
	@Attachment(value = "Screenshot", type = "image/png")
	private byte[] fileToByte(File file) throws IOException {
		if (file != null)
			return Files.readAllBytes(Paths.get(file.getPath()));
		else
			return new byte[0];
	}

	public String getClipboardData() {
		String strClipboardData = "";
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			strClipboardData = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return strClipboardData;
	}

	/**
	 * Generate random string
	 * 
	 * @return String random string value
	 */
	public String getRandomString(int lenght) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyz";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * Generate random string with numbers
	 * 
	 * @return String random string value
	 */
	public String getRandomStringWithNumbers(int lenght) {
		String allowedChars = "abcdefghiklABCDEFGHIJKLMNOmnopqrstuvwxyz1234567890";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * Generate random string with numbers
	 * 
	 * @return String random string value
	 */
	public String getRandomNumbers(int length) {
		String allowedChars = "1234567890";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	// Author ; Pranay Patel
	// This will return SSN number in 9 Digit
	// i.e. 1495691882
	public String getCurrentTimeInMillis(int subString) {
		long unixTime = System.currentTimeMillis();
		String unixTimeString = unixTime + "";
		return unixTimeString.substring(subString);
	}

	/**
	 * @Method: dpString
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader
	 *            - excel file header column name
	 * @return - value for corresponding header
	 * @author Digvijay Dusane (SQS) @CreationDate: 27 April 2015 @ModifiedDate:
	 */
	public String dpString(String columnHeader) {
		Hashtable<Object, Object> dataPoolHashTable = objPojo.getDataPoolHashTable();
		try {
			if (dataPoolHashTable.get(columnHeader) == null)
				return "";
			else {
				Log.info("I found, Key: " + columnHeader + " Value : " + dataPoolHashTable.get(columnHeader));
				return (String) dataPoolHashTable.get(columnHeader);
			}
		} catch (Exception exception) {
			objPojo.setCustomException("Developer Side Issue");
			throw new RuntimeException(exception);
		}
	}

	/*
	 * @Method : getDateInSpecifiedFormat
	 * 
	 * @Description : This method takes parameter of your required DateFormat
	 * Type Like: dd-mm-YYYY DD.MM.YYYY and in return it will give you today's
	 * date in specified date format
	 * 
	 * @param : dateFormat like : dd-MM-YYYY
	 * 
	 * @author : Pranay
	 * 
	 */
	public String getDateInSpecifiedFormat(String dateFormat) {
		String current_date = "";
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		current_date = formatter.format(today);
		// System.out.println("getDateInSpecifiedFormat "+dateFormat + " -
		// "+current_date);
		return current_date;
	}

	// NEED TO VERIFY
	/**
	 * @Method: dpInteger
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader
	 *            - excel file header column name
	 * @return - value for corresponding header
	 * @author Rahul Nivangune @CreationDate: 27 April 2015 @ModifiedDate:13 Oct
	 *         2017
	 * @Updated By : Yogesh Khachane Date: 22 Oct 17
	 * @Update: DP Integer With StringTokenizer(It Will return Only Number like
	 *          10)
	 */
	public Integer dpInteger(String columnHeader) {
		Hashtable<Object, Object> dataPoolHashTable = objPojo.getDataPoolHashTable();
		try {
			if (dataPoolHashTable.get(columnHeader) == null)
				return 0;
			else {
				Log.info("I found, Key: " + columnHeader + " Value : " + dataPoolHashTable.get(columnHeader));
				String value = (String) dataPoolHashTable.get(columnHeader);
				StringTokenizer strTokenizer = new StringTokenizer(value, ".");
				value = strTokenizer.nextToken();
				return Integer.parseInt(value);
			}
		} catch (Exception exception) {
			objPojo.setCustomException("Developer Side Issue");
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method: dpStringTokenizer
	 * @Description: this Method is Used for Only get Number in excel sheet.
	 * @param columnHeader
	 *            - excel file header column name
	 * @return - value for corresponding header
	 * @author Yogesh Khachane @CreationDate: 22 Oct 2017 @ModifiedDate:
	 */
	public String dpStringTokenizer(String columnHeader) {
		Hashtable<Object, Object> dataPoolHashTable = objPojo.getDataPoolHashTable();
		try {
			if (dataPoolHashTable.get(columnHeader) == null)
				return "";
			else {
				Log.info("I found, Key: " + columnHeader + " Value : " + dataPoolHashTable.get(columnHeader));
				String strReturnValue = (String) dataPoolHashTable.get(columnHeader);
				StringTokenizer strTokenizer = new StringTokenizer(strReturnValue, ".");
				strReturnValue = strTokenizer.nextToken();
				return strReturnValue;
			}
		} catch (Exception exception) {
			objPojo.setCustomException("Developer Side Issue");
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method : takeScreenShot
	 * @Description : Take Screen shot for given web driver.
	 * @author :Automation Tester . Update by Rohini to return boolean
	 * 
	 */
	public boolean takeScreenShot(WebDriver webDriver, String fileWithPath) {
		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		// Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File destFile = new File(fileWithPath);
		// Copy file at destination
		try {
			FileUtils.moveFile(srcFile, destFile);
			this.fileToByte(destFile);
			return true;
		} catch (IOException iOException) {
			iOException.printStackTrace();
			return false;
		}
	}

}