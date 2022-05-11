import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {
    private MyPriorityQueue<Integer> a;

    @Before
    public void init(){
        a = new MyPriorityQueue<Integer>(10);
    }

    @Test
    public void test(){
        int[] values = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        for (int value : values) {
            a.offer(value);
        }
        assertEquals(new Integer(90), a.peek());
        assertEquals(new Integer(90), a.poll());
        assertEquals(new Integer(36), a.poll());
        assertFalse(a.isEmpty());
        a.clear();
        assertTrue(a.isEmpty());
    }

}