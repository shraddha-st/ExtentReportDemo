package com.ati.basepackage;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	public static WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter spark;

	@BeforeSuite
	public void setExtent() {
		spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html");

		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("OrangeHRM Test Automation Report");
		spark.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();

		extent.attachReporter(spark);

		extent.setSystemInfo("HostName", "My Host");
		extent.setSystemInfo("Project Name", "OrangeHRM");
		extent.setSystemInfo("Tester", "Jasmine");
		extent.setSystemInfo("OS", "win 7");
		extent.setSystemInfo("Browser", "Chrome");

	}

	@AfterSuite
	public void endReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");

		
	}

	@AfterMethod

	public void tearDown() {
		driver.quit();
	}

}
