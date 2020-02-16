package generic;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;





public class BaseTest extends Pojo {
	
	private Properties objConfig;
	public WebDriver webDriver;
	private WrapperFunctions objWrapperFunctions;
	private InitializeTearDownEnviornment objInitializeTearDownEnvironment;

	
	
	public void initializeWebEnvironment() {

		this.loadConfigProperties();
		this.setObjWrapperFunctions(new WrapperFunctions((Pojo) this));
		this.objInitializeTearDownEnvironment = new InitializeTearDownEnviornment();
		this.setDriver(this.webDriver = this.objInitializeTearDownEnvironment.initializeWebEnvironment(this.objConfig));
		this.webDriver.get("http://google.com");


	}
	
	
	public void loadConfigProperties() {

		try {
			(this.objConfig = new Properties()).load(new FileInputStream(
					String.valueOf(System.getProperty("user.dir")) + "/src/test/resources/config.properties"));
			this.setObjConfig(this.objConfig);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	
	public void teardownenviornment() {
		
		try {
			objWrapperFunctions = null;
			this.getDriver().close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
