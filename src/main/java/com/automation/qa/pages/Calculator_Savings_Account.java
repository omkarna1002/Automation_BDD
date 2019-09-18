package com.automation.qa.pages;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.automation.qa.base.TestBase;
import com.automation.qa.util.ExcelData_Reader;
import com.relevantcodes.extentreports.LogStatus;


public class Calculator_Savings_Account extends TestBase {

		public static WebDriver driver;
		
		static HashMap<String, String> hm = new HashMap<String, String>();
		static String investment = "//li[@role='menuitem']/a[text()='Investments']";
		static String calculator= "//nav[@id='ubermenu-ps']/descendant::a[text()='Investments']/../descendant::a[text()='Calculators']";
		static String bestreturn="//a[text()='Which could give the best return?']";
		static String savingaccount="//div[@id='wpc-calculator-new']/descendant::a[@data-type='savings']/span[text()='Savings Account']";
		static String deposit="//label[text()='Initial Deposit:']/../div/input";
		static String saving="//label[text()='Savings Period:']/../div[1]/input[1]";
		static String taxrate="//label[text()='Income Tax Rate:']/../div[@class='wpc-select-wrapper wpc-adjacent-input']/div/select";
		
		public Calculator_Savings_Account(WebDriver driver) throws IOException
		{
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//li[@role='menuitem']/a[text()='Investments']")
		public static WebElement investment_link;
		
		@FindBy(xpath="//nav[@id='ubermenu-ps']/descendant::a[text()='Investments']/../descendant::a[text()='Calculators']")
		public static WebElement calculator_link;
		
		@FindBy(xpath="//a[text()='Which could give the best return?']")
		public static WebElement bestreturntext;
		
		@FindBy(xpath="//div[@id='wpc-calculator-new']/descendant::a[@data-type='savings']/span[text()='Savings Account']")
		public static WebElement savingacountlink;
		
		@FindBy(xpath="//div[@id='wpc-calc-body']/descendant::div[@id='wpc-calculators']/div[3]/descendant::a[@original-title='Enter the starting balance of your savings']")
		public static WebElement initialdep_icon;
		
		@FindBy(xpath="//div[@id='wpc-calc-body']/descendant::div[@id='wpc-calculators']/div[3]/descendant::a[@original-title='Optional: Enter the amount of your regular savings and how frequently they will be deposited']")
		public static WebElement ongoingsav_icon;
		
		@FindBy(xpath="//div[@id='wpc-calc-body']/descendant::div[@id='wpc-calculators']/div[3]/descendant::a[@original-title='This is the period over which your returns will be calculated']")
		public static WebElement savingperiod_icon;
		
		@FindBy(xpath="//div[@id='wpc-calc-body']/descendant::div[@id='wpc-calculators']/div[3]/descendant::a[text()='Select a Westpac Product']/../following-sibling::div/a[contains(@original-title,'Choose from a list of current interest rates offered by Westpac, or specify your own rate')]")
		public static WebElement interestrate_icon;
		
		@FindBy(xpath="//div[@id='wpc-calc-body']/descendant::div[@id='wpc-calculators']/div[3]/descendant::a[@original-title='Specify the frequency at which interest is paid out']")
		public static WebElement interestpaid_icon;
		
		@FindBy(xpath="//div[@id='wpc-calc-body']/descendant::div[@id='wpc-calculators']/div[3]/descendant::a[contains(@original-title,'If you know your Income (marginal) Tax Rate specify it here')]")
		public static WebElement incometax_icon;
			
		@FindBy(xpath="//label[text()='Initial Deposit:']/../div/input")
		public static WebElement initialdeposit_textbox;
		
		@FindBy(xpath="//label[text()='Ongoing Savings:']/../div/input")
		public static WebElement ongoingsaving_textbox;
		
		@FindBy(xpath="//label[text()='Ongoing Savings:']/../div[@class='wpc-select-wrapper wpc-adjacent-input']/div/select")
		public static WebElement ongoingsaving_dropdown;
		
		@FindBy(xpath="//label[text()='Savings Period:']/../div[1]/input[1]")
		public static WebElement savingperiod_textbox1;
		
		@FindBy(xpath="//label[text()='Savings Period:']/../div[1]/input[2]")
		public static WebElement savingperiod_textbox2;
		
		@FindBy(xpath="//label[text()='Savings Period:']/../div[1]/input[3]")
		public static WebElement savingperiod_textbox3;
			
		@FindBy(xpath="//label[text()='Interest Rate:']/../div/input")
		public static WebElement interestrate_textbox;
		
		@FindBy(xpath="//label[text()='Interest Paid:']/../div[@class='wpc-select-wrapper wpc-adjacent-input']/div/select")
		public static WebElement interestpaid_dropdown;

		@FindBy(xpath="//label[text()='Income Tax Rate:']/../div[@class='wpc-select-wrapper wpc-adjacent-input']/div/select")
		public static WebElement incometaxrate_dropdown;
		
		@FindBy(xpath="//label[text()='Income Tax Rate:']/../descendant::a[text()='Find My Rate']")
		public static WebElement findmyrate_button;
			
		@FindBy(xpath="//div[@id='cl-modal']/descendant::label[text()='I am a NZ tax resident:']/../following-sibling::td/label/input[@value='yes']")
		public static WebElement NZresident_radiobutton;
		
		@FindBy(xpath="//div[@id='cl-modal']/descendant::label[text()='My investments are held by:']/../following-sibling::td/label/input[contains(@value,'individual')]")
		public static WebElement individual_radiobutton;
		
		@FindBy(xpath="//div[@id='cl-modal']/descendant::label[contains(text(),'My expected annual')]/../following-sibling::td[@id='tax-calc-q3']/label/input[@value='14k']")
		public static WebElement taxableincome_radiobutton;
		
		@FindBy(xpath="//table[@class='wpc-form-nested-table']/tbody/descendant::h3")
		public static WebElement incometaxrate_onscreen;
		
		@FindBy(xpath="//a[text()='Close & Continue']")
		public static WebElement close_and_continue;
		
		
		
		public static void calculatorlink()
		{  
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(investment)));
			Actions act= new Actions(driver);
			act.moveToElement(investment_link);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(calculator)));
			act.moveToElement(calculator_link);
			act.click().build().perform();			
		}
		
		public static void bestreturnlink()
		{
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(bestreturn)));
			bestreturntext.click();				
		}
		
		public static void savingaccountlink()
		{
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(savingaccount)));
			savingacountlink.click();				
		}
		
		public static void taxcalculationdata(int value)
		{
			try {
				
				WebDriverWait wait = new WebDriverWait(driver,5);
				hm= ExcelData_Reader.setMapData(value);	
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deposit)));
				initialdeposit_textbox.sendKeys(Keys.BACK_SPACE,hm.get("Initial_Deposit"));
				ongoingsaving_textbox.sendKeys(Keys.BACK_SPACE,hm.get("Ongoing_Savings_Text"));
				Select s1= new Select(ongoingsaving_dropdown);
				s1.selectByVisibleText(hm.get("Ongoing_Savings_Dropdown"));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(saving)));
				savingperiod_textbox1.sendKeys(Keys.BACK_SPACE,hm.get("Savings_Period"));
				interestrate_textbox.sendKeys(Keys.BACK_SPACE,hm.get("Interest_Rate"));
				Select s2= new Select(interestpaid_dropdown);
				s2.selectByVisibleText(hm.get("Interest_Paid_Dropdown"));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(taxrate)));
				Select s3= new Select(incometaxrate_dropdown);
				s3.selectByVisibleText(hm.get("IncomeTax_Dropdown"));
				logger.log(LogStatus.PASS, "Screenshot-"+"Tax Detail Page is Displayed"+":"+logger.addScreenCapture(getScreenshot(driver)));
			} catch (Exception e) {
				
				e.printStackTrace();
				logger.log(LogStatus.FAIL, "Screenshot-"+"Tax Detail Page is not Displayed"+":"+logger.addScreenCapture(getScreenshot(driver)));
			}				
		}
		

		public static void taxrate()
		{
			try {
				
				findmyrate_button.click();
				driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				NZresident_radiobutton.click();		
				individual_radiobutton.click();	
				taxableincome_radiobutton.click();
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
				String taxrate = incometaxrate_onscreen.getText();
				Assert.assertEquals(taxrate.isEmpty(), false, "Tax Rate is not displayed");
				logger.log(LogStatus.PASS, "Screenshot-"+"Final Tax Data is Displayed"+logger.addScreenCapture(getScreenshot(driver)));
		        close_and_continue.click();
			} catch (Exception e) {
				
				e.printStackTrace();
				 logger.log(LogStatus.FAIL, "Screenshot-"+"Final Tax Data is not Displayed"+logger.addScreenCapture(getScreenshot(driver)));
			}			
		}
		
		
		public static void verify_messageicon()
		{
			try {
				 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				 SoftAssert softAssertion= new SoftAssert();
				 softAssertion.assertEquals(initialdep_icon.isDisplayed(), true, "initialdeposit_icon_message" + " is not present");
				 softAssertion.assertEquals(ongoingsav_icon.isDisplayed(), true, "ongoingsaving_icon_message" + " is not present");
				 softAssertion.assertEquals(savingperiod_icon.isDisplayed(), true, "savingperiod_icon_message" + " is not present");
				 softAssertion.assertEquals(interestrate_icon.isDisplayed(), true, "interestrate_icon_message" + " is not present");
				 softAssertion.assertEquals(interestpaid_icon.isDisplayed(), true, "interestpaid_icon_message" + " is not present");
				 softAssertion.assertEquals(incometax_icon.isDisplayed(), true, "incometax_icon_message" + " is not present");		
				 softAssertion.assertAll();

			} catch (Exception e) {
				
				e.printStackTrace();
				 logger.log(LogStatus.FAIL, "Screenshot-"+"Tax Page with icon messages is not Displayed"+":"+logger.addScreenCapture(getScreenshot(driver)));
			}
		}

	}

	
	
	
	

