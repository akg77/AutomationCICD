package rahulshettyacademy.tests;

import java.io.IOException;
import rahulshettyacademy.TestComponents.Retry;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest { // SubmitOrderTest extends BaseTest to inherit common functionalities from BaseTest class.
	ExtentReports extent;
	
	@Test (groups= {"ErrorHandling"},retryAnalyzer=Retry.class)// TestNG annotation to indicate that this is a test method.
	public void loginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		String countryName = "India";
		
		
		landingPage.loginToApplication("anand1@abc.com", "Pa55word!"); // Using ProductCatalogue page object to
		//landingPage.getErrorMessage(); // Using the method from LandingPage class to get the error message.
		Assert.assertEquals("Incorrect email or password1.",landingPage.getErrorMessage()); // Asserting that the error message matches the expected text.
	}

}
