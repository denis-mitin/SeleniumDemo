package auto.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Denis on 05.01.2018.
 */
public class Browser {

    private static Browser instance = null;

    private static WebDriver driver = null;

    //String value for browser version
    public static String browser;

    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public synchronized static WebDriver getCurrentDriver() {

        // getting browser param from command line
        try {
            browser = System.getProperty("browser");
        } catch (Exception e) {
            //e.printStackTrace()
        }

        if (browser == null) {
            // if not found at command line, using default
            //browser = "FIREFOX";
            browser = "CHROME";
            System.out.println("Browser not found at command line, using " + browser);

        }

        // start new Selenium webdriver

        //System.out.println(System.getProperty("user.dir"));
        if (driver == null) {
            try {

                System.out.println("Starting browser " + browser);

                switch (browser) {
                    case "CHROME":

                        ChromeDriverService service = new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File("chromedriver.exe"))
                                .usingAnyFreePort()
                                .build();
                        ChromeOptions options = new ChromeOptions();
                        options.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
                        options.setCapability("web-security", false);
                        options.setCapability("ssl-protocol", "any");
                        options.setCapability("ignore-ssl-errors", true);
                        options.setCapability("acceptIngisecureCerts", true);
                        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        driver = new ChromeDriver(service, options);
                        break;

                    case "IE":
                        File file = new File("C:/Selenium/iexploredriver.exe");
                        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                        driver = new InternetExplorerDriver();
                        break;

                    case "FIREFOX":
                    default:
                        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                        driver = new FirefoxDriver();

                }
            } finally {
                Runtime.getRuntime().addShutdownHook(
                        new Thread(new BrowserCleanup()));
            }
        }
        return driver;
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            close();
        }
    }

    public static void close() {
        try {
            getCurrentDriver().quit();
            driver = null;
            System.out.println("closing browser for sure");
        } catch (UnreachableBrowserException e) {
            System.out.println("cannot close browser: unreachable browser");
        }
    }
}
