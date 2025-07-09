package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReporterObject() {
		// This method is intended to return an ExtentReports object for reporting purposes.
		// Implementation details would go here, such as setting up the report path, configuration, etc.
		// For example:
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//attach the reporter to the report
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anand Ghodke");
		extent.createTest(path); // Creating a test in the report.
		return extent; // Returning the ExtentReports object for further use in test cases.
	}

}
