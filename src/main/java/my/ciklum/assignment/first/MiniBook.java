package my.ciklum.assignment.first;

import java.util.List;

/**
 A book includes all securities that the institution regularly buys and sells on
 the stock market. A bid is the price that an institution is willing to buy and
 an offer is the price that an institution is willing to sell.

 We want you to implement a Mini-Book class in Java. The following are the requirements:
 - Mini-Book receives quotes as input.
 - A quote is a 5-tuple string containing: QuoteID/{B|O}/{N|U|D}/Price/Volume
 * QuoteID : alphanumeric string which uniquely identifies a quote.
 * B/O: whether the quote is for Bid or Offer side.
 * N/U/D: whether this is a new quote or an update or delete for a previously
 received quote. A quote update can update either volume or price or both.
 A delete for a quote id of “0” will delete the entire book i.e. 0/B/D/0/0
 will delete the entire bid book and 0/O/D/0/0 will delete the entire offer
 book.
 * Price: price associated with this quote.
 * Volume: volume associated with this quote.
 - Mini-Book then sorts the offers and the bids from the quotes that it receives.
 - For offer, the best price is the lowest price and for bid, the best price is
 the highest price.
 - The best price is the first price that is displayed.
 - If two quotes have the same price, they are then sorted by volume (from high to low).
 - If two quotes have the same price and volume, they are then ranked by time - most
 recent to least recent.
 - There should be a function for dumping the contents of the book.
 - Optimize the code for speed and maintenance (simplicity is a good virtue)
 *
 * @author Anton Borovyk
 */
public class MiniBook {

    /**
     * Add quote to MiniBook
     * @param quote string to add
     */
    public void addQuote(String quote) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * public function for MiniBook content testing
     * @return list of MiniBook's quotes string ready for printing
     */
    public List<String> dumpQuotes() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Prints MiniBook's quotes string
     */
    public void printQuotes() {
        dumpQuotes().forEach(System.out::println);
    }
}
