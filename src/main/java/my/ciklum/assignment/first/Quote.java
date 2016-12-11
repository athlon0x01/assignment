package my.ciklum.assignment.first;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;

/**
 * Quote to be received by {@link MiniBook}
 *
 * @author Anton Borovyk
 */
public class Quote {

    /**
     * Quote Status enum (New / Update / Delete)
     */
    public enum Status {
        NEW,
        UPDATE,
        DELETE
    }

    /**
     * Quote Type enum (Bid / Offer)
     */
    public enum Type {
        BID,
        OFFER
    }

    private static DecimalFormat priceFormatter = new DecimalFormat(".00");

    private final String id;
    private final Type type;
    private final Status status;
    private final ZonedDateTime timestamp;
    private final Double price;       //if we need more accuracy, it is better to use BigDecimal
    private final Long volume;

    public Quote(String id, Type type, Status status, double price, long volume) {
        if (id == null || type == null || status == null) {
            throw new IllegalArgumentException("Null values are forbidden for id, type and status");
        }
        this.id = id;
        this.type = type;
        this.status = status;
        this.price = price;
        this.volume = volume;
        this.timestamp = ZonedDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public Double getPrice() {
        return price;
    }

    public Long getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote)) return false;

        Quote quote = (Quote) o;

        if (!id.equals(quote.id)) return false;
        if (!price.equals(quote.price)) return false;
        if (status != quote.status) return false;
        if (!timestamp.equals(quote.timestamp)) return false;
        if (type != quote.type) return false;
        if (!volume.equals(quote.volume)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id + "/" + priceFormatter.format(price) + "/" + volume;
    }

    private static Status parseStatus(final String status) {
        switch (status) {
            case "N": return Status.NEW;
            case "U": return Status.UPDATE;
            case "D": return Status.DELETE;
            default: throw new IllegalArgumentException("Unknown status - " + status);
        }
    }

    private static Type parseType(final String type) {
        switch (type) {
            case "B": return Type.BID;
            case "O": return Type.OFFER;
            default: throw new IllegalArgumentException("Unknown type - " + type);
        }
    }

    public static Quote parse(String quote) {
        final String[] items = quote.split("/");
        if (items.length != 5) {
            throw new IllegalArgumentException("A quote is a 5-tuple string containing: QuoteID/{B|O}/{N|U|D}/Price/Volume, but ["
                    + quote + "] doesn't match these criteria.");
        }
        return new Quote(items[0],
                parseType(items[1]),
                parseStatus(items[2]),
                Double.parseDouble(items[3]),
                Long.parseLong(items[4]));
    }
}
