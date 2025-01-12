package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

   public MyAccountPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath="//h2[text()='My Account']")//xpath of my account page
	WebElement txtMyAccount;//my account page text
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")//xpath of logout link //add in step no 6
	WebElement lnkLogout;//logout link
	
	
	
	public boolean isMyAccountPageExist() //method to check my account page is exist or not
	{
		try //
		{
			Thread.sleep(3000);
			return (txtMyAccount.isDisplayed());//return true if my account page is displayed
        } 
		catch (Exception e) //if my account page is not displayed then catch the exception
		{
           return false;//return false if my account page is not
		}
	
		
	}
   
	public void clickLogout() // method to click on logout link
	{
		lnkLogout.click();// click on logout link
	}
   
}
