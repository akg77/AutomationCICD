package rahulshettyacademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent { // ProductCatalogue extends AbstractComponent to inherit common
														// functionalities from AbstractComponent class.

	WebDriver driver; // WebDriver instance from StandaloneTest is passed from the test class to this
						// page object class.

	public ConfirmationPage(WebDriver driver) {
		super(driver); // Calls the constructor of AbstractComponent to initialize the WebDriver
						// instance.
		this.driver = driver; // Constructor to initialize the WebDriver instance. for this LandingPage class
								// and driver parameter passed from Standalone class is assigned to
								// LandingPage.driver.
		PageFactory.initElements(driver, this); // Initializes the web elements in this class using PageFactory.
	}

	// Page Object Model (POM) is a design pattern that creates an object repository
	// for web UI elements.
	

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage; // WebElement for the confirmation message after order submission.

	
	public String verifyOrderConfirmation() {
		// Method to verify the order confirmation message.
		String confirmMessage = confirmationMessage.getText(); // Get the text of the confirmation message.
		System.out.println(confirmMessage); // Print the confirmation message to the console.
		return confirmMessage; // Return the confirmation message.
	}


}
