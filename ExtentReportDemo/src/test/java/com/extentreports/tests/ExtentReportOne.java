package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportOne {

	@Test
	
	public void extentTest() throws IOException {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html"); // html file will be generated

	
		// https://www.extentreports.com/docs/versions/5/java/spark-reporter.html
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Framework Test Report");
		spark.config().setReportName("Extent Report for Test Cases");
		extent.attachReporter(spark);
		
		
		ExtentTest test = extent.createTest("Login Test").assignAuthor("Shraddha").assignCategory("Smoke").assignCategory("Regression").assignDevice("Chrome 90"); // create a test node in the report
		test.pass("Test Passed"); // create a test step node in test node
		test.info("URL launched");
		test.info("Value entered");
		test.pass("Login test Completed successfully");
		
		
		
		// create a test node in the report
		ExtentTest test2 = extent.createTest("HomePage Test").assignAuthor("Thakur").assignCategory("Smoke").assignDevice("Firefox"); 
		test2.pass("Test Passed"); // create a test step node in test node
		test2.info("URL launched");
		test2.info("Value entered");
		test2.fail("Home page failed");
		
		
		extent.flush(); // unless uou call this method. your report will not be written with logs
		
		Desktop.getDesktop().browse(new File("indexConfigJSON.html").toURI()); // open the file in desktop default browser
		
	}
	
}


// 1. Create simple report with spark reporter
// 2. add configurationn wth XML and JSON
// 3. 
