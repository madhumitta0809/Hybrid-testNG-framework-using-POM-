package com.tutorialsninja.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.utils.ExtentReporter;
import com.tutorialsninja.utils.Utilities;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.Instance;

public class MyListeners implements ITestListener {
	
	ExtentReports extentreports;
	ExtentTest extentTest;
	String testname;
	
	@Override
	public void onStart(ITestContext context) {
		
		extentreports=ExtentReporter.generateExtentReport();
		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		 testname=result.getName();
	 extentTest=extentreports.createTest(testname);
		extentTest.log(Status.INFO, testname+" Started executing");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS,testname+" Successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		
	try {
		driver=	(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	String destinationPath=Utilities.captureScreenshot(driver, testname);
	extentTest.addScreenCaptureFromPath(destinationPath);
	
	
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL,testname+" Failed" );
		
		
				
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP,testname+" test skipped" );
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentreports.flush();
		String extentReportpath=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\Extentreport.html";
		File extentReport=new File(extentReportpath);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Throwable e) {
		
			e.printStackTrace();
		}
	}

}
