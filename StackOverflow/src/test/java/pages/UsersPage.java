package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UsersPage {


		WebDriver driver;
		
		public UsersPage(WebDriver webdriver) {
			this.driver = webdriver;
			PageFactory.initElements(driver, this);

		}
		
		@FindBy(how=How.CSS  ,using="a[data-value='editors']")
		public WebElement tabEditors;	
		
		@FindBy(how=How.CSS  ,using="a[rel='next']")
		public WebElement btnNext;
		
		@FindBy(how=How.XPATH  ,using="//div[contains(@class,'user-info')]//div[@class='user-tags']")
		public List<WebElement> txtUserEdits;
		
		@FindBy(how=How.XPATH  ,using="//div[contains(@class,'user-info')]//div[@class='user-tags']")
		public WebElement txtUserEdit;		
		
		@FindBy(how=How.XPATH  ,using="//div[contains(@class,'user-info')]//div[@class='user-tags']/preceding-sibling::div[@class='user-details']/a")
		public WebElement txtUserNames;
		
		@FindBy(how=How.XPATH  ,using="//div[contains(@class,'user-info')]//div[@class='user-tags']/preceding-sibling::div[@class='user-details']/span")
		public WebElement txtUserLocations;	
		
		@FindBy(how=How.XPATH, using="//div[@class='s-pagination--item is-selected']")
		public WebElement txtpageSelected;
		
		public String userNameOfCorrespondingEdit(int userEditOccurence,WebDriver driver) {
			return driver.findElement(By.xpath("(//div[contains(@class,'user-info')]//div[@class='user-tags'])["+userEditOccurence+"]/preceding-sibling::div[@class='user-details']/a")).getText();
		}

		public String userLocationOfCorrespondingEdit(int userEditOccurence,WebDriver driver) {
			return driver.findElement(By.xpath("(//div[contains(@class,'user-info')]//div[@class='user-tags'])["+userEditOccurence+"]/preceding-sibling::div[@class='user-details']/span")).getText();
		}
		
		public String userEditCount(int occurence,WebDriver driver) {
			return driver.findElement(By.xpath("(//div[contains(@class,'user-info')]//div[@class='user-tags'])["+occurence+"]")).getText();
		}
}
