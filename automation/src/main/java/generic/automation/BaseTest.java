
package generic.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest extends Pojo {
	private WebDriver webDriver;
	private Properties objConfig;
	private WebDriverWait webDriverWait;
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	private String callingClassName = "";
	private String serverAndPort = "";
	private String baseURL = "";

/*	private String double_check_url = "10.100.21.172";
	private String double_check_url_1 = "10.100.21.172";
	private String double_check_port = "9090";
	private String double_check_port2 = "9091";
	private String double_check_port3 = "8092";
	private String double_check_port4 = "9081";
	private String double_check_port5 = "8081";
	private String double_check_port6 = "9082";
	private String double_check_port7 = "8083";
	private String double_check_port8 = "7091";
	private String double_check_port9 = "7092";
	private String double_check_portTemp = "90";
	private String double_check_portTemp1 = "80";
	private String double_check_urlTemp = "10.100.21.";*/

	/*private String double_check_webPath = "/mobiledoc/jsp/webemr/login/newLogin.jsp";*/

	Hashtable<Object, Object> testDataTable = new Hashtable<Object, Object>();
	Hashtable<Object, Object> testDataForTest = new Hashtable<Object, Object>();
	private InitializeTearDownEnvironment objInitializeTearDownEnvironment;

	public void initializeWebEnvironment(String testDataFilePath) {
		loadConfigProperties();
		loadDataProvider(testDataFilePath);
		callingClassName = getClass().getName();
		setRunningScriptName(callingClassName);
		objInitializeTearDownEnvironment = new InitializeTearDownEnvironment();
		webDriver = objInitializeTearDownEnvironment.initializeWebEnvironment(objConfig);
		setDriver(webDriver);
		setAfterClickwait(Integer.valueOf(Integer.parseInt(objConfig.getProperty("AfterClickWait"))));
		setScriptTimeoutWait(Integer.parseInt(objConfig.getProperty("ScriptTimeoutWait")));
		webDriverWait = new WebDriverWait(webDriver,
				Integer.parseInt(objConfig.getProperty("driver.WebDriverWait").trim()));
		setWebDriverWait(webDriverWait);
		objUtilities = new Utilities(this);
		setObjUtilities(objUtilities);
		objWrapperFunctions = new WrapperFunctions(this);
		setObjWrapperFunctions(objWrapperFunctions);
		serverAndPort = (objConfig.getProperty("web.protocol") + "://" + objConfig.getProperty("web.domain") + ":"
				+ objConfig.getProperty("web.port"));
		baseURL = (serverAndPort + objConfig.getProperty("web.path"));
		baseURL = "https://www.flipkart.com/";
		setServerAndPort(serverAndPort);
		setBaseURL(baseURL);

		verifyVersion();
		verifyServer();

		loadBaseURL();
	}

	public void verifyVersion() {
		try {
			Document doc = Jsoup.connect(serverAndPort + "/mobiledoc/jsp/catalog/xml/CheckServerVersion.jsp").get();
			Element body = doc.select("body").first();
			String version = body.text();
			setVersion(version);
			getObjUtilities().logReporter("Server is Reachable", true);
		} catch (Exception exception) {
			exception.printStackTrace();
			getObjUtilities().logReporter("Server is Not Reachable", false);
		}
	}

	private void verifyServer() {
		String server = getBaseURL().substring(getBaseURL().indexOf(":") + 3);
		server = server.substring(0, server.indexOf("/"));
		setServer(server);
		System.out.println(server);
	}

	public void loadBaseURL() {
		try {
			webDriver.get(getBaseURL());
			getObjUtilities().logReporter("Open URL - '" + getBaseURL() + "'.", true);
			getObjUtilities().logReporter("Verified - eCW", true);
		} catch (Exception exception) {
			getObjUtilities().logReporter("Open URL - '" + getBaseURL() + "'.", false);
			exception.printStackTrace();
		}
	}

	public void tearDownWebEnvironment() {
		try {
			webDriver.manage().deleteAllCookies();
			try {
				File file = new File(System.getProperty("java.io.tmpdir"));
				FileUtils.cleanDirectory(file);

			} catch (IOException localIOException1) {
			}
			webDriver.quit();
			objUtilities = null;
			objWrapperFunctions.waitFor(2);
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Thread.sleep(1500L);

			Thread.sleep(1500L);
			System.out.println("Kill Chrome Browser.!!!!!!");
		} catch (Exception exception) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			exception.printStackTrace();
			if ((System.getProperty("web.browser").trim().equalsIgnoreCase("IE"))
					|| (System.getProperty("web.browser").trim().equalsIgnoreCase("Chrome"))) {
				killBrowserAndDriver(objConfig);
			}
		}
		objWrapperFunctions = null;
	}

	protected void killBrowserAndDriver(Properties objConfig) {
		String browser = System.getProperty("web.browser").trim();
		String browserProcess = "";
		String driverProcess = "";

		if ((!browser.equals("")) && (browser.equalsIgnoreCase("IE"))) {
			browserProcess = "iexplore";
			driverProcess = "IEDriverServer.exe";
		} else if ((!browser.equals("")) && (browser.equalsIgnoreCase("Chrome"))) {
			browserProcess = browser;
			driverProcess = "chromedriver.exe";
		}
		try {
			Process procDriver = Runtime.getRuntime().exec("taskkill /F /T /IM " + driverProcess);
			Process procIE = Runtime.getRuntime().exec("taskkill /F /T /IM " + browserProcess + ".exe");
			procDriver.waitFor();
			procIE.waitFor();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void loadDataProvider(String testDataFilePath) {
		if (!testDataFilePath.equals("")) {
			loadConfigProperties();
			testDataFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/" + testDataFilePath
					+ ".xlsx";
			DataPool2 objDataPool = new DataPool2();
			testDataTable = objDataPool.loadTestData(testDataFilePath);
		}
	}

	public void loadTestData(String RMIDRowNumber) {
		testDataForTest = (Hashtable<Object, Object>) testDataTable.get(RMIDRowNumber);
		System.out.println("testDataForTest------->" + testDataForTest);
		setDataPoolHashTable(testDataForTest);
		objUtilities = new Utilities(this);
		setObjUtilities(objUtilities);
		Logger logger = Logger.getLogger(Utilities.class);
		logger.info("RMID " + RMIDRowNumber + " Startred at "
				+ objUtilities.getDateInSpecifiedFormat("dd-MMM-yyyy-HH-mm-ss"));
		objUtilities.setCurrent_RMID(RMIDRowNumber);
	}

	public void createLo4jLogerDirectory() {
		String logFilePath = System.getProperty("user.dir") + "/target/custom-reports/log4j-Logger.log";
		File reportFile = new File(logFilePath);
		try {
			if (!reportFile.exists()) {
				new File(System.getProperty("user.dir") + "/target/custom-reports").mkdir();
				reportFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadConfigProperties() {
		try {
			objConfig = new Properties();
			objConfig.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
			setObjConfig(objConfig);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public boolean cleanDonloadsDirectory() {
		try {
			String downloadFilesLocation = System.getProperty("user.dir")
					+ objConfig.getProperty("downloads.path").trim();
			File directory = new File(downloadFilesLocation);
			FileUtils.cleanDirectory(directory);
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;

		}
		return true;
	}

	public void initializeWebEnvironmentForSettings(String testDataFilePath) {
		loadConfigProperties();
		loadDataProvider(testDataFilePath);
		callingClassName = getClass().getName();
		setRunningScriptName(callingClassName);
		objInitializeTearDownEnvironment = new InitializeTearDownEnvironment();
		webDriver = objInitializeTearDownEnvironment.initializeWebEnvironment(objConfig);
		setDriver(webDriver);
		setAfterClickwait(Integer.valueOf(Integer.parseInt(objConfig.getProperty("AfterClickWait"))));
		setScriptTimeoutWait(Integer.parseInt(objConfig.getProperty("ScriptTimeoutWait")));
		webDriverWait = new WebDriverWait(webDriver,
				Integer.parseInt(objConfig.getProperty("driver.WebDriverWait").trim()));
		setWebDriverWait(webDriverWait);
		objUtilities = new Utilities(this);
		setObjUtilities(objUtilities);
		objWrapperFunctions = new WrapperFunctions(this);
		setObjWrapperFunctions(objWrapperFunctions);
		serverAndPort = (objConfig.getProperty("web.protocol") + "://" + objConfig.getProperty("web.domain") + ":"
				+ objConfig.getProperty("web.port.settings"));
		System.out.println("serverAndPort   =" + serverAndPort);
		baseURL = (serverAndPort + objConfig.getProperty("web.path"));
		System.out.println("baseURL   =" + baseURL);
		setServerAndPort(serverAndPort);
		setBaseURL(baseURL);
		verifyVersion();
		verifyServer();
		loadBaseURL();
	}

	public void loadDataProviderWithSpecialCharacter(String testDataFilePath) {
		if (!testDataFilePath.equals("")) {
			loadConfigProperties();
			testDataFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/"
					+ objConfig.getProperty("testdata.sc.path") + testDataFilePath + ".xlsx";
			DataPool2 objDataPool = new DataPool2();
			testDataTable = objDataPool.loadTestData(testDataFilePath);
		}
	}

public void initializeWebEnvironmentForSpecialCharacter(String testDataFilePath)
{
		loadConfigProperties();
		loadDataProviderWithSpecialCharacter(testDataFilePath);
		callingClassName = getClass().getName();
		setRunningScriptName(callingClassName);
		objInitializeTearDownEnvironment = new InitializeTearDownEnvironment();
		webDriver = objInitializeTearDownEnvironment.initializeWebEnvironment(objConfig);
		setDriver(webDriver);
		setAfterClickwait(Integer.valueOf(Integer.parseInt(objConfig.getProperty("AfterClickWait"))));
		setScriptTimeoutWait(Integer.parseInt(objConfig.getProperty("ScriptTimeoutWait")));
		webDriverWait = new WebDriverWait(webDriver, 
		Integer.parseInt(objConfig.getProperty("driver.WebDriverWait").trim()));
		setWebDriverWait(webDriverWait);
		objUtilities = new Utilities(this);
		setObjUtilities(objUtilities);
		objWrapperFunctions = new WrapperFunctions(this);
		setObjWrapperFunctions(objWrapperFunctions);
		serverAndPort = (objConfig.getProperty("web.protocol") + "://" + objConfig.getProperty("web.domain") + ":" + objConfig.getProperty("web.port"));
    	baseURL = (serverAndPort + objConfig.getProperty("web.path"));
        setServerAndPort(serverAndPort);
        setBaseURL(baseURL);
        verifyVersion();
        verifyServer();
       	loadBaseURL();
 }
}