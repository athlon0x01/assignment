package my.ciklum.assignment.first;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link MiniBook}
 *
 Example:
 Given the following sequence of quotes as input:

 Q1/O/N/1.31/1000000
 Q2/B/N/1.21/1000000
 Q3/B/N/1.22/1000000
 Q4/B/N/1.20/1000000
 Q5/B/N/1.20/1000000
 Q6/O/N/1.32/1000000
 Q7/O/N/1.33/200000
 Q5/B/U/1.20/500000
 Q7/O/U/1.33/100000
 Q7/O/D/0/0


 MiniBook’s contents should be displayed as follows:

 OFFER
 Q1/1.31/1000000
 Q6/1.32/1000000

 BID
 Q3/1.22/1000000
 Q2/1.21/1000000
 Q4/1.20/1000000
 Q5/1.20/500000
 */
public class MiniBookTest {

    private MiniBook book;

    @Before
    public void setUp() throws Exception {
        book = new MiniBook();
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongQuoteTypeTest() {
        book.addQuote("Q1/W/U/1.31/1000000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongQuoteStatusTest() {
        book.addQuote("Q1/B/NEW/1.31/1000000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongQuotePriceTest() {
        book.addQuote("Q1/B/N/1.rfd31/1000000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongQuoteVolumeTest() {
        book.addQuote("Q1/B/NEW/1.31/-100");
    }

    @Test
    public void addNewQuoteFirstTest() {
        book.addQuote("Q1/O/N/1.31/1000000");
        final List<String> quotes = book.dumpQuotes();
        assertEquals(4, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q1/1.31/1000000",
                "",
                "BID");
        assertEquals(expected, quotes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUpdateQuoteFirstTest() {
        book.addQuote("Q1/O/U/1.31/1000000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDeleteQuoteFirstTest() {
        book.addQuote("Q1/B/U/1.31/1000000");
    }

    @Test
    public void quoteOrderTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/1000000",
                "Q2/B/N/1.21/1000000",
                "Q3/B/N/1.22/1000000",
                "Q4/B/N/1.20/1000000",
                "Q5/B/N/1.20/1000000",
                "Q6/O/N/1.32/1000000",
                "Q7/O/N/1.33/200000",
                "Q5/B/U/1.20/500000",
                "Q7/O/U/1.33/100000");
        quoteString.forEach(book::addQuote);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(10, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q1/1.31/1000000",
                "Q6/1.32/1000000",
                "Q7/1.33/100000",
                "",
                "BID",
                "Q3/1.22/1000000",
                "Q2/1.21/1000000",
                "Q4/1.20/1000000",
                "Q5/1.20/500000");
        assertEquals(expected, quotes);
    }

    @Test
    public void quoteOrderWithDeleteTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/1000000",
                "Q2/B/N/1.21/1000000",
                "Q3/B/N/1.22/1000000",
                "Q4/B/N/1.20/1000000",
                "Q5/B/N/1.20/1000000",
                "Q6/O/N/1.32/1000000",
                "Q7/O/N/1.33/200000",
                "Q5/B/U/1.20/500000",
                "Q7/O/U/1.33/100000",
                "Q7/O/D/0/0");
        quoteString.forEach(book::addQuote);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(9, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q1/1.31/1000000",
                "Q6/1.32/1000000",
                "",
                "BID",
                "Q3/1.22/1000000",
                "Q2/1.21/1000000",
                "Q4/1.20/1000000",
                "Q5/1.20/500000");
        assertEquals(expected, quotes);
    }

    @Test
    public void quoteOrderWithSamePriceAndVolumeTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/1000000",
                "Q2/B/N/1.21/1000000",
                "Q3/B/N/1.22/1000000",
                "Q4/B/N/1.20/1000000",
                "Q5/B/N/1.20/1000000",
                "Q6/O/N/1.32/1000000",
                "Q7/O/N/1.33/200000",
                "Q5/B/U/1.20/500000",
                "Q7/O/U/1.33/100000",
                "Q6/O/N/1.32/1000000");
        quoteString.forEach(book::addQuote);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(11, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q1/1.31/1000000",
                "Q8/1.32/1000000",
                "Q6/1.32/1000000",
                "Q7/1.33/100000",
                "",
                "BID",
                "Q3/1.22/1000000",
                "Q2/1.21/1000000",
                "Q4/1.20/1000000",
                "Q5/1.20/500000");
        assertEquals(expected, quotes);
    }

    @Test
    public void offerQuotesDeleteTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/1000000",
                "Q2/B/N/1.21/1000000",
                "Q3/B/N/1.22/1000000",
                "Q4/B/N/1.20/1000000",
                "Q5/B/N/1.20/1000000",
                "Q6/O/N/1.32/1000000",
                "Q7/O/N/1.33/200000",
                "Q5/B/U/1.20/500000",
                "Q7/O/U/1.33/100000",
                "0/O/D/0/0");
        quoteString.forEach(book::addQuote);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(7, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "",
                "BID",
                "Q3/1.22/1000000",
                "Q2/1.21/1000000",
                "Q4/1.20/1000000",
                "Q5/1.20/500000");
        assertEquals(expected, quotes);
    }

    @Test
    public void bidQuotesDeleteTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/1000000",
                "Q2/B/N/1.21/1000000",
                "Q3/B/N/1.22/1000000",
                "Q4/B/N/1.20/1000000",
                "Q5/B/N/1.20/1000000",
                "Q6/O/N/1.32/1000000",
                "Q7/O/N/1.33/200000",
                "Q5/B/U/1.20/500000",
                "Q7/O/U/1.33/100000",
                "0/B/D/0/0");
        quoteString.forEach(book::addQuote);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(6, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q1/1.31/1000000",
                "Q6/1.32/1000000",
                "Q7/1.33/100000",
                "",
                "BID");
        assertEquals(expected, quotes);
    }
}