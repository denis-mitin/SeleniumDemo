package auto.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;


/**
 * Created by Denis on 31.01.2018.
 */
public class SearchPage extends Page {

    private WebDriver driver;

    // define wait
    WebDriverWait wait;

    private List<WebElement> searchResults = (List<WebElement>)EMPTY_LIST;

    // Page URL
    String searchPageURL = super.siteURL; // ese/?q=

    // Page Objects
    @FindBy(xpath = "//input[@class='search-input--MU0BH']")
    public WebElement searchText;

    @FindBy(xpath = "//button[@class='icon-search2 search-go--kpr3Z']")
    WebElement startSearchButton;

    @FindBy(xpath = "//div[@class='more--dOHrW']")
    WebElement moreResults;

    @FindBy(id="ElasticSearch")
    WebElement searchResultsTable;


    public SearchPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait =  new WebDriverWait(driver, 30);
    }

    public SearchPage navigate() {

        driver.get(searchPageURL);

        // Check that we're on the right page.
        Assert.assertEquals(searchPageURL, driver.getCurrentUrl());

        return this;
    }

    public int submitSearch(String query) {

        String url = searchPageURL + "ese/?q=" + query;

        driver.get(url);


        // Check that we're on the right page.
        Assert.assertEquals(url, driver.getCurrentUrl());

        wait.until(ExpectedConditions
                .elementToBeClickable(searchResultsTable));

        String resultsPath = "//div[@style='height: 110px; clear: both; border-bottom: 1px solid #CCC;']";

        searchResults = searchResultsTable.findElements(By.xpath(resultsPath));

        return searchResults.size();
    }



    public List<WebElement> getSearchResults() {
        return this.searchResults;
    }

    /*public String getResultData(int index) {
        return searchResults.get(index).findElement(resultFileName).getText();
    }*/

}
