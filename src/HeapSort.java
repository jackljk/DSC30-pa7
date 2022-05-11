/*
 * Name: Jack Kai Lim
 * PID:  A16919063
 */

/**
 * Heap sort Implementation
 * @since 05/11/2022
 * @author Jack Kai Lim
 */
public class HeapSort {

    /**
     * TODO
     */
    public static void heapSort(int[] arr, int start, int end) {
        /* TODO */
        dHeap<Integer> heap = new dHeap<>();
        for (int i = start;i<end + 1;i++){
            heap.add(arr[i]);
        }
        int j = end;
        while (heap.size() != 0){
            arr[j] = heap.remove();
            j--;
        }
    }
}
