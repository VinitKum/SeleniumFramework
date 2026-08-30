package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.lang.String;
import org.testng.Assert;
import pages.DashboardPage;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

import utilities.DriverManager;
import pages.LoginPage;

public class LoginSteps
{
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    @Given("user is on the OrangeHRM login page")
    public void user_is_on_login_page() {

        loginPage = new LoginPage(DriverManager.getDriver());

       // System.out.println("User is on OrangeHRM login page");
    }

    @When("user enters {string} and {string}")
    public void user_enters_following_credentials(String username, String password)
    {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    @When("user enters following credentials from DataTable")
    public void user_enters_credentials_from_data_table(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps();

        for (Map<String, String> row : data) {

            String username = row.get("username");
            String password = row.get("password");

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
        }
        loginPage.clickLogin();
    }
    @When("user enters following message")
    public void user_enters_following_message(String message) {

        System.out.println("Message received:");
        System.out.println(message);
    }
    @When("user enters following credentials")
    public void user_enters_following_CredentialsFromBackGroundFeatureFile(
            DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps();

        for (Map<String, String> row : data) {

            String username = row.get("username");
            String password = row.get("password");

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
        }
    }

    @And("user clicks on login button")
    public void user_clicks_on_login_button() {

    System.out.println("Login button clicked");
    loginPage.clickLogin();
    }
    @Then("user should be successfully logged in")
    public void user_should_be_successfully_logged_in() {

        dashboardPage = new DashboardPage(DriverManager.getDriver());

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard was not displayed after login"
        );
    }
    @Then("login error message should be displayed")
    public void user_should_get_ErrorMessage_logged_in() {

                Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Login error message was not displayed"
        );
    }

}