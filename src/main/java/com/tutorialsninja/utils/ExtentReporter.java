package com.tutorialsninja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentreports=new ExtentReports();
		File extentreportfile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\Extentreport.html");
		ExtentSparkReporter extentsparkreporter=new ExtentSparkReporter(extentreportfile);
		
		extentsparkreporter.config().setTheme(Theme.DARK);
		extentsparkreporter.config().setReportName("TutorialsNinja Automation Results");
		extentsparkreporter.config().setDocumentTitle("TutorialsNinja Automation Report");
		extentsparkreporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		
		extentreports.attachReporter(extentsparkreporter);
		Properties configprop=new Properties();
		File configpropfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\config\\config.properties");
		try {
			FileInputStream configpropfis=new FileInputStream(configpropfile);
			configprop.load(configpropfis);
		} catch (Throwable e) {
			
			e.printStackTrace();
		} 
		
			
		
		extentreports.setSystemInfo("Application URL",configprop.getProperty("url"));
		extentreports.setSystemInfo("Browser Name", configprop.getProperty("browserName"));
		extentreports.setSystemInfo("Email", configprop.getProperty("validEmail"));
		extentreports.setSystemInfo("Password", configprop.getProperty("validPassword"));
		extentreports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreports.setSystemInfo("Username",System.getProperty("user.name"));
		extentreports.setSystemInfo("Javaversion",System.getProperty("java.specification.version"));
		
		return extentreports;
		
		
		
		
		
		
		
		
		
	}

}
