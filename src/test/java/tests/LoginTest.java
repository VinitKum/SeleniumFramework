package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utilities.ExcelUtility;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginTest extends BaseTest {

	@Epic("OrangeHRM")
	@Feature("Login Module")
	@Story("Verify Login using Valid and Invalid Credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify Login Functionality using multiple credentials")
	@Test(dataProvider="loginData")

    public void loginCheck(String username, String password, String result) {
		
		Allure.getLifecycle().updateTestCase(testResult ->
        testResult.setName(username + " - " + result));

		LoginPage loginPage = new LoginPage(getDriver());

		loginPage.login(username, password);		

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        System.out.println("Current URL : " + getDriver().getCurrentUrl());
        System.out.println("Page Title  : " + getDriver().getTitle());

        if (result.equalsIgnoreCase("SUCCESS")) 
        {

        	Assert.assertTrue(true);

            wait.until(ExpectedConditions.urlContains("dashboard"));

            Assert.assertTrue(getDriver().getCurrentUrl().contains("dashboard"));

        }

        else {

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));

            Assert.assertTrue(getDriver().getPageSource().contains("Invalid credentials"));

        }

    }

    @DataProvider(name = "loginData", parallel = false)
    public Object[][] loginData() throws IOException {

        return ExcelUtility.getData();

    }

}