package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    By username = By.name("username");
    By password = By.name("password");
    By loginBtn = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) 
    	{

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	}
	public void enterUsername(String username)
	{
	 Allure.step("Username: " +username);
    wait.until(ExpectedConditions.visibilityOfElementLocated(this.username)).clear();
    wait.until(ExpectedConditions.visibilityOfElementLocated(this.username)).sendKeys(username);

	}
    
    public void enterPassword(String password) 
    {
    	Allure.step("Enter passsword");
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.password)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.password)).sendKeys(password);

    }
    public void clickLogin() 
    {
    	Allure.step("Click login button");
        wait.until(ExpectedConditions.elementToBeClickable(this.loginBtn)).click();

    }
    public void login(String username, String password) {
    	Allure.step("Perform Login");
        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }

}