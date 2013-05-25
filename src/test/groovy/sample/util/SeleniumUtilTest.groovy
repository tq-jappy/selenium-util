package sample.util;

import static org.junit.Assert.*

import org.junit.ClassRule
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.How

import sample.fixtures.AbstractFixture
import sample.pages.AbstractPage
import sample.rules.WebDriverRule

class SeleniumUtilTest {

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule()


    @Test
    void "sendProperties() を使って基本的なformパーツをマッピングできる"() {
        def driver = webDriverRule.driver
        def page = new TestPage(driver).open()

        def data = new Fixture()
        data.a = "aaa"
        data.b = true
        data.d = "5"
        data.e = "eee"
        SeleniumUtil.sendProperties(data, page)

        assert page.a.getAttribute("value") == "aaa"
        assert page.b.isSelected() == true
        assert page.e.getText() == "eee"
    }

    private static class TestPage extends AbstractPage {

        @FindBy(how = How.ID, id = "a")
        WebElement a

        @FindBy(how = How.ID, id = "b")
        WebElement b

        @FindBy(how = How.ID, id = "d")
        WebElement d

        @FindBy(how = How.ID, id = "e")
        WebElement e

        TestPage(WebDriver driver) {
            super(driver)
        }

        TestPage open() {
            def htmlFile = new File("src/test/resources/test.html")
            def url = "file:///" + htmlFile.absolutePath.replaceAll("\\\\", "/").replaceAll(" ", "%20")
            driver.get(url)
            return this
        }
    }

    private static class Fixture extends AbstractFixture {

        String a // text
        boolean b // checkbox
        String d // select
        String e // textarea
    }
}
