package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

   public static WebDriver driver;
   public org.apache.logging.log4j.Logger logger; //Log4j
   public Properties p;
   
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os", "browser"})
	 public void setup(String os, String br) throws IOException
	{
		
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		logger=LogManager.getLogger(this.getClass());//LOG4J2
	//grid setup
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			//os
			if (os.equalsIgnoreCase("windows")) 
			{
				cap.setPlatform(Platform.WIN10);
			}
			 else if (os.equalsIgnoreCase("mac")) 
			 {
				cap.setPlatform(Platform.MAC);
			}
			 else 
			 {
				 System.out.println("no matching os name..");
				 return;
			 }
			//browser
			switch(br.toLowerCase())
            {
			   case "chrome" : cap.setBrowserName("chrome"); break;
			   case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			   case "firefox" : cap.setBrowserName("firefox"); break;
			   default:
				System.out.println("Invalid browser name..");
					return;
			}
           driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
           
		} // end of remote grid setup
		
		
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			// Selecting browser based on parameter passed from testng.xml
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));// Reading url from properties file
			driver.manage().window().maximize();
			
		}
		
		/*
		//Selecting browser based on parameter passed from testng.xml
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;	
		default : System.out.println("Invalid browser name.."); return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));//Reading url from properties file
		
		driver.manage().window().maximize();
		*/
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();	
	}
	
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNmber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomeAlphanumeric()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
		
	}
	
	public String captureScreen(String tname) 
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcfile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetfilpath = System.getProperty("user.dir") + "\\screenshots\\" + tname +"_"+ timestamp + ".png";
		File targetfile = new File(targetfilpath);
		
		srcfile.renameTo(targetfile);
		
		return targetfilpath;
	}
	
	
}
