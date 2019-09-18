package com.automation.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.qa.pages.Calculator_Savings_Account;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static ExtentReports extent;
	public static ExtentTest logger;	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/automation"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 
	public static Calculator_Savings_Account tocalculatorpage() throws IOException {
 		 Calculator_Savings_Account  cs1= new Calculator_Savings_Account(driver);
 	    	return cs1;
 	    }

	
	public static WebDriver initialize_and_move_to_homepage(){
		 try {
			 
			String browserName = prop.getProperty("browser");
			 String timeStamp=getTimeStamp();
			 extent = new ExtentReports (System.getProperty("user.dir") +"//target//ExtentReport.html", true);
			 logger = extent.startTest("WestPac", "Test to Tax Rate Functionality of WestPac");
			 extent.addSystemInfo("Host Name", "Functional");
			 extent.addSystemInfo("Environment", "Automation_WestPac");
			if(browserName.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", prop.getProperty("path"));	
				driver = new ChromeDriver(); 
			}
			else if(browserName.equals("FF")){
				System.setProperty("webdriver.gecko.driver", prop.getProperty("path"));	
				driver = new FirefoxDriver(); 
			}				
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();			
			driver.get(prop.getProperty("url")); 
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return driver;		
	}
	

    @AfterMethod
    public void getResult(ITestResult result){
           if(result.getStatus() == ITestResult.FAILURE){
                  logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
                  logger.log(LogStatus.FAIL, "Test Case Failed reason  "+result.getThrowable());
           }else if(result.getStatus() == ITestResult.SKIP){
                  logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
           }
           else if(result.getStatus() == ITestResult.SUCCESS){
                  logger.log(LogStatus.PASS, "Test Case passed ");
           }
           extent.endTest(logger);
    }
    
    
    @AfterSuite
    public void endReport(){
         	extent.flush();  
        	driver.quit();
 }
    

    public static String getScreenshot(WebDriver driver) {
           String destination="";
           try {
                  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                  TakesScreenshot ts = (TakesScreenshot) driver;
                  File source = ts.getScreenshotAs(OutputType.FILE);         
                  destination = System.getProperty("user.dir") + "\\Screenshots\\Test_" + dateName + ".png";
                  File finalDestination = new File(destination);
                  FileUtils.copyFile(source, finalDestination);
           } catch (WebDriverException e) {
                 
                  e.printStackTrace();
           } catch (IOException e) {
                 
                  e.printStackTrace();
           }
           
           return destination;
    }

    public static String getTimeStamp() {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());  
         return  sdf.format(timestamp);         
     }

    public void screenShot(ITestResult result){
    	 
    	 if(ITestResult.FAILURE==result.getStatus())
    	 {
	    	 try{   	
	    	 TakesScreenshot screenshot=(TakesScreenshot)driver;    	
	    	 File src=screenshot.getScreenshotAs(OutputType.FILE);
	    	 FileUtils.copyFile(src, new File(System.getProperty("user.dir")+result.getName()+".png"));
    	       }catch (Exception e){
    	           System.out.println(e.getMessage());
    	                           } 
    	 }

    } 
    	
    	 
    }	
    
  

