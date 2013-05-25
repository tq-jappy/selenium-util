package sample;

import static org.junit.Assert.*

import org.junit.ClassRule
import org.junit.Test

import sample.pages.SearchPage
import sample.rules.WebDriverRule

/**
 *
 * @author t.endo
 */
class SearchUsingPageObjectPatternTest {

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule()

    @Test
    void "Cheese! で Google 検索"() {
        def searchPage = new SearchPage(webDriverRule.getDriver())

        def searchResultPage = searchPage.open().type("Cheese!").submit();

        // assert searchResultPage.getTitle() == "Cheese! - Google 検索"
        System.out.println "First Test: " + searchResultPage.getTitle()
    }
}
