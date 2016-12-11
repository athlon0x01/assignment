package my.ciklum.assignment.second;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChamberTest {

    @Test(expected = IllegalArgumentException.class)
    public void testWrongSpeed() {
        new Chamber(11, "...");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongInit() {
        new Chamber(1, "");
    }

    @Test
    public void testPrint() throws Exception {
        Chamber chamber = new Chamber(2, "R..L..R");
        assertEquals("X..X..X", chamber.print());
    }

    @Test
    public void testPrintEmpty() throws Exception {
        Chamber chamber = new Chamber(2, "....");
        assertEquals("....", chamber.print());
    }

    @Test
    public void testIsEmpty() throws Exception {
        Chamber chamber = new Chamber(2, "....");
        assertTrue(chamber.isEmpty());
    }

    @Test
    public void testNotEmpty() throws Exception {
        Chamber chamber = new Chamber(2, ".L..");
        assertFalse(chamber.isEmpty());
    }

    @Test
    public void testMoveParticles() throws Exception {
        Chamber chamber = new Chamber(2, "R..L..R");
        chamber.moveParticles();
        assertEquals(".XX....", chamber.print());
    }
}