package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent { // ProductCatalogue extends AbstractComponent to inherit common
														// functionalities from AbstractComponent class.

	WebDriver driver; // WebDriver instance from StandaloneTest is passed from the test class to this
						// page object class.

	public CheckoutPage(WebDriver driver) {
		super(driver); // Calls the constructor of AbstractComponent to initialize the WebDriver
						// instance.
		this.driver = driver; // Constructor to initialize the WebDriver instance. for this LandingPage class
								// and driver parameter passed from Standalone class is assigned to
								// LandingPage.driver.
		PageFactory.initElements(driver, this); // Initializes the web elements in this class using PageFactory.
	}

	// Page Object Model (POM) is a design pattern that creates an object repository
	// for web UI elements.
	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryInput; // WebElement for the country input field.

	@FindBy(xpath = "//button[contains(@class,'ta-item')]")
	WebElement countryOptionList; // WebElement for the country option button.

	// button[contains(@class,'ta-item')]/span[contains(text(),'India')]

	@FindBy(css = ".action__submit")
	WebElement submitButton; // WebElement for the submit button.

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage; // WebElement for the confirmation message after order submission.

	public void inputCountry(String countryName) {
		Actions a = new Actions(driver);

		a.sendKeys(countryInput, countryName).build().perform(); // Type the country name into the input field.
		waitForElementToAppear(By.cssSelector(".ta-results")); // Wait for the country options to appear.

	}

	public void selectCountryFromList(String countryName) {
		// Method to select a country from the list of options.
		List<WebElement> options = driver.findElements(By.cssSelector(".ta-results button")); // Get the list of country
																								// options.

		WebElement countryOption = options.stream().filter(option -> option.getText().equalsIgnoreCase(countryName)) // Filter the options to find the one that matches the country name.
				.findFirst().orElse(null); // Get the first matching option or null if not found.
		if (countryOption != null) {
			countryOption.click(); // Click on the found country option.
		} else {
			//Assert.assefail("Country not found in the list: " + countryName); // Fail the test if the country is not found.
		}
	}

	public void clickSubmitButton() {
		// Method to click the submit button to place the order.
		waitForElementToAppear(By.cssSelector(".action__submit")); // Wait for the submit button to appear.
		submitButton.click(); // Click on the submit button.
	}

	public ConfirmationPage placeOrder(String countryName) {
		inputCountry(countryName); // Input country in the input field.
		selectCountryFromList(countryName); // Select the country from the list of options.
		clickSubmitButton(); // Click the submit button to place the order.
		return new ConfirmationPage(driver); // Return a new instance of ConfirmationPage to verify the order
												// confirmation.
	}
}
