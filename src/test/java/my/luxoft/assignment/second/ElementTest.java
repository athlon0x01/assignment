package my.luxoft.assignment.second;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Anton Borovyk.
 */
public class ElementTest {
    @Test
    public void filterElements() throws Exception {
        List<Element> elements = Arrays.asList(new Element(1, "first", 17),
                new Element(2, "second", 27),
                new Element(2, "third", 22),
                new Element(4, "fourth", 37),
                new Element(5, "fifth", 15));

        List<Element> expected = Arrays.asList(new Element(2, "second", 27),
                new Element(4, "fourth", 37));

        assertEquals(expected, new ArrayList<>(Element.filterElements(elements)));
    }

    @Test
    public void filterElements2() throws Exception {
        List<Element> elements = Arrays.asList(new Element(1, "first", 17),
                new Element(2, "second", 12),
                new Element(2, "third", 22),
                new Element(4, "fourth", 37),
                new Element(5, "fifth", 15));

        List<Element> expected = Arrays.asList(new Element(2, "third", 22),
                new Element(4, "fourth", 37));

        assertEquals(expected, new ArrayList<>(Element.filterElements(elements)));
    }

}