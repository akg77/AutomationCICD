package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0; // Counter to keep track of retry attempts
	private static final int maxTry = 1; // Maximum number of retry attempts

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTry) { // Check if the current count is less than the maximum allowed retries
			count++; // Increment the retry count
			return true; // Indicate that the test should be retried
		}
		return false; // If max retries reached, do not retry
	}

}
