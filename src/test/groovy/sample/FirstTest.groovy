/**
 *
 */
package sample;

import static org.junit.Assert.*

import org.junit.Test
import org.openqa.selenium.By

import sample.rule.WebBrowser

/**
 *
 * @author t.endo
 */
class FirstTest {

    public static WebBrowser browser = new WebBrowser()

    @Test
    void "Cheese! で Google 検索"() {
        def driver = browser.getDriver()
        driver.get("http://www.google.co.jp/")

        def element = driver.findElement(By.name("q"))

        element.sendKeys("Cheese!")

        element.submit()

        // assert driver.getTitle() == "Cheese! - Google 検索"
        System.out.println "First Test: " + driver.getTitle()
    }
}
