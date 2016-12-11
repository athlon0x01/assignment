package my.ciklum.assignment.first;

import org.junit.Before;
import org.junit.Ignore;
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

    private void addWithDelay(String quote) {
        book.addQuote(quote);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            //who cares
        }
    }

    @Test
    public void emptyBookTest() {
        final List<String> quotes = book.dumpQuotes();
        assertEquals(3, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "",
                "BID");
        assertEquals(expected, quotes);
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
    public void nullQuoteTest() {
        book.addQuote("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUpdateQuoteFirstTest() {
        book.addQuote("Q1/O/U/1.31/1000000");
    }

    @Test
    public void addDeleteQuoteFirstTest() {
        book.addQuote("Q1/B/D/1.31/1000000");
        final List<String> quotes = book.dumpQuotes();
        assertEquals(3, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "",
                "BID");
        assertEquals(expected, quotes);
    }

    @Test
    public void simpleBidQuoteOrderTest() {
        final List<String> quoteString = Arrays.asList("Q1/B/N/1.31/100",
                "Q2/B/N/1.32/200");
        quoteString.forEach(this::addWithDelay);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(5, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "",
                "BID",
                "Q2/1.32/200",
                "Q1/1.31/100");
        assertEquals(expected, quotes);
    }

    @Test
    public void simpleBidQuoteOrderVolumeTest() {
        final List<String> quoteString = Arrays.asList("Q1/B/N/1.31/100",
                "Q2/B/N/1.31/200");
        quoteString.forEach(this::addWithDelay);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(5, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "",
                "BID",
                "Q2/1.31/200",
                "Q1/1.31/100");
        assertEquals(expected, quotes);
    }

    @Test
    public void simpleBidQuoteOrderTimestampTest() {
        final List<String> quoteString = Arrays.asList("Q1/B/N/1.31/100",
                "Q2/B/N/1.31/200",
                "Q3/B/N/1.31/100");
        quoteString.forEach(this::addWithDelay);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(6, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "",
                "BID",
                "Q2/1.31/200",
                "Q3/1.31/100",
                "Q1/1.31/100");
        assertEquals(expected, quotes);
    }

    @Test
    public void simpleOfferQuoteOrderTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/100",
                "Q2/O/N/1.32/200");
        quoteString.forEach(this::addWithDelay);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(5, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q1/1.31/100",
                "Q2/1.32/200",
                "",
                "BID");
        assertEquals(expected, quotes);
    }

    @Test
    public void simpleOfferQuoteOrderVolumeTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/100",
                "Q2/O/N/1.31/200");
        quoteString.forEach(this::addWithDelay);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(5, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q2/1.31/200",
                "Q1/1.31/100",
                "",
                "BID");
        assertEquals(expected, quotes);
    }

    @Test
    public void simpleOfferQuoteOrderTimestampTest() {
        final List<String> quoteString = Arrays.asList("Q1/O/N/1.31/100",
                "Q2/O/N/1.31/200",
                "Q3/O/N/1.31/100");
        quoteString.forEach(this::addWithDelay);
        final List<String> quotes = book.dumpQuotes();
        assertEquals(6, quotes.size());
        final List<String> expected = Arrays.asList("OFFER",
                "Q2/1.31/200",
                "Q3/1.31/100",
                "Q1/1.31/100",
                "",
                "BID");
        assertEquals(expected, quotes);
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
        quoteString.forEach(this::addWithDelay);
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
        quoteString.forEach(this::addWithDelay);
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
                "Q8/O/N/1.32/1000000");
        quoteString.forEach(this::addWithDelay);
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
        quoteString.forEach(this::addWithDelay);
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
        quoteString.forEach(this::addWithDelay);
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