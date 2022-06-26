package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AttachLogo6 {

	/**
	 *
	 * 1.Attach company logo to extent report
	 * 2. part II how to config xml files to set up extent report (XML config file)
	 * 
	 * 
	 */
	
	@Test
	public void attachLogoTest() throws IOException {
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("indexAttachLogo.html");

		spark.loadXMLConfig(new File ("extentConfig.xml"));
		extent.attachReporter(spark);

		ExtentTest test = extent.createTest("First Test");
		test.pass("Test Started");
		test.pass("Test Finished");
		
		
		
		extent.flush();
		
		Desktop.getDesktop().browse(new File ("indexAttachLogo.html").toURI());
		
		
	}
}
