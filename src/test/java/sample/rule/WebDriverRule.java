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
public class WebDriverRule extends ExternalResource {

    private static int count;

    private static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void before() {
        if (count == 0) {
            driver = new FirefoxDriver();
        }
        count++;
    }

    @Override
    public void after() {
        count--;
        if (driver != null && count == 0) {
            driver.quit();
        }
    }
}
