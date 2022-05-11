import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapSortTest {

    @Test
    public void heapSort() {
        int[] test = new int[]{2, 7, 26, 25, 19, 17, 1, 90, 3, 36};
        HeapSort.heapSort(test, 0, 9);
        System.out.println(Arrays.toString(test));
    }
}