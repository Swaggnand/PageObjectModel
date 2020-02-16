package generic.automation;

import java.util.Hashtable;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pojo {
	private WebDriver webDriver;
	private WebDriverWait webDriverWait;
	private WebDriverWait appiumDriverWait;
	private Properties objConfig;
	private Hashtable<Object, Object> dataPoolHashTable;
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	private CustomReporter objReporter;
	private String runningScript = "";
	private String strTestDataFilePath = "";
	private String testCaseID = "";
	private String runID = "";
	private String testSuiteName = "";
	private String baseURL = "";
	private String serverAndPort = "";
	private String version = "";
	private String server = "";
	private int afterClickwait = 0;
	private String resourceName = "Undefined";
	private String customException = " ";
	private String groups = "";
	private String description = "";

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getGroupName() {
		return groups;
	}

	public void setGroupName(String groups) {
		this.groups = groups;
	}

	public String getCustomException() {
		return customException;
	}

	public void setCustomException(String customException) {
		this.customException = customException;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	private int scriptTimeoutWait = 0;

	public int getScriptTimeoutWait() {
		return scriptTimeoutWait;
	}

	public void setScriptTimeoutWait(int scriptTimeoutWait) {
		this.scriptTimeoutWait = scriptTimeoutWait;
	}

	public Integer getAfterClickwait() {
		return Integer.valueOf(afterClickwait);
	}

	public void setAfterClickwait(Integer afterClickwait) {
		this.afterClickwait = afterClickwait.intValue();
	}

	public void setDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getDriver() {
		return webDriver;
	}

	public String getStrTestDataFilePath() {
		return strTestDataFilePath;
	}

	public void setStrTestDataFilePath(String strTestDataFilePath) {
		this.strTestDataFilePath = strTestDataFilePath;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String strTestSuite) {
		testSuiteName = strTestSuite;
	}

	public void setWebDriverWait(WebDriverWait webDriverWait) {
		this.webDriverWait = webDriverWait;
	}

	public WebDriverWait getWebDriverWait() {
		return webDriverWait;
	}

	public void setAppiumDriverWait(WebDriverWait appiumDriverWait) {
		this.appiumDriverWait = appiumDriverWait;
	}

	public WebDriverWait getAppiumDriverWait() {
		return appiumDriverWait;
	}

	public void setObjConfig(Properties objConfig) {
		this.objConfig = objConfig;
	}

	public Properties getObjConfig() {
		return objConfig;
	}

	public void setDataPoolHashTable(Hashtable<Object, Object> testDataForTest) {
		this.dataPoolHashTable = testDataForTest;
	}

	public Hashtable<Object, Object> getDataPoolHashTable() {
		return dataPoolHashTable;
	}

	public void setRunningScriptName(String scriptName) {
		runningScript = scriptName;
	}

	public String getRunningScriptName() {
		return runningScript;
	}

	public void setCustomeReporter(CustomReporter reporter) {
		objReporter = reporter;
	}

	public CustomReporter getCustomReporter() {
		return objReporter;
	}

	public Utilities getObjUtilities() {
		return objUtilities;
	}

	public void setObjUtilities(Utilities objUtilities) {
		this.objUtilities = objUtilities;
	}

	public WrapperFunctions getObjWrapperFunctions() {
		return objWrapperFunctions;
	}

	public void setObjWrapperFunctions(WrapperFunctions objWrapperFunctions) {
		this.objWrapperFunctions = objWrapperFunctions;
	}

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public String getRunID() {
		return runID;
	}

	public void setRunID(String runID) {
		this.runID = runID;
	}

	public void setServerAndPort(String serverAndPort) {
		this.serverAndPort = serverAndPort;
	}

	public String getServerAndPort() {
		return serverAndPort;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}