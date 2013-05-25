package sample;

import static org.junit.Assert.*
import static org.openqa.selenium.support.ui.ExpectedConditions.*

import org.junit.ClassRule
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait

import sample.fixtures.SearchFixture
import sample.pages.SearchPage
import sample.rules.WebDriverRule

/**
 *
 * @author t.endo
 */
class SearchTest {

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule()

    @Test
    void "Cheese! で検索（スタンダード）"() {
        def driver = webDriverRule.getDriver()
        driver.get("http://www.google.co.jp/")

        def element = driver.findElement(By.name("q"))
        element.sendKeys("Cheese!")
        element.submit()

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(presenceOfElementLocated(By.id("resultStats")));

        assert driver.getTitle() == "cheese! - Google 検索"
    }

    @Test
    void "Cheese! で検索（ページオブジェクトパターン）"() {
        def driver = webDriverRule.getDriver()
        def searchPage = new SearchPage(driver)

        def searchResultPage = searchPage.open().type("Cheese!").submit();

        assert driver.getTitle() == "cheese! - Google 検索"
    }

    @Test
    void "Cheese! で検索（ページオブジェクトパターン+Fixture）"() {
        def driver = webDriverRule.getDriver()
        def searchPage = new SearchPage(driver)

        def fixture = new SearchFixture("Cheese!");
        def searchResultPage = searchPage.open().type(fixture).submit();

        assert driver.getTitle() == "cheese! - Google 検索"
    }
}
