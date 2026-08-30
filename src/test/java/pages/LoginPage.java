package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "username")
    WebElement userName;

    @FindBy(name="password")
    WebElement passWord;

    @FindBy(xpath ="//button[@type='submit']" )
    WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    WebElement loginError;

    //    By username = By.name("username");
    //    By password = By.name("password");
    //    By loginBtn = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) 
    	{

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    	}
	public void enterUsername(String username)
	{
	 Allure.step("Username: " +username);
        wait.until(ExpectedConditions.visibilityOf(userName)).clear();
        wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys(username);

	}
    
    public void enterPassword(String password) 
    {
    	Allure.step("Enter password");
        wait.until(ExpectedConditions.visibilityOf(passWord)).clear();
        wait.until(ExpectedConditions.visibilityOf(passWord)).sendKeys(password);

    }
    public void clickLogin() 
    {
    	Allure.step("Click login button");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

    }
    public void login(String username, String password)
    {
    	Allure.step("Perform Login");
        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }
    public boolean isLoginErrorDisplayed()
    {
        return wait.until(ExpectedConditions.visibilityOf(loginError)).isDisplayed();
    }

}