package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportFailedTest3 {

//1. To have all tests irrespective of test menu ---> index.html	
	// 2. To have only fail tests ---> failed-tests-index.html

	@Test
	public void extentTest() throws IOException {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("indexAll.html"); // html file will be generated
		ExtentSparkReporter failedspark = new ExtentSparkReporter("failed-tests-index.html")
										.filter().statusFilter().as(new Status [] {Status.FAIL}).apply(); 
		
		// html file will be generated
		
		failedspark.config().setDocumentTitle("Failed Tests only");
		
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Framework Test Report");
		spark.config().setReportName("Extent Report for Test Cases");

		extent.attachReporter(spark, failedspark); 

		ExtentTest test = extent.createTest("Login Test").assignAuthor("Shraddha").assignCategory("Smoke")
				.assignCategory("Regression").assignDevice("Chrome 90"); // create a test node in the report
		test.pass("Test Passed"); // create a test step node in test node
		test.info("URL launched");
		test.info("Value entered");
		test.pass("Login test Completed successfully");

		// create a test node in the report
		ExtentTest test2 = extent.createTest("HomePage Test").assignAuthor("Thakur").assignCategory("Smoke")
				.assignDevice("Firefox");
		test2.pass("Test Passed"); // create a test step node in test node
		test2.info("URL launched");
		test2.info("Value entered");
		test2.fail("Home page failed");

		extent.flush(); // unless uou call this method. your report will not be written with logs

		Desktop.getDesktop().browse(new File("indexAll.html").toURI()); // open the file in desktop default browser
		Desktop.getDesktop().browse(new File("failed-tests-index.html").toURI()); // open the file in desktop default browser

	}

}

// 1. Create simple report with spark reporter
// 2. add configurationn wth XML and JSON
// 3. Failed test only
// added filter to new spark and added to attach reporter
