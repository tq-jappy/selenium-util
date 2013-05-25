package sample.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static sample.util.SeleniumTestUtil.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import sample.fixtures.SearchFixture;

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

    public SearchPage type(SearchFixture fixture) throws Exception {
        sendProperties(fixture, this);
        return this;
    }

    public SearchResultPage submit() {
        q.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(presenceOfElementLocated(By.id("resultStats")));

        return new SearchResultPage(driver);
    }
}
