package functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import constants.CommonConstants;
import pages.Homepage;
import pages.UsersPage;

public class Users extends IntializeAndDeInitialize {
	
	public static WebDriver driver = IntializeAndDeInitialize.driver;
	
	public static UsersPage usersPage = new UsersPage(driver);
	
	
	/**This is function is used for moving to required page number in the Users form 
	 * @throws Exception */
	public static void navigateToPageNumberInEditors(int pageNum) throws Exception {
		
		// If parameter is not passed from tesplan defaulting it to 2 
		if (pageNum==0) {
			pageNum=CommonConstants.DEFAULT_PAGENUM;
			Log.info("defaulted pagenumber to 2");
		}
		Log.info("Started Navigation to required pagenumber "+pageNum+" in Users form");
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		
		usersPage.tabEditors.click();
		for(int i=1;i<pageNum;i++) {
			wait.until(ExpectedConditions.elementToBeClickable(usersPage.btnNext));
			usersPage.btnNext.click();
		}
		
		Assert.assertEquals(Integer.parseInt(usersPage.txtpageSelected.getText()), pageNum,"Required Pagenumber is selected successfully");
		Log.info("Navigated to Required pagenumber in Users");
		Common.takesnapShot();
	}
	
	/** This function can be used to get user details with max user edits in Users -> Editors
	 * 
	 * @param driver
	 * @throws IOException 
	 */
	public static void userDetailsOfMaxEdits() throws IOException {
		
		List<WebElement> useredits = usersPage.txtUserEdits;

		int tempCount = 0;		
		List<Integer> occurence = new ArrayList<>();	
		for(int i=0;i<useredits.size();i++) {
			
			String[] edits = useredits.get(i).getText().split(" ");
			int editCount = Integer.parseInt(edits[0]);
			if(editCount>=tempCount) {
				tempCount=editCount;	
				if(editCount!=tempCount) {
					occurence.clear();
				}
				occurence.add(i);
			}
			
		}
		Log.info("$$$$$$$$$$$$$$$$$$$$$$$ User details with max edits are as follows $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		for(int index:occurence) {
			String edits = usersPage.userEditCount(index+1, driver);
			String userName = usersPage.userNameOfCorrespondingEdit(index+1, driver);			
			String userLocation = usersPage.userLocationOfCorrespondingEdit(index+1, driver);
			if (userLocation.isEmpty()) {
				Log.info("UserLocation is Empty for "+userName);
			}
			Log.info("$$$$$$$$$$$$$$$ User Name "+userName+" in Location "+userLocation+" is having "+edits+"$$$$$$$$$$$$$$$$$$");
		
		}
		Common.takesnapShot();
	}

}
