package rahulshettyacademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCataloguePage;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCataloguePage productCatalogue;
	public CartPage myCartPage;
	public CheckoutPage placeOrderPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		// Code to navigate to the e-commerce page
		landingPage = launchApplication();
	}

	// In the Given (.+) is a regular expression that matches any string.
	// We can also give {string} instead of (.+) to match any string if we know its
	// string
	// Given string should start with ^ and end with $ to tell its regular
	// expression
	@Given("^Logged in with the username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) throws InterruptedException {
		// Code to log in with the provided username and password
		productCatalogue = landingPage.loginToApplication(username, password);
	}

	@When("^I add product (.+) from cart$")
	public void i_add_product_to_cart(String productName) {
		// Code to add the specified product to the cart
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^checkout (.+) and submit the order for (.+)$")
	public void checkout_and_submit_the_order(String productName,String countryName) throws InterruptedException {
		// Code to proceed to checkout and submit the order
		myCartPage = productCatalogue.goToCartPage(); // Using MyCartPage page object to interact with cart
																// elements.
		Boolean match = myCartPage.getCartProducts(productName); // Verifying if the product is present in the cart
																	// using the method
		Assert.assertTrue(match, "Product not found in the cart: " + productName);
		// from MyCartPage class.

		placeOrderPage = myCartPage.goToCheckout(); // Using PlaceOrderPage page object to interact with
		// order placement elements.
		confirmationPage = placeOrderPage.placeOrder(countryName); // Placing the order using the method
																				// from PlaceOrderPage class.

	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String expectedMessage) {
		// Code to verify the confirmation message
		String confirmMessage = confirmationPage.verifyOrderConfirmation(); // Verifying the order confirmation using
																			// the method from ConfirmationPage class.
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), // Asserting that the confirmation
																						// message matches the expected
																						// text.
				"Order confirmation message does not match expected text.");
	}
}
