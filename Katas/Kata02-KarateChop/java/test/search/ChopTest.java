package search;
import static org.junit.Assert.*;

import org.junit.Test;

import search.IterativeChop;

public class ChopTest {

    @Test
    public void test_iterative_odd_elems() {
        Chop chopper = new IterativeChop();
        int[] testArray = new int[] { 1, 3, 5 };
        
        assertEquals(0, chopper.chop(1, testArray));
        assertEquals(1, chopper.chop(3, testArray));
        assertEquals(2, chopper.chop(5, testArray));
        assertEquals(-1, chopper.chop(0, testArray));
        assertEquals(-1, chopper.chop(2, testArray));
        assertEquals(-1, chopper.chop(4, testArray));
        assertEquals(-1, chopper.chop(6, testArray));
    }

    @Test
    public void test_iterative_even_elems() {
        Chop chopper = new IterativeChop();
        int[] testArray = new int[] { 1, 3, 5, 7 };
        
        assertEquals(0, chopper.chop(1, testArray));
        assertEquals(1, chopper.chop(3, testArray));
        assertEquals(2, chopper.chop(5, testArray));
        assertEquals(3, chopper.chop(7, testArray));
        assertEquals(-1, chopper.chop(0, testArray));
        assertEquals(-1, chopper.chop(2, testArray));
        assertEquals(-1, chopper.chop(4, testArray));
        assertEquals(-1, chopper.chop(6, testArray));
        assertEquals(-1, chopper.chop(8, testArray));
    }

    @Test
    public void test_recursive_odd_elems() {
        Chop chopper = new RecursiveChop();
        int[] testArray = new int[] { 1, 3, 5 };
        
        assertEquals(0, chopper.chop(1, testArray));
        assertEquals(1, chopper.chop(3, testArray));
        assertEquals(2, chopper.chop(5, testArray));
        assertEquals(-1, chopper.chop(0, testArray));
        assertEquals(-1, chopper.chop(2, testArray));
        assertEquals(-1, chopper.chop(4, testArray));
        assertEquals(-1, chopper.chop(6, testArray));
    }

    @Test
    public void test_recursive_even_elems() {
        Chop chopper = new RecursiveChop();
        int[] testArray = new int[] { 1, 3, 5, 7 };
        
        assertEquals(0, chopper.chop(1, testArray));
        assertEquals(1, chopper.chop(3, testArray));
        assertEquals(2, chopper.chop(5, testArray));
        assertEquals(3, chopper.chop(7, testArray));
        assertEquals(-1, chopper.chop(0, testArray));
        assertEquals(-1, chopper.chop(2, testArray));
        assertEquals(-1, chopper.chop(4, testArray));
        assertEquals(-1, chopper.chop(6, testArray));
        assertEquals(-1, chopper.chop(8, testArray));
    }
}
