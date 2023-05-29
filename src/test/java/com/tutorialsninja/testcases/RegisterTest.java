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
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.pages.AccountSuccessPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.RegisterPage;
import com.tutorialsninja.utils.Utilities;

public class RegisterTest extends Base{

	public RegisterTest()
	{
		super();
	}

	public WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	@BeforeMethod
	public void setUp()
	{   
		driver=initializeBrowserAndStatApplicationURL(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		 registerpage=homepage.navigateToRegisterPage();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}


	@Test(priority = 1) 
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		accountsuccesspage=registerpage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"), Utilities.generateTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		
		String actualSuccessHeading=accountsuccesspage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");


	}
	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllFields()
	{
		
		accountsuccesspage=registerpage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"), Utilities.generateTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String actualSuccessHeading=accountsuccesspage.retrieveAccountSuccessPageHeading();;
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");

	}
	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress()
	{
		
		registerpage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		


		//driver.findElement(By.name("newsletter")).click();


		String actualWarning=registerpage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message is not displayed");

	}
	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails()
	{   
		
		registerpage.clickOnContinueButton();
		
		String ActualprivacyPolicyWarning=registerpage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(ActualprivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy warning is not displayed");

		String actualFirstNameWarning=registerpage.retrieveFirstNameWarning();
		Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("firstNameWarning")),"First name warning is not displayed");

		String actualLastNameWarning=registerpage.retrieveLastNameWarning();
		Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("lastNameWarning")),"Last name warning is not displayed");

		String actualEmailWarning=registerpage.retrieveEmailWarning();
		Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("emailWarning")),"Email warning is not displayed");


		String actualTelephoneWarning=registerpage.retrieveTelephoneWarning();
		Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("telephoneWarning")),"Telephone warning is not displayed");

		String actualPasswordWarning=registerpage.retrievePasswordWarning();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("passwordWarning")),"Password warning is not displayed");


	}




}
