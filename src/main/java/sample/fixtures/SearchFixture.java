package sample.fixtures;

/**
 *
 * @author t.endo
 */
public class SearchFixture extends AbstractFixture {

    private String q;

    public SearchFixture(String q) {
        this.q = q;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
