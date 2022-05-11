import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class dHeapTester {
    private dHeap<Integer> twoHeap;
    private dHeap<Integer> twoHeaptwo;
    private dHeap<Integer> threeHeap;
    private dHeap<Integer> eightHeap;
    private dHeap<Integer> twoHeapMin;
    private dHeap<Integer> threeHeapMin;

    @Before
    public void setUp() throws Exception {
        twoHeap = new dHeap<Integer>();
        twoHeaptwo = new dHeap<Integer>(10);
        threeHeap = new dHeap<Integer>(3, 12, true);
        eightHeap = new dHeap<Integer>(8, 24, true);
        twoHeapMin = new dHeap<Integer>(2, 12, false);
        threeHeapMin = new dHeap<Integer>(3, 12, false);
    }

    @Test
    public void twoHeapTest(){
        int[] values = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        for (int value : values) {
            twoHeap.add(value);
        }
        assertEquals(new Integer(90), twoHeap.element());
        assertEquals(new Integer(90), twoHeap.remove());
        assertEquals(new Integer(36), twoHeap.remove());
        assertEquals(new Integer(26), twoHeap.remove());
        assertEquals(7, twoHeap.size());
        for (int value : values) {
            twoHeap.add(value);
        }
        assertEquals(17, twoHeap.size());
        assertEquals(new Integer(90), twoHeap.remove());
        assertEquals(new Integer(36), twoHeap.remove());
        assertEquals(new Integer(26), twoHeap.remove());
        assertEquals(new Integer(25), twoHeap.remove());
        assertEquals(new Integer(25), twoHeap.remove());
        twoHeap.clear();
        assertEquals(0, twoHeap.size());
    }

    @Test
    public void threeHeapTest(){
        int[] values = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        for (int value : values) {
            threeHeap.add(value);
        }
        assertEquals(new Integer(90), threeHeap.element());
        assertEquals(new Integer(90), threeHeap.remove());
        assertEquals(new Integer(36), threeHeap.remove());
        assertEquals(new Integer(26), threeHeap.remove());
        assertEquals(new Integer(25), threeHeap.remove());
        assertEquals(6, threeHeap.size());
        for (int value : values) {
            threeHeap.add(value);
        }
        assertEquals(16,threeHeap.size());
        assertEquals(new Integer(90), threeHeap.element());
        threeHeap.clear();
        assertEquals(0, threeHeap.size());
    }

    @Test
    public void eightHeapTest(){
        int[] values = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        for (int value : values) {
            eightHeap.add(value);
        }
        assertEquals(new Integer(90), eightHeap.element());
        assertEquals(new Integer(90), eightHeap.remove());
        assertEquals(9, eightHeap.size());
        //Also checked using debug mode that the array is in the correct order for a 8-ary heap
        eightHeap.clear();
        assertEquals(0, eightHeap.size());
    }

    @Test
    public void twoHeapMinTest(){
        int[] values = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        for (int value : values) {
            twoHeapMin.add(value);
        }
        assertEquals(new Integer(1), twoHeapMin.element());
        assertEquals(new Integer(1), twoHeapMin.remove());
        assertEquals(new Integer(2), twoHeapMin.remove());
        assertEquals(new Integer(3), twoHeapMin.remove());
        assertEquals(new Integer(7), twoHeapMin.remove());
        assertEquals(new Integer(17), twoHeapMin.element());
        assertEquals(6, twoHeapMin.size());
        for (int value : values) {
            twoHeapMin.add(value);
        }
        assertEquals(new Integer(1), twoHeapMin.remove());
        twoHeapMin.clear();
        assertEquals(0, twoHeapMin.size());
    }

    @Test
    public void threeHeapMinTest(){
        int[] values = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        for (int value : values) {
            threeHeapMin.add(value);
        }
        assertEquals(new Integer(1), threeHeapMin.element());
        assertEquals(new Integer(1), threeHeapMin.remove());
        assertEquals(new Integer(2), threeHeapMin.remove());
        assertEquals(new Integer(3), threeHeapMin.remove());
        assertEquals(new Integer(7), threeHeapMin.remove());
        assertEquals(new Integer(17), threeHeapMin.element());
        assertEquals(6, threeHeapMin.size());
        for (int value : values) {
            threeHeapMin.add(value);
        }
        assertEquals(new Integer(1), threeHeapMin.remove());
        threeHeapMin.clear();
        assertEquals(0, threeHeapMin.size());
    }

    @Test
    public void twoHeaptwoTest(){
        int[] values = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        for (int value : values) {
            twoHeaptwo.add(value);
        }
        //Checked with debugger that heap size is exactly 10 and still exactly 10 after adds.
        assertEquals(10, twoHeaptwo.size());
    }

    @Test (expected = NullPointerException.class)
    public void addNPE(){
        twoHeap.add(null);
    }

    @Test (expected = NoSuchElementException.class)
    public void removeNSEE(){
        twoHeap.remove();
    }

    @Test (expected = NoSuchElementException.class)
    public void elementNSEE(){
        twoHeap.element();
    }
}