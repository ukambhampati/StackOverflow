package functions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import constants.CommonConstants;
import functions.ReadPropertyFile;


public class IntializeAndDeInitialize {

	public static WebDriver driver;
	public static Properties config = new Properties();
	String directory = System.getProperty("user.dir");	
	
	@BeforeTest
	@Parameters("url")	
	public void setUp(@Optional("") String url) throws IOException {
				
		
		ReadPropertyFile readProp = new ReadPropertyFile();
		config = readProp.initPropertyFile(directory+"\\src\\test\\resources\\properties\\Config.properties",config);
				
		if(driver==null) {
			
			String browserName = config.getProperty("browser");
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",directory+"\\src\\test\\resources\\executables\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				driver = new ChromeDriver(options);
			}
		}
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("pageLoadWait")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
		if(url.isEmpty()) {
			driver.get(config.getProperty("baseUrl"));
		} else {
			driver.get(url);
		}
		// Deleting any existing files in screenshots folder
		FileUtils.deleteDirectory(new File(CommonConstants.PATH_FOR_SCREENSHOTS));
		// deleting log file
		FileUtils.forceDelete(new File(CommonConstants.PATH_FOR_LOGFILE));
	}
	
	@AfterTest
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
