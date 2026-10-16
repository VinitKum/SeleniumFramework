package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RerunTest {

    @Test (priority=1)
    public void passedTest() {
        System.out.println("Passed Test Executed");
        Assert.assertTrue(true);
    }

    @Test (priority = 2)
    public void failedTest() {
        System.out.println("Failed Test Executed");
        Assert.fail("Intentional failure for rerun demonstration");
    }
}