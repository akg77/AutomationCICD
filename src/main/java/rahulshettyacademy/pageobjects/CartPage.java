package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponent.AbstractComponent; // Importing the AbstractComponent class to inherit common functionalities.

public class CartPage extends AbstractComponent { // ProductCatalogue extends AbstractComponent to inherit common
													// functionalities from AbstractComponent class.

	WebDriver driver; // WebDriver instance from StandaloneTest is passed from the test class to this
						// page object class.

	public CartPage(WebDriver driver) {
		super(driver); // Calls the constructor of AbstractComponent to initialize the WebDriver
						// instance.
		this.driver = driver; // Constructor to initialize the WebDriver instance. for this LandingPage class
								// and driver parameter passed from SubmitOrder class is assigned to
								// LandingPage.driver.
		PageFactory.initElements(driver, this); // Initializes the web elements in this class using PageFactory.
	}

	// Page Object Model (POM) is a design pattern that creates an object repository
	// for web UI elements.
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts; // List of products in the cart.

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton; // WebElement for the checkout button in the cart.

	public Boolean getCartProducts(String productName) {
		// Method to verify if the product is present in the cart.
		waitForElementToAppear(By.cssSelector(".cartSection h3")); // Wait for the cart section to appear.

		// Check if the product is present in the cart
		Boolean productMatch = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		//Assert.assertTrue(match, "Product not found in the cart: " + productName);
		return productMatch; // Return true if the product is found, otherwise false.
	}

	public CheckoutPage goToCheckout() {
		checkoutButton.click(); // Click on the checkout button to proceed with the order.
		waitForElementToAppear(By.cssSelector(".action__submit")); // Wait for the submit button to appear after
																	// clicking checkout.
		return new CheckoutPage(driver); // Using PlaceOrderPage page object to interact with
	}

}
