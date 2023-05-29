package com.tutorialsninja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	public static String generateTimeStamp()
	{
		  String timestamp=java.time.LocalTime.now().toString().replace(":", "_");
		  return "amotooricap9"+timestamp+"@gmail.com";
		
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName)
	{  
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook=null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			
			e.printStackTrace();
		} 
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		
		Object[][] data=new Object[rows][cols];
		for(int i=0;i<rows;i++)
		{
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<cols;j++)
			{
				XSSFCell cell=row.getCell(j);
				DataFormatter df=new DataFormatter();
				String value=df.formatCellValue(cell);
				data[i][j]=value;
			}
		}
		return data;
	}
	public static String captureScreenshot(WebDriver driver,String testname)
	{
		TakesScreenshot ts=((TakesScreenshot)driver);
		File srcscreenshot=ts.getScreenshotAs(OutputType.FILE);
		File destinationscreenshot=new File("./Screenshots/"+testname+".png");
		String destinationPath=System.getProperty("user.dir")+"\\Screenshots\\"+testname+".png";
		try {
			FileUtils.copyFile(srcscreenshot, destinationscreenshot);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationPath;
	}
}
