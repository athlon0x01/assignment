package my.ciklum.assignment.first;

import org.junit.Before;

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

    @Before
    public void setUp() throws Exception {

    }
}