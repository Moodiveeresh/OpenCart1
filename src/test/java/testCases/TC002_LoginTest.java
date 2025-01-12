package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{

	@Test(groups={"Sanity","Master"})
	public void loginTest()
    {
     
		logger.info("***** Starting TC002_LoginTest *****");
		
		//home page
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link");
		hp.clickLogin();
		logger.info("clicked on login link");
		
		//login page
		logger.info("providing login details...");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//validation my account page
		MyAccountPage map=new MyAccountPage(driver);
		boolean target_page=map.isMyAccountPageExist();
		
	//	Assert.assertEquals(target_page, true, "Login test failed");//if target page is not displayed then it will fail the test
		Assert.assertTrue(target_page);//if target page is not displayed then it will fail the test
		}
		catch(Exception e)
		{
			Assert.fail();
		}
        logger.info("***** Ending TC002_LoginTest *****");
		
		
		
		
		
    }
	
}
