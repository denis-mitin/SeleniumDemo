package auto.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * Created by Denis on 31.01.2018.
 */
public class LoginPage extends Page {

    private final WebDriver driver;

    WebDriverWait wait;

    String loginPageURL = super.siteURL; //+ "login.html";

    final String wrongPassError = "Your Email and/or Password is incorrect";


    @FindBy(xpath = "//span[@class='login-menu-text--18Mba']") //<span class="login-menu-text--18Mba">LOGIN</span>
    WebElement loginLink;

    @FindBy(id="loginEmail")
    WebElement loginEmailText;

    @FindBy(id="loginPassword")
    WebElement passwordText;

    @FindBy(id="ContinueLoginBtn")
    WebElement loginButton;

    @FindBy(id = "invalidLogin")
    WebElement loginErrorText;


    public LoginPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

        // define common wait value in seconds
        wait = new WebDriverWait(driver, 30);
    }


    public LoginPage navigate(){

        driver.get(loginPageURL);

        // wait for element to load
        wait.until(ExpectedConditions
                .elementToBeClickable(loginLink));

        // Check that we're on the right page.
        Assert.assertEquals(loginPageURL, driver.getCurrentUrl());

        loginLink.click();

        // wait for element to load
        wait.until(ExpectedConditions
                .elementToBeClickable(loginEmailText));

        return this;
    }


    // The login page allows the user to type their username into the username field
    public LoginPage typeUsername(String username) {
        // This is the only place that "knows" how to enter a username
        loginEmailText.sendKeys(username);

        // Return the current page object as this action doesn't navigate to a page represented by another PageObject
        return this;
    }

    // The login page allows the user to type their password into the password field
    public LoginPage typePassword(String password) {
        // This is the only place that "knows" how to enter a password
        passwordText.sendKeys(password);
        return this;
    }

    // The login page allows the user to submit the login form
    public LoginPage submitLogin(String username, String password) {

        typeUsername(username);
        typePassword(password);

        //click on signIn button
        loginButton.click();
        return new LoginPage(driver);
    }

    // The login page allows the user to submit the login form knowing that an invalid username and / or password were entered
    public LoginPage submitLoginExpectingFailure() {
        // This is the only place that submits the login form and expects the destination to be the login page due to login failure.
        loginButton.click();

        // wait for element to load
        wait.until(ExpectedConditions
                .elementToBeClickable(loginErrorText));

        // verify error message appeared, cant verify since there is no error message on page
        String error = loginErrorText.getText();
        Assert.assertEquals(wrongPassError, error);

        //validate user remains on login page
        Assert.assertTrue(loginEmailText.isDisplayed());

        return this;
    }

 }
