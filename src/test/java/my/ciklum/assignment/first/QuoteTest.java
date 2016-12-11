package my.ciklum.assignment.first;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class QuoteTest {

    @Test
    public void testEquals() throws Exception {
        Quote q1 = new Quote("Q1", Quote.Type.BID, Quote.Status.NEW, 1.32, 10000);
        Quote q11 = new Quote("Q1", Quote.Type.BID, Quote.Status.NEW, 1.32, 10000);
        Quote q12 = new Quote("Q1", Quote.Type.BID, Quote.Status.NEW, 1.32, 10000);
        Quote q13 = new Quote("Q1", Quote.Type.BID, Quote.Status.UPDATE, 1.22, 20000);
        Quote q2 = new Quote("Q2", Quote.Type.OFFER, Quote.Status.NEW, 1.32, 10000);

        assertEquals(q1, q11);
        assertEquals(q1.hashCode(), q11.hashCode());

        assertEquals(q1, q12);
        assertEquals(q1.hashCode(), q12.hashCode());

        assertEquals(q12, q11);
        assertEquals(q12.hashCode(), q11.hashCode());

        assertNotEquals(q1, q13);
        assertEquals(q1.hashCode(), q13.hashCode());

        assertNotEquals(q1, q2);
        assertNotEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void toStringTest() {
        Quote quote = new Quote("Q1", Quote.Type.BID, Quote.Status.NEW, 1.3, 10000);
        assertEquals("Q1/1.30/10000", quote.toString());
    }

    @Test
    public void testParse() throws Exception {
        Quote quote = Quote.parse("Q1/B/N/1.32/10000");
        assertEquals("Q1", quote.getId());
        assertEquals(Quote.Type.BID, quote.getType());
        assertEquals(Quote.Status.NEW, quote.getStatus());
        assertEquals(1.32, quote.getPrice(), 0.0001);
        assertEquals(Long.valueOf(10000), quote.getVolume());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseFailId() throws Exception {
        Quote.parse("B/N/1.32/10000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseFailType() throws Exception {
        Quote.parse("Q1/W/N/1.32/10000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseFailStatus() throws Exception {
        Quote.parse("Q1/O/R/1.32/10000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseFailPrice() throws Exception {
        Quote.parse("Q1/O/N/1.sdsfd532/10000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseFailVolume() throws Exception {
        Quote.parse("Q1/B/N/1.32/10yes000");
    }
}