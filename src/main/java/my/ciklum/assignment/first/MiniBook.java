package my.ciklum.assignment.first;

import java.util.*;

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

    public static final String OFFER_LABEL = "OFFER";
    public static final String BID_LABEL = "BID";
    public static final String DELETE_ID = "0";

    private final Map<String, Quote> bids;
    private final Map<String, Quote> offers;
    private final Comparator<Quote> bidComparator;
    private final Comparator<Quote> offerComparator;

    public MiniBook() {

        bidComparator = (a, b) -> {
            int c = b.getPrice().compareTo(a.getPrice());
            if (c == 0) {
                c = b.getVolume().compareTo(a.getVolume());
            }
            if (c == 0) {
                c = b.getTimestamp().compareTo(a.getTimestamp());
            }
            return c;
        };
        bids = new HashMap<>();

        offerComparator = (a, b) -> {
            int c = a.getPrice().compareTo(b.getPrice());
            if (c == 0) {
                c = b.getVolume().compareTo(a.getVolume());
            }
            if (c == 0) {
                c = b.getTimestamp().compareTo(a.getTimestamp());
            }
            return c;
        };
        offers = new HashMap<>();
    }

    /**
     * Add quote to MiniBook
     * @param quote string to add
     */
    public void addQuote(String quote) {
        if (quote == null || quote.isEmpty()) {
            throw new IllegalArgumentException("Empty quote string is not allowed");
        }
        final Quote newQuote = Quote.parse(quote);
        Map<String, Quote> qMap = (newQuote.getType() == Quote.Type.BID) ? bids : offers;
        switch (newQuote.getStatus()) {
            case NEW: {
                if (qMap.containsKey(newQuote.getId())) {
                    throw new IllegalArgumentException("MiniBook contains quote with id = ["
                            + newQuote.getId() + "], use UPDATE quote instead of NEW");
                }
                qMap.put(newQuote.getId(), newQuote);
                break;
            }
            case UPDATE: {
                if (!qMap.containsKey(newQuote.getId())) {
                    throw new IllegalArgumentException("MiniBook does not contain quote with id = ["
                            + newQuote.getId() + "], use NEW quote instead of UPDATE");
                }
                qMap.put(newQuote.getId(), newQuote);
                break;
            }
            case DELETE: {
                if (DELETE_ID.equals(newQuote.getId())) {
                    qMap.clear();
                }
                else {
                    qMap.remove(newQuote.getId());
                }
            }
        }
    }

    /**
     * public function for MiniBook content testing
     * @return list of MiniBook's quotes string ready for printing
     */
    public List<String> dumpQuotes() {
        final List<String> quotes = new ArrayList<>();
        quotes.add(OFFER_LABEL);
        offers.values().stream().sorted(offerComparator).forEach(q -> quotes.add(q.toString()));
        quotes.add("");
        quotes.add(BID_LABEL);
        bids.values().stream().sorted(bidComparator).forEach(q -> quotes.add(q.toString()));
        return quotes;
    }

    /**
     * Prints MiniBook's quotes string
     */
    public void printQuotes() {
        dumpQuotes().forEach(System.out::println);
    }
}
