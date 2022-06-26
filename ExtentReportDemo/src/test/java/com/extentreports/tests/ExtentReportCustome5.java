package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReportCustome5 {
/**
 * 1. change the viewing order
 * 2. remove some menu
 * 3. highlight a log line
 * 4. List of String => how can i log in report
 * 5. Map<String, String> ==> how can i log in report
 * 
 */
	
	@Test
	public void extentTest() throws IOException {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("indexCustom.html")
						.viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY})
						.apply(); // main apply

	
		// https://www.extentreports.com/docs/versions/5/java/spark-reporter.html
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Framework Test Report");
		spark.config().setReportName("Extent Report for Test Cases");
		
		extent.attachReporter(spark);
		
		
		ExtentTest test = extent.createTest("Login Test").assignAuthor("Shraddha").assignCategory("Smoke").assignCategory("Regression").assignDevice("Chrome 90"); // create a test node in the report
		test.pass("Test Passed"); // create a test step node in test node
		test.info("URL launched");
		test.info("Value entered");
//		Arrays.asList(new String[] {"Selinium", "Appium", "Rest Assured"}).forEach(test :: pass);
		
		test.pass(MarkupHelper.createOrderedList(Arrays.asList(new String[] {"Selenium", "Appium", "Rest Assured"})).getMarkup());
		test.pass(MarkupHelper.createUnorderedList(Arrays.asList(new String[] {"Selenium", "Appium", "Rest Assured"})).getMarkup());
		
		
		Map<String,String> map1 = new HashMap<>();
		map1.put("fname", "Jass");
		map1.put("lname", "mine");
		map1.put("role", "QA");
		
//		map1.forEach((k,v)->test.pass(k+ " : " + v));
		
		test.pass(MarkupHelper.createOrderedList(map1).getMarkup());
		test.pass(MarkupHelper.createLabel("Login test Completed successfully", ExtentColor.GREEN));
		
		
		
		
		// create a test node in the report
		ExtentTest test2 = extent.createTest("HomePage Test").assignAuthor("Thakur").assignCategory("Smoke").assignDevice("Firefox"); 
		test2.pass("Test Passed"); // create a test step node in test node
		test2.info("URL launched");
		test2.info("Value entered");
		test2.fail("Home page failed");
		test2.fail(MarkupHelper.createLabel("Home page failed", ExtentColor.RED));
		
		
		extent.flush(); // unless uou call this method. your report will not be written with logs
		
		Desktop.getDesktop().browse(new File("indexCustom.html").toURI()); // open the file in desktop default browser
		
	}
	
}


//1. Create simple report with spark reporter
//2. add configurationn wth XML and JSON
//3. Failed test only
//added filter to new spark and added to attach reporter
//4. how to add json or xml code in log
//		test.info(MarkupHelper.createCodeBlock(xmlcode, CodeLanguage.XML));

//5. custom report
/**
 * 1. change the viewing order
 * 2. remove some menu
 * 3. highlight a log line
 * 4. List of String => how can i log in report
 * 5. Map<String, String> ==> how can i log in report
 * 
 */
//6. Attach logo to report
// configure in xml config

/**
 *
 * 1.Attach company logo to extent report
 * 2. part II how to config xml files to set up extent report (XML config file)
 * 
 */
//7. attach screenshot in 3 ways 
/**
 * 1. Take a screenshot as png, jpg file >> Attach to report
 * 2. Take a screenshot as png, jpg file >> Convert to form of Base64 >> Attach to report
 * 3. Take a screenshot as base64 >> Attach to report (mose recommended)
 * @throws IOException 
 * 
 */
