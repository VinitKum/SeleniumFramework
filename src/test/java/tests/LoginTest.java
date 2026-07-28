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
	
	public class LoginTest extends BaseTest
	{
	
	@Test(dataProvider="loginData")
	public void loginCheck(String username, String password,String result)
	{	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

				
	System.out.println("Current URL : " + driver.getCurrentUrl());
	System.out.println("Page Title  : " + driver.getTitle());
	if(result.equals("SUCCESS"))
	{
		wait.until(ExpectedConditions.urlContains("dashboard"));

	    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}
	else
	{
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));

	    Assert.assertTrue(driver.getPageSource().contains("Invalid credentials"));
	}
	
	}
	
	@DataProvider(name="loginData")
	public Object[][] loginData() throws IOException
	{
	    return ExcelUtility.getData();
	}	
}