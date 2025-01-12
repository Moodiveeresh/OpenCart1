package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;



public class TC001_AccountRegistrationTest extends BaseClass {

	
	
	@Test(groups={"Regression", "Master"})
	public void verify_acc_registrartion()
	{
		
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link");
		
		hp.clickRegister();
		logger.info("clicked on register link");
		
		AccountRegistrationPage ar=new AccountRegistrationPage(driver);
		
		logger.info("providing customer details...");
		ar.setFirstName("virat".toUpperCase());
		ar.setLastName("v".toUpperCase());
		ar.setEmail(randomeString()+"@gmail.com");
		ar.setTelePhnno(randomeNmber());
		
		String pwd=randomeAlphanumeric();
		
		ar.setPassword(pwd);
		ar.setConfirmPwd(pwd);
		ar.setPrivacyPolicy();
		ar.clkcontinue();
		logger.info("validating expected message..");
		String confirm_msg=ar.getConfirmationMsg();
		if(confirm_msg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed....");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
	//	Assert.assertEquals(confirm_msg, "Your Account Has Been Created");
		
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
	
		logger.info("***** finished TC001_AccountRegistrationTest *****");
		
	}
	
	
	
}
