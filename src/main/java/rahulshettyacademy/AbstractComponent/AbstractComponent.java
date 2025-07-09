package rahulshettyacademy.AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initializes the web elements in this class using PageFactory.
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> products; // List of products on the product catalogue page.
	@FindBy(css = "[routerlink*='cart']") // Locator for the cart icon.
	WebElement cartHeader; // WebElement for the cart icon.
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader; // WebElement for the orders icon.


	public void waitForElementToAppear(By findBy) {
		// Implementation for waiting for an element to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		// Implementation for waiting for an element to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToDisappear(By findBy) {
		// Implementation for waiting for an element to disappear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public CartPage goToCartPage() {
		// Implementation for navigating to the cart page
		cartHeader.click();
		waitForElementToAppear(By.cssSelector(".cartSection h3"));
		CartPage myCartPage = new CartPage(driver); // Using MyCartPage page object to interact with cart elements.
		return myCartPage; // Return the CartPage object to interact with cart functionalities.
	}
	public OrderPage goToOrdersPage() {
		// Implementation for navigating to the orders page
		ordersHeader.click();
		OrderPage orderPage = new OrderPage(driver); // Using OrderPage page object to interact with order elements.
		waitForElementToAppear(By.cssSelector(".orderSection h3"));
		return orderPage; // Return the OrderPage object to interact with order functionalities.
}
}
