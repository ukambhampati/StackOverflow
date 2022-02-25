package functions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.CommonConstants;
import pages.Homepage;

public class Common extends IntializeAndDeInitialize {
	
	

	public static WebDriver driver = IntializeAndDeInitialize.driver;

	public static Homepage homepage = new Homepage(driver);
	
	/** This function can be used to click accept cookies button after website is loaded for first time */
	public static void acceptCookies() throws Exception {
		
		Log.info("Started checking if Accept all cookies button displayed");
				
		if(isElementDisplayed(homepage.btnAcceptCookies)) {
			Log.info("Accept All cookies message is displayed");
			homepage.btnAcceptCookies.click();
			Log.info("clicked on accept all cookies button");
		} else {
			Log.info("Accept All cookies button is NOT displayed");
		}
		takesnapShot();
	}
	
	/** This function is used for verifying if a particular element is available in DOM and visible
	 * if NOT visible and available in DOM it returns false */
	public static boolean isElementDisplayed(WebElement locator) {
		
		boolean displayStatus = false;
		
		try {
			displayStatus = locator.isDisplayed();
		} catch (Exception ex) {
			displayStatus = false;
		}
		return displayStatus;
	}	

	/** THis function can be used to refresh browser */
	 public static void refreshBrowser() throws InterruptedException {

		driver.navigate().refresh();
		Thread.sleep(3000);//required to click on alert button.
		try {
			driver.switchTo().alert().accept();			
		}
		catch(Exception e) {
			Thread.sleep(2000);
			Log.info("No Alerts after refresh");
		}
		Log.info("Browser refreshed");
		
	 }
	 
	 /** THis function is for taking screenshots and storing in destination file 
	  * For this project we are storing in src/test/java/resources/screenshots folder
	  * @param driver
	  * @throws IOException
	  */
	 public static void takesnapShot() throws IOException {
		 TakesScreenshot scrnShot = ((TakesScreenshot)driver);
		 File SrcFile=scrnShot.getScreenshotAs(OutputType.FILE);
		 File destFile = new File(CommonConstants.PATH_FOR_SCREENSHOTS);
		 FileUtils.copyFileToDirectory(SrcFile, destFile);
	 }
	 

	 
}
