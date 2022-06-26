package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportLoggingJsonXML4 {

//1. To have all tests irrespective of test menu ---> index.html	
	// 2. To have only fail tests ---> failed-tests-index.html

	@Test
	public void extentTest() throws IOException {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("indexLogXML.html"); // html file will be generated
		
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Framework Test Report");
		spark.config().setReportName("Extent Report for Test Cases");

		extent.attachReporter(spark); 

		ExtentTest test = extent.createTest("Login Test").assignAuthor("Shraddha").assignCategory("Smoke")
				.assignCategory("Regression").assignDevice("Chrome 90"); // create a test node in the report
		test.pass("Test Passed"); // create a test step node in test node
		test.info("URL launched");
		test.info("Value entered");

//		String jsoncode = "{\r\n"
//				+ "  \"theme\": \"STANDARD\",\r\n"
//				+ "  \"encoding\": \"utf-8\",\r\n"
//				+ "  \"protocol\": \"HTTPS\",\r\n"
//				+ "  \"timelineEnabled\": false,\r\n"
//				+ "  \"offlineMode\": true,\r\n"
//				+ "  \"thumbnailForBase64\": false,\r\n"
//				+ "  \"documentTitle\": \"ExtentReports\",\r\n"
//				+ "  \"reportName\": \"ExtentReports\",\r\n"
//				+ "  \"timeStampFormat\": \"MMM dd, yyyy HH:mm:ss a\",\r\n"
//				+ "  \"js\": \"\",\r\n"
//				+ "  \"css\": \"\"\r\n"
//				+ "}";
//		
//		test.info(jsoncode);
//		test.info("<pre>" + jsoncode.replace("\n", "<br>") + "</pre>");
//		test.info(MarkupHelper.createCodeBlock(jsoncode, CodeLanguage.JSON));
//		
		
		String xmlcode = "<extentreports>\r\n"
				+ "  <configuration>\r\n"
				+ "\r\n"
				+ "    <!-- report theme -->\r\n"
				+ "    <!-- STANDARD, DARK -->\r\n"
				+ "    <theme>DARK</theme>\r\n"
				+ "\r\n"
				+ "    <!-- document encoding -->\r\n"
				+ "    <!-- defaults to UTF-8 -->\r\n"
				+ "    <encoding>UTF-8</encoding>\r\n"
				+ "\r\n"
				+ "    <!-- protocol for script and stylesheets -->\r\n"
				+ "    <!-- defaults to https -->\r\n"
				+ "    <!-- HTTP, HTTPS -->\r\n"
				+ "    <protocol>HTTPS</protocol>\r\n"
				+ "\r\n"
				+ "    <timelineEnabled>true</timelineEnabled>\r\n"
				+ "\r\n"
				+ "  </configuration>\r\n"
				+ "</extentreports>\r\n"
				+ "";
		
		test.info(MarkupHelper.createCodeBlock(xmlcode, CodeLanguage.XML));
		test.pass("Login test Completed successfully");
		
		
		extent.flush(); // unless uou call this method. your report will not be written with logs

		Desktop.getDesktop().browse(new File("indexLogXML.html").toURI()); // open the file in desktop default browser

	}

}

// 1. Create simple report with spark reporter
// 2. add configurationn wth XML and JSON
// 3. Failed test only
// added filter to new spark and added to attach reporter
// 4. how to add json or xml code in log
//		test.info(MarkupHelper.createCodeBlock(xmlcode, CodeLanguage.XML));
