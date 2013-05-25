package sample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author t.endo
 */
public class SearchPage {

    private WebDriver driver;

    @FindBy(how = How.NAME, name = "q")
    @CacheLookup
    private WebElement q;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchPage open() {
        driver.get("http://www.google.co.jp/");
        return this;
    }

    public SearchPage type(String searchWord) {
        q.sendKeys(searchWord);
        return this;
    }

    public SearchResultPage submit() {
        q.submit();

        return new SearchResultPage(driver);
    }
}
