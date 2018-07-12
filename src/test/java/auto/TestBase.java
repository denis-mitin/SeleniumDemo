package auto;

import auto.Config.Browser;
import auto.Config.Config;
import auto.Pages.LoginPage;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestBase {


    //Get config singletone containing siteURL, users;
    public static Config conf = Config.getInstance();

    // Get Selenium browser driver instance
    public static WebDriver driver = Browser.getInstance().getCurrentDriver();

    // get users list from configuration object
    static JSONObject users = conf.getUsers();

    public void login(String user) {

        System.out.println("Login as " + user + " started");
        LoginPage lp = new LoginPage(driver);

        lp.navigate();

        JSONObject u = users.getJSONObject(user);

        lp.submitLogin(u.getString("username"),u.getString("password") );

        WebDriverWait wait =  new WebDriverWait(driver, 60);

        String path = "//div[@class='logged-in--3V7Y0']/a[1]";

        //WebElement bage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
        WebElement bage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));

        String first= u.getString("firstname");
        String last = u.getString("lastname");

        //Assert.assertEquals( first + " " + last,  bage.getText());
        Assert.assertEquals( first + " " + last,  bage.getAttribute("text"));

        System.out.println("Login as " + user + " success");
    }
}
