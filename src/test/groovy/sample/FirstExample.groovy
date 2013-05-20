/**
 *
 */
package sample;

import static org.junit.Assert.*

import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.htmlunit.HtmlUnitDriver

/**
 *
 * @author t.endo
 */
class FirstExample {

    @Test
    void "http://code.google.com/p/selenium/wiki/GettingStarted を動かしてみる"() {
        def driver = new HtmlUnitDriver()

        driver.get("http://www.google.co.jp/")

        def element = driver.findElement(By.name("q"))

        element.sendKeys("Cheese!")

        element.submit()

        assert driver.getTitle() == "Cheese! - Google 検索"
    }
}
