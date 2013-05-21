/**
 *
 */
package sample;

import static org.junit.Assert.*

import org.junit.ClassRule
import org.junit.Test
import org.openqa.selenium.By

import sample.rule.WebDriverRule

/**
 *
 * @author t.endo
 */
class SecondTest {

    @ClassRule
    public static WebDriverRule browser = new WebDriverRule()

    @Test
    void "Chocolate! で Google 検索"() {
        def driver = browser.getDriver()
        driver.get("http://www.google.co.jp/")

        def element = driver.findElement(By.name("q"))

        element.sendKeys("Chocolate!")

        element.submit()

        // assert driver.getTitle() == "Cheese! - Google 検索"
        System.out.println "Second Test: " + driver.getTitle()
    }
}
