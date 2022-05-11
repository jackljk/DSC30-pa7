/*
 * Name: Jack Kai Lim
 * PID:  A16919063
 */

import java.util.NoSuchElementException;

/**
 * Priority Queue implementation
 * @since 05/11/2022
 * @param <T> Generic type
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private final int D_VALUE = 5;
    private dHeap<T> pQueue;

    /**
     * Constructor that creates a new priority queue
     * 
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        /* Creates a 5-ary Heap that is saved as pQueue */
        this.pQueue = new dHeap<T>(D_VALUE, initialSize, true);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot be
     * null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        /* Adds the element to the queue, since we are using a maxHeap it automatically sorts to
        where it needs to be */
        if (element == null){
            throw new NullPointerException();
        } else {
            this.pQueue.add(element);
            return true;
        }
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is empty.
     */
    public T poll() {
        /* If the size of the queue is 0, return null, else remove head(The Largest element) and
        return it. */
        if (this.pQueue.size() == 0){
            return null;
        } else {
            return this.pQueue.remove();
        }
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        /* Resets the queue using dheap clear function */
        this.pQueue.clear();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     * 
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        /* If the size of the queue is 0, returns null else returns the head without removing */
        if (this.pQueue.size() == 0){
            return null;
        } else {
            return this.pQueue.element();
        }
    }

    /**
     * Indicates whether the queue is empty.
     * @return true is the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        /* Returns true if the heap size is 0, else false */
        return this.pQueue.size() == 0;
    }

}
