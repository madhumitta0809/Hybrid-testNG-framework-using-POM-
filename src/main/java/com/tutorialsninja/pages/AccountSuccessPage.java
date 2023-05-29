package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;
	@FindBy(xpath = "//h1[.='Your Account Has Been Created!']")
	private WebElement accountSuccessPageHeading;
	
	public AccountSuccessPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Actions
	public String retrieveAccountSuccessPageHeading()
	{
		String accountSuccessPageHeadingText =accountSuccessPageHeading.getText();
		return accountSuccessPageHeadingText ;
	}
}
