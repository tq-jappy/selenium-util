/**
 *
 */
package sample;

import static org.junit.Assert.*

import org.junit.ClassRule
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

import sample.rule.WebDriverRule


/**
 *
 * @author t.endo
 */
@RunWith(Suite.class)
@SuiteClasses([FirstTest.class, SecondTest.class, NormalTest.class])
class AllTests {

    @ClassRule
    public static WebDriverRule browser = new WebDriverRule()
}
