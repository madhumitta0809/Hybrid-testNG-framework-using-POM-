package com.tutorialsninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.tutorialsninja.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() 
	{
		prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\config\\config.properties");
		try {
			FileInputStream fis=new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		
		dataProp=new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\testdata\\testdata.properties");
        try {
			FileInputStream dataFis=new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		} catch (Throwable e) {
			
			e.printStackTrace();
		} 
	}

	public WebDriver initializeBrowserAndStatApplicationURL(String browserName)
	{


		if(browserName.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			driver=new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		//driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));

		driver.get(prop.getProperty("url"));
		return driver;
	}
}
