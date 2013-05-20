/**
 *
 */
package sample.rule;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author t.endo
 */
public class WebBrowser extends ExternalResource {

    private static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void before() {
        System.out.println("before");
        if (driver == null) {
            driver = new FirefoxDriver();
        }
    }

    @Override
    public void after() {
        System.out.println("after");
        if (driver != null) {
            driver.quit();
        }
    }
}
