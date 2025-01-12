package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


/*
 Data is valid - login success - test pass - logout
 Data is valid - login fail - test fail
 
 Data is invalid - login success - test fail - logout
 Data is invalid - login failed - test pass
 
 */

public class TC003_LoginDDT extends BaseClass
{
    @Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")//DataProviders.class is the class where we have written the data provider method
	public void verify_loginDDT(String email, String pwd, String exp) 
	{
		logger.info("***** Starting TC003_LoginDDT *****");
		//home page
		try 
		{
		
    	HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		//login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email); //p.getProperty("email")
		lp.setPassword(pwd); //p.getProperty("password")
		lp.clickLogin();
		
		//validation my account page
		MyAccountPage map=new MyAccountPage(driver);
		boolean target_page=map.isMyAccountPageExist();
				
		/*
		 Data is valid - login success - test pass - logout
		               - login fail - test fail
				 
		 Data is invalid - login success - test fail - logout
		                 - login failed - test pass
		*/
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if (target_page == true) 
			{
				logger.info("Login test passed");
				map.clickLogout();
				Assert.assertTrue(true);
			} 
			else 
			{
				logger.error("Login test failed");
				Assert.assertTrue(false);
			}
		}
		
		if (exp.equalsIgnoreCase("Invalid")) 
		{
			if (target_page == true) 
			{
				logger.error("Login test failed");
				map.clickLogout();
				Assert.assertTrue(false);
			}
			else 
			{
				logger.info("Login test passed");
				Assert.assertTrue(true);
			}
		}
	 } 
	catch (Exception e) 
	{
		Assert.fail();
	}
		logger.info("***** Ending TC003_LoginDDT *****");
	}
	
}
