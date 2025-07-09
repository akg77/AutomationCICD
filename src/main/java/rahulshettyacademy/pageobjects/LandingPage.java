package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent { // LandingPage extends AbstractComponent to inherit common
														// functionalities from AbstractComponent class.

	WebDriver driver; // WebDriver instance from StandaloneTest is passed from the test class to this
						// page object class.

	public LandingPage(WebDriver driver) {
		super(driver); // Calls the constructor of AbstractComponent to initialize the WebDriver
						// instance.
		this.driver = driver; // Constructor to initialize the WebDriver instance. for this LandingPage class
								// and driver parameter passed from Standalone class is assigned to
								// LandingPage.driver.
		PageFactory.initElements(driver, this); // Initializes the web elements in this class using PageFactory.
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// Page Object Model (POM) is a design pattern that creates an object repository
	// for web UI elements.
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css = "[class*='flyInOut']") // Locator for the error message that appears when login fails.
	WebElement errorMessage; // WebElement for the error message that appears when login fails.

	// Method to login to the application using the provided email and password.
	public ProductCataloguePage loginToApplication(String email, String password) throws InterruptedException {
		userEmail.sendKeys(email); // Using the WebElement userEmail to send the email.
		userPassword.sendKeys(password); // Using the WebElement userPassword to send the password.
		Thread.sleep(2000); // Adding a sleep to wait for 2 seconds before clicking the login button.
		login.click(); // Using the WebElement login to click on the login button.
		ProductCataloguePage productCatalogue = new ProductCataloguePage(driver); // Using ProductCatalogue page object to
		return productCatalogue;																				// interact with product elements.
	}

	// Method to navigate to the application URL and set up the browser.
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/"); // Method to navigate to the application URL.
		
	}
	
	public String getErrorMessage() {
		//waitForElementToAppear(By.cssSelector("[class*='flyInOut']")); // Wait for the error message to appear.
		System.out.println(errorMessage.getText()); // Print the error message to the console.
		//waitForElementToDisappear(By.cssSelector("[class*='flyInOut']")); // Wait for the error message to disappear.
		return errorMessage.getText(); // Return the error message text.
}
}
