package sample;

import static org.junit.Assert.*

import org.junit.ClassRule
import org.junit.Test
import org.openqa.selenium.By

import sample.rules.WebDriverRule

/**
 *
 * @author t.endo
 */
class SearchTest {

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule()

    @Test
    void "Cheese! で Google 検索"() {
        def driver = webDriverRule.getDriver()
        driver.get("http://www.google.co.jp/")

        def element = driver.findElement(By.name("q"))

        element.sendKeys("Cheese!")

        element.submit()

        // assert driver.getTitle() == "Cheese! - Google 検索"
        System.out.println "First Test: " + driver.getTitle()
    }
}
