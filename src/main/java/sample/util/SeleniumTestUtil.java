package sample.util;

import java.lang.reflect.Field;

import org.openqa.selenium.WebElement;

import sample.fixtures.AbstractFixture;
import sample.pages.AbstractPage;

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
    public static void sendProperties(AbstractFixture fixture, AbstractPage page)
            throws IllegalAccessException, NoSuchFieldException {

        Class<?> fixtureClass = fixture.getClass();
        Class<?> pageClass = page.getClass();

        for (Field pageField : pageClass.getDeclaredFields()) {
            // ページクラス側の WebElement を取り出す
            if (!pageField.getType().equals(WebElement.class)) {
                continue;
            }
            pageField.setAccessible(true);
            WebElement element = (WebElement) pageField.get(page);

            // 同じフィールド名をもつ FixtureClass 側の Object の値を取り出す
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
            } else if (value instanceof String) {
                sendString(element, (String) value);
            } else if (value instanceof Boolean) {
                sendBoolean(element, (Boolean) value);
            } else {
                throw new AssertionError("unsupported class: "
                        + value.getClass().getName());
            }
        }
    }

    private static void sendString(WebElement element, String value) {
        element.sendKeys(value);
    }

    private static void sendBoolean(WebElement element, boolean selected) {
        if (element.isSelected() != selected) {
            element.click();
        }
    }

    private SeleniumTestUtil() {
    }
}
