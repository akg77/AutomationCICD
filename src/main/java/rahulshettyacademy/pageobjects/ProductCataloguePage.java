package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent { // ProductCatalogue extends AbstractComponent to inherit
															// common functionalities from AbstractComponent class.

	WebDriver driver; // WebDriver instance from StandaloneTest is passed from the test class to this
						// page object class.

	public ProductCataloguePage(WebDriver driver) {
		super(driver); // Calls the constructor of AbstractComponent to initialize the WebDriver
						// instance.
		this.driver = driver; // Constructor to initialize the WebDriver instance. for this LandingPage class
								// and driver parameter passed from SubmitOrderTest class is assigned to
								// LandingPage.driver.
		PageFactory.initElements(driver, this); // Initializes the web elements in this class using PageFactory.
	}

	// Page Object Model (POM) is a design pattern that creates an object repository
	// for web UI elements.
	@FindBy(css = ".mb-3")
	List<WebElement> products; // List of products on the product catalogue page.
	@FindBy(css = "[routerlink*='cart']") // Locator for the cart icon.
	WebElement cartIcon; // WebElement for the cart icon.

	By productsBy = By.cssSelector(".mb-3"); // Locator for the product elements.
	By addToCartButton = By.cssSelector(".card-body button:last-of-type"); // Locator for the "Add to Cart" button.
	By toastMessage = By.cssSelector("#toast-container"); // Locator for the toast message that appears after adding a
															// product to the cart.
	By animation = By.cssSelector(".ng-animating"); // Locator for the animation that occurs when adding a product to
													// the cart.

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy); // Wait for the product elements to appear on the page.
		return products; // Method to return the list of products.
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null); // Filter the product list to find the product with the specified name.
		return prod; // Return the found product element.
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName); // Get the product element by name.
		prod.findElement(addToCartButton).click(); // Click on the "Add to Cart" button for the product.
		waitForElementToAppear(toastMessage); // Wait for the toast notification to appear after adding the product to
												// the cart.
		waitForElementToDisappear(animation); // Wait for the animation to complete.
	}

	public void goToCart() {
		cartIcon.click(); // Click on the cart icon to navigate to the cart page.
		waitForElementToAppear(By.cssSelector(".cartSection h3")); // Wait for the cart section to appear.
	}

}
