package sample.util;

import java.lang.reflect.Field;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import sample.fixtures.AbstractFixture;
import sample.pages.AbstractPage;

/**
 *
 * @author t.endo
 */
public final class SeleniumUtil {

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
            }

            if (isElementSelect(element)) {
                if (value instanceof String) {
                    selectValue(element, (String) value);
                } else if (value instanceof Number) {
                    selectValue(element, ((Number) value).toString());
                } else {
                    throw new AssertionError("unsupported class: "
                            + value.getClass().getName());
                }
            } else {
                if (value instanceof String) {
                    inputString(element, (String) value);
                } else if (value instanceof Number) {
                    inputNumber(element, (Number) value);
                } else if (value instanceof Boolean) {
                    checkBoolean(element, (Boolean) value);
                } else {
                    throw new AssertionError("unsupported class: "
                            + value.getClass().getName());
                }
            }
        }
    }

    private static void inputString(WebElement element, String value) {
        element.sendKeys(value);
    }

    private static void inputNumber(WebElement element, Number number) {
        element.sendKeys(number.toString());
    }

    private static void checkBoolean(WebElement element, boolean selected) {
        if (element.isSelected() != selected) {
            element.click();
        }
    }

    private static void selectValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    private static boolean isElementSelect(WebElement element) {
        return element.getTagName().equalsIgnoreCase("select");
    }

    private SeleniumUtil() {
    }
}
