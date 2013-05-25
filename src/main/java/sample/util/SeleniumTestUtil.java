package sample.util;

import java.lang.reflect.Field;

import org.openqa.selenium.WebElement;

import sample.fixtures.SearchFixture;
import sample.pages.SearchPage;

/**
 *
 * @author t.endo
 */
public final class SeleniumTestUtil {

    /**
     * fixture 内のプロパティの値を page 内の同名フィールドの WebElement に入力する。
     *
     * @param fixture
     *            入力値を格納している Fixture
     * @param page
     *            入力先のページオブジェクト
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static void sendProperties(SearchFixture fixture, SearchPage page)
            throws IllegalAccessException, NoSuchFieldException {

        Class<?> fixtureClass = fixture.getClass();
        Class<?> pageClass = page.getClass();

        for (Field pageField : pageClass.getDeclaredFields()) {
            if (!pageField.getType().equals(WebElement.class)) {
                continue;
            }
            pageField.setAccessible(true);

            WebElement element = (WebElement) pageField.get(page);

            Field fixtureField = null;
            try {
                fixtureField = fixtureClass.getDeclaredField(pageField
                        .getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            fixtureField.setAccessible(true);

            Object value = fixtureField.get(fixture);
            if (value == null) {
                continue;
            } else if (!(value instanceof String)) {
                continue;
            } else {
                element.sendKeys((String) value);
            }
        }
    }

    private SeleniumTestUtil() {
    }
}
