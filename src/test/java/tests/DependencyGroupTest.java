package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyGroupTest {

    @Test(groups = "login")
    public void validLogin() {
        System.out.println("Valid Login Executed");
        Assert.assertTrue(true);
    }

    @Test(groups = "login")
    public void verifyLoginSession() {
        System.out.println("Login Session Verified");
        //Assert.assertTrue(true);
        Assert.fail("Login session verification failed");
    }

    @Test(dependsOnGroups = "login")
    public void dashboardTest() {
        System.out.println("Dashboard Test Executed");
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        System.out.println("Logout Test Executed");
    }
}