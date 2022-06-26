package com.extentreports.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AttachScreenShot7 {

	/**
	 * 1. Take a screenshot as png, jpg file >> Attach to report
	 * 2. Take a screenshot as png, jpg file >> Convert to form of Base64 >> Attach to report
	 * 3. Take a screenshot as base64 >> Attach to report (mose recommended)
	 * @throws IOException 
	 * 
	 */
	
	ExtentReports extent ;
	WebDriver driver;
	
	@BeforeSuite
	private void setUp() throws IOException {
		 extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("indexScreenShot.html");

		spark.loadXMLConfig(new File ("extentConfig.xml"));
		extent.attachReporter(spark);

	}
	
	@AfterSuite
	private void tearDown() throws IOException {
		// TODO Auto-generated method stub
		extent.flush();
		
		Desktop.getDesktop().browse(new File ("indexScreenShot.html").toURI());
		driver.quit();
		
	} 
	
	@Test
	public void attachScreenShotTest() throws IOException {

		ExtentTest test = extent.createTest("attachScreenShotTest");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		 
		driver.get("https://google.com"); 
		driver.findElement(By.name("q")).sendKeys("Automation testing", Keys.ENTER);
		
		test.pass("Local screenshot.png", MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath()).build());
		test.pass("Local screenshot.png converted to base64", MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotAsBase64()).build());
		
		test.pass("base64 image", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
		
		
		test.pass("Browser Opened");
		test.pass("Test Finished");
		
	}
// this will add extra burden to folder not able to send in email due to size
	private String getScreenShotPath() throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/image.png";
		FileUtils.copyFile(source, new File(path));
		return path;
	}

	private String getScreenShotAsBase64() throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/image.png";
		FileUtils.copyFile(source, new File(path));
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
		
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	private String getBase64() {
			
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
// test.Log(LogStatus.Fail, "Error Snapshot : " + test.AddBase64ScreenCapture(imgFormat));
	}
	
}
