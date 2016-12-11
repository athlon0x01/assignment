package my.ciklum.assignment.second;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests fro {@link Animation}
 *
 * Examples
 --------

 a) "..R....", 2

 Returns: { "..X....",  "....X..",  "......X",  "......." }

 The single particle starts at the 3rd position, moves to the 5th, then 7th,
 and then out of the chamber.


 b)  "RR..LRL", 3
 Returns: { "XX..XXX",  ".X.XX..",  "X.....X",  "......." }
 At time 1, there are actually 4 particles in the chamber, but two are
 passing through each other at the 4th position.


 c)  "LRLR.LRLR", 2

 Returns: { "XXXX.XXXX",  "X..X.X..X",  ".X.X.X.X.",  ".X.....X.",  "........." }

 At time 0 there are 8 particles. At time 1, there are still 6 particles, but
 only 4 positions are occupied since particles are passing through each
 other.


 d)  "RLRLRLRLRL", 10

 Returns: { "XXXXXXXXXX",  ".........." }

 These particles are moving so fast that they all exit the chamber by time 1.


 e)  "...", 1

 Returns: { "..." }


 f)  "LRRL.LR.LRR.R.LRRL.", 1

 Returns: {"XXXX.XX.XXX.X.XXXX.",
 "..XXX..X..XX.X..XX.",
 ".X.XX.X.X..XX.XX.XX",
 "X.X.XX...X.XXXXX..X",
 ".X..XXX...X..XX.X..",
 "X..X..XX.X.XX.XX.X.",
 "..X....XX..XX..XX.X",
 ".X.....XXXX..X..XX.",
 "X.....X..XX...X..XX",
 ".....X..X.XX...X..X",
 "....X..X...XX...X..",
 "...X..X.....XX...X.",
 "..X..X.......XX...X",
 ".X..X.........XX...",
 "X..X...........XX..",
 "..X.............XX.",
 ".X...............XX",
 "X.................X",
 "..................." }
 */
public class AnimationTest {

    private Animation animation;

    @Before
    public void setUp() throws Exception {
        animation = new Animation();
    }

    @Test
    public void aCaseTest() {
        final List<String> actual = Arrays.asList(animation.animate(2, "..R...."));
        final List<String> expected = Arrays.asList("..X....",
                "....X..",
                "......X",
                ".......");
        assertEquals(expected, actual);
    }

    @Test
    public void bCaseTest() {
        final List<String> actual = Arrays.asList(animation.animate(3, "RR..LRL"));
        final List<String> expected = Arrays.asList("XX..XXX",
                ".X.XX..",
                "X.....X",
                ".......");
        assertEquals(expected, actual);
    }

    @Test
    public void cCaseTest() {
        final List<String> actual = Arrays.asList(animation.animate(2, "LRLR.LRLR"));
        final List<String> expected = Arrays.asList("XXXX.XXXX",
                "X..X.X..X",
                ".X.X.X.X.",
                ".X.....X.",
                ".........");
        assertEquals(expected, actual);
    }

    @Test
    public void dCaseTest() {
        final List<String> actual = Arrays.asList(animation.animate(10, "RLRLRLRLRL"));
        final List<String> expected = Arrays.asList("XXXXXXXXXX",
                "..........");
        assertEquals(expected, actual);
    }

    @Test
    public void eCaseTest() {
        final List<String> actual = Arrays.asList(animation.animate(1, "..."));
        final List<String> expected = Arrays.asList("...");
        assertEquals(expected, actual);
    }

    @Test
    public void fCaseTest() {
        final List<String> actual = Arrays.asList(animation.animate(1, "LRRL.LR.LRR.R.LRRL."));
        final List<String> expected = Arrays.asList("XXXX.XX.XXX.X.XXXX.",
                "..XXX..X..XX.X..XX.",
                ".X.XX.X.X..XX.XX.XX",
                "X.X.XX...X.XXXXX..X",
                ".X..XXX...X..XX.X..",
                "X..X..XX.X.XX.XX.X.",
                "..X....XX..XX..XX.X",
                ".X.....XXXX..X..XX.",
                "X.....X..XX...X..XX",
                ".....X..X.XX...X..X",
                "....X..X...XX...X..",
                "...X..X.....XX...X.",
                "..X..X.......XX...X",
                ".X..X.........XX...",
                "X..X...........XX..",
                "..X.............XX.",
                ".X...............XX",
                "X.................X",
                "...................");
        assertEquals(expected, actual);
    }
}