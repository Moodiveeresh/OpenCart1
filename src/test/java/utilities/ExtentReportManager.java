package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    String repName;
    
	public void onStart(ITestContext testContext) 
	{
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date = new Date();
		String currenttimeStamp = sdf.format(date);
		*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName = "Test-Report-" + timeStamp + ".html";//report name with time stamp .html extension 
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);//report location
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Test Report");
		sparkReporter.config().setReportName("OpenCart Functional Test Report");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("OS", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successful executed");
		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try 
		{
			String imgpath = new BaseClass().captureScreen(result.getName());//capture screenshot
			test.addScreenCaptureFromPath(imgpath);//attach screenshot to report
		} 
		catch (Exception e1) //catch exception
		{
			e1.printStackTrace();//print exception
		}
		

	}

	public void onsTestSkipped(ITestResult result) 
	{
    	test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
        
	}
	
	public void onFinish(ITestContext testContext) 
	{
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File exetentReport = new File(pathOfExtentReport);
		
		try 
		{
			Desktop.getDesktop().browse(exetentReport.toURI());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		/*
		 * try { URL url = new URL("file:///"+System.getProperty("user.dir") +
		 * "\\reports\\" + repName);
		 * 
		 * ImageHtmlEmail email = new ImageHtmlEmail(); email.setDataSourceResolver(new
		 * DataSourceUrlResolver(url)); email.setHostName("smtp.googlemail.com");
		 * email.setSmtpPort(465); email.setAuthenticator(new
		 * DefaultAuthenticator("veereshmoodi613@gmail.com", "password"));
		 * email.setSSLOnConnect(true);
		 * email.setFrom("veereshmoodi613@gmail.com");//sender email
		 * email.setSubject("Test results");
		 * email.setMsg("Please find the attached test results");
		 * email.addTo("moodiveeresh613@gmail.com"); //receiver email
		 * email.attach(url, "extent report",
		 * "please check report"); email.send(); 
		 * } 
		 * catch (Exception e) 
		 * {
		 * e.printStackTrace(); 
		 * }
		 */
	}
	
}
