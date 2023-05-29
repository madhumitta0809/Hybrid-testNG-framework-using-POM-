package com.tutorialsninja.testcases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.pages.AccountPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.utils.Utilities;

public class LoginTest extends Base {

	public LoginTest()
	{
		super();
	}

	public WebDriver driver;
	LoginPage loginpage;
	AccountPage accountpage;
	@BeforeMethod
	public void setUp()
	{
		driver=initializeBrowserAndStatApplicationURL(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		loginpage=homepage.naviageToLoginPage();
	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data=Utilities.getTestDataFromExcel("Login");
		return data;
	}
	@Test(priority = 1,dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCrentials(String email,String password)
	{
		
		
		 accountpage =loginpage.login(email,password);

		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information option is not displayed");


	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCrentials()
	{
		
		
		loginpage.login(Utilities.generateTimeStamp(),dataProp.getProperty("invalidPassword"));

		String actualWarningMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");
	}
	
		
	@Test(priority =3)
	public void verifyLoginWithValidEmailAndInValidPassword()
	{
		loginpage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
		

		String actualWarningMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
	   String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");



	}

	@Test(priority =4 ,dependsOnMethods ="verifyLoginWithInValidCrentials")
	public void verifyLoginWithoutProvidingCredentials()
	{
		
		loginpage.clickOnLoginButton();
		String actualWarningMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");

	}
	
	@Test(priority=5)
			
    public void verifyLoginWithInValidEmailAndValidPassword()
	{

		
		loginpage.login(Utilities.generateTimeStamp(),prop.getProperty("validPassword"));
		
		String actualWarningMessage=loginpage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");


	}


	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}

