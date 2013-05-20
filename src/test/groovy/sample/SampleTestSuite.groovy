/**
 *
 */
package sample;

import static org.junit.Assert.*

import org.junit.ClassRule
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

import sample.rule.WebBrowser


/**
 *
 * @author t.endo
 */
@RunWith(Suite.class)
@SuiteClasses([FirstTest.class, SecondTest.class])
class SampleTestSuite {

    @ClassRule
    public static WebBrowser browser = new WebBrowser()
}
