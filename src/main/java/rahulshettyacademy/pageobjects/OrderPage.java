package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
	WebDriver driver;
	
	@FindBy(css = ".totalRow button") // Locator for the order list items.
	List<WebElement> orderList; // List of orders displayed on the order page.
	
	@FindBy(css = "tr td:nth-child(3)") // Locator for the order section header.
	List<WebElement> productNames; // WebElement for the order section header.
	
	
	public OrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initializes the web elements in this class using PageFactory.
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		// Implementation for verifying if the order is present in the order history
		// This method should return true if the order is found, false otherwise.
		Boolean matchProduct = productNames.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return matchProduct; // Placeholder return value, actual implementation needed.
	}
	

}
