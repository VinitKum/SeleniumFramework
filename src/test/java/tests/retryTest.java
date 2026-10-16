package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.RetryAnalyzer;

public class retryTest {

    private int attempt = 0;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRetry() {

        attempt++;

        System.out.println("Test Attempt: " + attempt);

        if (attempt == 1) {
            Assert.fail("Intentional failure on first attempt");
        }

        System.out.println("Test passed on retry");
    }
}