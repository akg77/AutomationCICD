package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.data.DataReader;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCataloguePage;


public class SubmitOrderTest extends BaseTest { // SubmitOrderTest extends BaseTest to inherit common functionalities from BaseTest class.
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData") // TestNG annotation to indicate that this is a test method.
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		String countryName = "India";
		//WebDriver driver = new ChromeDriver();
		//LandingPage landingPage = launchApplication();; // Using Page Object Model. Created LandingPage which has the
							// WebDriver instance sent from here. LandingPage is the page object class.
		//landingPage.goTo(); // Using the method goTo from LandingPage class to navigate to the application
							// URL and set up the browser.
		 // Launching the application using the method from BaseTest class.
		ProductCataloguePage productCatalogue = landingPage.loginToApplication(input.get("email"), input.get("password")); // Using ProductCatalogue page object to
																			// interact with product elements.
		productCatalogue.addProductToCart(input.get("productName")); // Adding the product to the cart using the method from ProductCatalogue class.
		productCatalogue.goToCartPage(); // Navigating to the cart using the method from ProductCatalogue class.
		
		CartPage myCartPage = productCatalogue.goToCartPage(); // Using MyCartPage page object to interact with cart elements.
		Boolean match = myCartPage.getCartProducts(input.get("productName")); // Verifying if the product is present in the cart using the method
		Assert.assertTrue(match, "Product not found in the cart: " + input.get("productName"));
													// from MyCartPage class.
		
		CheckoutPage placeOrderPage = myCartPage.goToCheckout(); // Using PlaceOrderPage page object to interact with
																	// order placement elements.
		ConfirmationPage confirmationPage = placeOrderPage.placeOrder(countryName); // Placing the order using the method from PlaceOrderPage class.
		String confirmMessage = confirmationPage.verifyOrderConfirmation(); // Verifying the order confirmation using the method from ConfirmationPage class.
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), // Asserting that the confirmation message matches the expected text.
				"Order confirmation message does not match expected text.");
		//driver.close();
	}
	
	//To verify ZARA COAT 3 is displaying in orders
	@Test(dependsOnMethods = {"submitOrder"}) // This test will run after the submitOrder test.
	public void OrderHistoryTest() throws IOException, InterruptedException {
		ProductCataloguePage productCatalogue = landingPage.loginToApplication("anand@abc.com", "Pa55word!"); // Using ProductCatalogue page object to
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		orderPage.verifyOrderDisplay(productName); // Verifying the order history using the method from OrderPage class.
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName), "Order history does not display the expected product: " + productName);
	}
	
	@DataProvider // DataProvider annotation to provide data for the test method using String array
	public Object[][] getData1() {
		return new Object[][] {{"anand@abc.com","Pa55word!","ZARA COAT 3"},{"anand@abc.com","Pa55word!","ADIDAS ORIGINAL"}};
		}
	
	@DataProvider // DataProvider annotation to provide data for the test method using HashMap
	public Object[][] getData2() {
		HashMap<String, String> map = new HashMap<String, String>(); // Creating a HashMap to store data.
		map.put("email", "anand@abc.com");
		map.put("password", "Pa55word!");
		map.put("productName", "ZARA COAT 3");
		HashMap<String, String> map1 = new HashMap<String, String>(); // Creating another HashMap to store data.
		map1.put("email", "anand@abc.com");
		map1.put("password", "Pa55word!");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}}; // Returning an array of HashMaps as data for the test method.
		}
	@DataProvider // DataProvider annotation to provide data for the test method using HashMap
	public Object[][] getData() throws IOException {
		// DataReader class is responsible for reading data from JSON files and converting it into a suitable format for testing.
		List<HashMap<String, String>> dataList = null; // Initializing a List of HashMaps to store data.
		
		try {
			dataList = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"); // Calling the method from DataReader class to read data from JSON file.;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Calling the method from DataReader class to read data from JSON file.
	
		return new Object[][] {{dataList.get(0)},{dataList.get(1)}}; // Returning an array of HashMaps as data for the test method.
		}
	

	
	//Extent Reports is a reporting library that provides detailed reports for test execution.
}
