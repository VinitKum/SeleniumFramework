package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class BaseTest
{
	public WebDriver driver;
	
	public WebDriver getDriver()
	{
	    return driver;
	}
	
	
	@BeforeMethod
	public void setup() throws IOException
	{
		ConfigReader reader=new ConfigReader();
		driver =new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(reader.getUrl());
	
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException
	{
		//Thread.sleep(5000);
		driver.quit();
	}
}
