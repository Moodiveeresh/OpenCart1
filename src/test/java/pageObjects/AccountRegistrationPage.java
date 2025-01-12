package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
    WebDriver driver;
    
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtlname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtphn;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpwd;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtpwdconfirm;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkpolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement clkcontinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirmation;
	
	public void setFirstName(String fname)
	{
		txtfname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtlname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void setTelePhnno(String phn)
	{
		txtphn.sendKeys(phn);
	}
	
	public void setPassword(String pwd)
	{
		txtpwd.sendKeys(pwd);
	}
	
	public void setConfirmPwd(String pwd)
	{
		txtpwdconfirm.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		chkpolicy.click();
	}
	
	public void clkcontinue()
	{
		clkcontinue.click();
	}
	
	
	public String getConfirmationMsg()
	{
//this is not validation
		try {
		return (msgconfirmation.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
		
	}
	
}
