package auto;

import auto.Pages.LoginPage;
import org.testng.annotations.*;

import static auto.Config.Config.ENVIRONMENT;
import static java.lang.System.out;


/**
 * Created by Denis on 31.01.2018.
 */

public class LoginTests extends TestBase {


    @BeforeClass
    public static void initEnvironment() {

        out.println("Starting Login tests on environment = " + ENVIRONMENT);

        // maximize browser
        out.println("maximize browser");
        driver.manage().window().maximize();

        // clear cookies
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void afterAll() {
        out.println("Closing browser");
        driver.quit();
    }

    @Test
    public void login_invalid_user_and_pass_Test() {

        out.println("login_with_wrong_user_and_pass test started");

        LoginPage lp = new LoginPage(driver);

        lp.navigate();

        lp.typeUsername("admin");
        lp.typePassword("dfdsf");

        lp.submitLoginExpectingFailure();

        out.println("login_with_wrong_user_and_pass test passed");

    }

    @Test
    public void login_with_existing_user_Test() {

        out.println("login_with_existing_user test started");

        super.login("user1");

        out.println("login_with_existing_user test passed");
    }

}
