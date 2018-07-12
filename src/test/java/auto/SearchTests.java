package auto;


import auto.Pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.System.out;


/**
 * Created by Denis on 1.02.2018.
 */

public class SearchTests extends TestBase {

    SearchPage sp = new SearchPage(driver);

    @BeforeClass
    public static void initEnvironment() {
        // maximize browser
        out.println("maximize browser");
        driver.manage().window().maximize();

    }

    @AfterClass
    public static void afterAll() {
        out.println("Closing browser");
        driver.quit();
    }

    @Test
    public void basic_search_Test() {

        out.println(" basic_search_Test started");

        sp.navigate();

        int results = sp.submitSearch("diamond");

        out.println("Got " + results + " results");

        // Validate there are 20 results on page
        Assert.assertEquals(results, 20);

        out.println(" basic_search_Test passed");
    }

    @Test
    public void search_without_results_Test() {

        out.println(" search_without_results_Test started");

        sp.navigate();

        int results = sp.submitSearch("fgdfggdfgdfg");

        out.println("Got " + results + " results");

        Assert.assertEquals(results, 0);

        out.println(" search_without_results_Test passed");

    }
}
