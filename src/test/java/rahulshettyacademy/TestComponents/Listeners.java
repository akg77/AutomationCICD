package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal(); //Thread Safe
	// Implement methods from ITestListener interface to handle test events
	@Override
	public void onTestStart(ITestResult result) {
		// Code to execute when a test starts
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // Set the test in ThreadLocal for thread safety and assign one unique test instance per thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Code to execute when a test passes
		extentTest.get().log(Status.PASS, "Test passed successfully: " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Code to execute when a test fails
		//test.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		String screenshotPath=null;
		try {
			screenshotPath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//take Screenshot , attach to report
		extentTest.get().addScreenCaptureFromPath(screenshotPath,result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Code to execute when a test is skipped
	}

	@Override
	public void onFinish(ITestContext context) {
		// Code to execute after all tests in the context have finished
		extent.flush(); // Flush the extent report to write all logs to the report file
	}

}
