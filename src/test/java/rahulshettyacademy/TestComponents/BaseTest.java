package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver; // WebDriver instance to be used across tests.
	public LandingPage landingPage; // LandingPage instance to be used in tests.
	// BaseTest class serves as a foundation for test classes, providing common
	public WebDriver initializeDriver() throws IOException {
		// Implementation for initializing the WebDriver
		// This method can be used to set up the WebDriver instance before running
		// tests.
		// For example, setting up browser options, initializing the driver, etc.
		// This is a placeholder method and should be implemented as per the
		// requirements.

		// Properties class - to set global variables for the test execution.
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
				"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis); // File needed as input stream
		//To read values coming from mvn command we need to use System.getProperty("key") method.
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); // Fetching the browser name from the command line argument.
		
		//String browserName = prop.getProperty("browser"); // Fetching the browser name from the properties file.

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions(); // Creating ChromeOptions instance for Chrome browser.
			if (prop.getProperty("headless").equalsIgnoreCase("true")) { // Checking if headless mode is enabled.
			 	options.addArguments("--headless"); // Adding headless argument to ChromeOptions if headless mode is enabled.
			 	options.addArguments("window-size=1920,1080");

			 	}			
			 driver = new ChromeDriver(options);
			 //driver.manage().window().setSize(new Dimension(1920, 1080)); // Setting the window size for headless mode.
		} else if (browserName.equalsIgnoreCase("firefox")) {
			 driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			 driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize(); // Maximizing the browser window.
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10)); // Setting implicit wait for 10
		return driver; // Returning the WebDriver instance to be used in tests.
		
	}
	
	// DataReader class is responsible for reading data from JSON files and converting it into a suitable format for testing.
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// Convert the JSON content to a HashMap or any other data structure as needed.
		// For example, you can use a JSON library like Jackson or Gson to parse the JSON content.
		// Example using Jackson: 	
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		// This will read the JSON file and convert it into a List of HashMaps, where each HashMap represents a row in the JSON file.
		return data; // Return the list of HashMaps containing the data from the JSON file.
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver; // Casting the WebDriver instance to TakesScreenshot to capture screenshots.
		File source = ts.getScreenshotAs(OutputType.FILE); // Capturing the screenshot and storing it in a file.
		File file =new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName +".png"); // Creating a file object to specify the location where the screenshot will be saved.
		FileUtils.copyFile(source, file); // Saving the screenshot to a specified location.
		System.out.println("Screenshot taken: " + source.getAbsolutePath()); // Printing the path of the saved screenshot.
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName +".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)// This method will run before each test method.
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver(); // Initializing the WebDriver instance before launching the application.
		landingPage = new LandingPage(driver); // Creating an instance of LandingPage with the WebDriver.
		landingPage.goTo(); // Navigating to the application URL using the goTo method from LandingPage class.
		return landingPage; // Returning the LandingPage instance to be used in tests.
	}
	
	@AfterMethod(alwaysRun=true) // This method will run after each test method.
	public void tearDown() {
		driver.close(); // Closing the browser after each test method execution.
		driver = null; // Setting the WebDriver instance to null to free up resources.
		landingPage = null; // Setting the LandingPage instance to null to free up resources.
		System.gc(); // Suggesting garbage collection to free up memory.
	}
}
