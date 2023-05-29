package com.tutorialsninja.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.SearchPage;

public class SearchTest extends Base{
	
	public SearchTest()
	{
		super();
	}
	
	public WebDriver driver;
	SearchPage searchpage;
	HomePage homepage;
	
	@BeforeMethod
	public void setUp()
	{   
		driver=initializeBrowserAndStatApplicationURL(prop.getProperty("browserName"));
		 homepage=new HomePage(driver);
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
		
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		
	 searchpage=homepage.searchForAProduct(dataProp.getProperty("validProduct"));
		
		Assert.assertTrue(searchpage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in seach results");
	}
	@Test(priority = 2)
	public void verifySearchWithInValidProduct()
	{
		
		searchpage=homepage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		
		Assert.assertTrue(searchpage.displayStatusOfNoProductMessageText(),"No product message is not displayed");
	}
	
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct()
	{
		
		searchpage=homepage.clickOnSearchButton();
		

		
		Assert.assertTrue(searchpage.displayStatusOfNoProductMessageText(),"No product message is not displayed");
	}
}
