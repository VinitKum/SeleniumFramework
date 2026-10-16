package tests;

import org.testng.annotations.Test;

public class FactoryTest {

    private String username;

    public FactoryTest(String username)
    {
        this.username = username;
    }

    @Test
    public void loginTest() {
        System.out.println("Testing login with user: " + username);
    }
}