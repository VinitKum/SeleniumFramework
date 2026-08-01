package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest
{
	public WebDriver driver;
	
	public WebDriver getDriver()
	{
	    return driver;
	}
	
	
	
//	public void setup() throws IOException
//	{
//		ConfigReader reader=new ConfigReader();
//		driver =new FirefoxDriver();
//		driver.manage().window().maximize();
//		driver.get(reader.getUrl());
//	
//	}
	
	@BeforeMethod
	public void setup() throws IOException {
		
		ConfigReader reader = new ConfigReader();

		String browser = reader.getBrowser();
		boolean headless = reader.isHeadless();
		
		if (browser.equalsIgnoreCase("chrome"))
		{
		    ChromeOptions options = new ChromeOptions();

		    if (headless)
		    {
		        options.addArguments("--headless=new");
		    }

		    driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
		    driver = new FirefoxDriver();
		}
		else if ("edge".equalsIgnoreCase(browser))
		{
		    driver = new EdgeDriver();
		}
		else
		{
		    throw new RuntimeException("Invalid Browser: " + browser);
		}

		driver.manage().window().maximize();

		driver.get(reader.getUrl());
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException
	{
		//Thread.sleep(5000);
		if (driver != null) 
		{
            driver.quit();
        }
	}
}
