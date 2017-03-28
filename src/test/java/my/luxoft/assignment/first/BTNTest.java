package my.luxoft.assignment.first;

import org.junit.Test;

import static org.junit.Assert.*;

public class BTNTest {

    @Test
    public void testCompareToNull() throws Exception {
        BTN node = new BTN(1);
        assertEquals(-1 , node.compareTo(null));
    }

    @Test
    public void testCompareToSimple() throws Exception {
        BTN node = new BTN(1);
        BTN node2 = new BTN(1);
        assertEquals(0 , node.compareTo(node2));
    }

    @Test
    public void testCompareTo2Levels() throws Exception {
        BTN node = new BTN(3, new BTN(1), new BTN(5));
        BTN node2 = new BTN(3, new BTN(1), new BTN(5));
        assertEquals(0 , node.compareTo(node2));
    }

    @Test
    public void testCompareTo3Levels() throws Exception {
        BTN node = new BTN(3, new BTN(2, new BTN(1), null), new BTN(5, new BTN(4), new BTN(7, null, new BTN(10))));
        BTN node2 = new BTN(3, new BTN(2, new BTN(1), null), new BTN(5, new BTN(4), new BTN(7, null, new BTN(10))));
        assertEquals(0 , node.compareTo(node2));
    }

    @Test
    public void testCompareTo3LevelsNe() throws Exception {
        BTN node = new BTN(3, new BTN(2, new BTN(1), null), new BTN(5, new BTN(4), new BTN(7, null, new BTN(10))));
        BTN node2 = new BTN(3, new BTN(2, new BTN(-1), null), new BTN(5, new BTN(4), new BTN(7, null, new BTN(12))));
        assertNotEquals(0 , node.compareTo(node2));
    }
}