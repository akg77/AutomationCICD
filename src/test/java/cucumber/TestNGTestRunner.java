package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",
glue = "rahulshettyacademy.StepDefinitions",
tags= "@Regression",
monochrome=true,
plugin = { "pretty", "html:target/cucumber-reports.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	// This class is used to run Cucumber tests with TestNG.
	// The @CucumberOptions annotation specifies the location of the feature files,
	// the step definitions, and other options like report generation.
	
}
