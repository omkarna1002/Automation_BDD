package com.automation.qa.testrunner;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.automation.qa.base.TestBase;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.*;
public class Testrunner extends TestBase {
	
	static WebDriver driver;
	   
	@CucumberOptions(
	        features = "src/main/java/com.automation.qa.feature",
	        glue = {"com.automation.qa.stepdefinition"},
	        tags = {"@Functional"}
	)
	
	public class TestRunner {
		
	}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

