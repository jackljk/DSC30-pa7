/*
 * Name: Jack Kai Lim
 * PID:  A16919063
 */

import jdk.nashorn.internal.ir.IfNode;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.*;

/**
 * d-heap Implementation
 * @since 05/10/2022
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min
    private int arrSize; //Holds the arrSize to reinitialize heap when cleared

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        /* Constructor, that creates a binary heap by setting d as 2. */
        this.arrSize = 6;
        this.heap = (T[]) new Comparable[arrSize];
        this.nelems = 0;
        this.d = 2;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        /* Constructor, that creates a binary heap by setting d as 2 with a custom initial heap
        size */
        this.arrSize = heapSize;
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.d = 2;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        /* Constructor, that creates a d-heap with a vaue of d, with a custom heap size and it is
        *  also able to specific whether it is a max or min heap. */
        if (d < 1){
            throw new IllegalArgumentException();
        } else {
            this.d = d;
            this.arrSize = heapSize;
            this.heap = (T[]) new Comparable[heapSize];
            this.isMaxHeap = isMaxHeap;
            this.nelems = 0;
        }
    }

    /**
     * Getter Method
     * @return Size of heap
     */
    @Override
    public int size() {
        /* Getter Method that returns the size of the heap */
        return this.nelems;
    }

    /**
     * Adds a new data to the heap
     * @param data Data to be added
     * @throws NullPointerException Throws if data is null
     */
    @Override
    public void add(T data) throws NullPointerException {
        /* Adds a new value to the tail of the array using nelems as the index then uses bubble
        up to move it to the correct position.*/
        if (data == null){
            throw new NullPointerException();
        } else if (this.nelems == 0){
            this.heap[0] = data;
            this.nelems++;
        } else {
            if (this.nelems == this.heap.length){
                this.resize();
            }
            this.heap[this.nelems] = data; //Puts the new node in the heap at the last position
            bubbleUp(this.nelems);//Moves the node up to the correct position
            this.nelems++;//Increases number of nodes in heap
        }
    }

    /**
     * Removed the element at the root, then trickles down to move the last node to the right
     * position
     * @return The removed element
     * @throws NoSuchElementException Throws if the heap is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        /* Removes value at the root, moves last node to the root position then trickles down to
        the correct position */
        if (this.nelems == 0){
            throw new NoSuchElementException();
        }
        T removed = this.heap[0]; //Gets root node
        this.heap[0] = this.heap[this.nelems - 1]; //Replaces root with the last node
        this.heap[this.nelems - 1] = null; //Replaces last node with null placeholder
        this.nelems--; //Decreases the number of nodes in the heap
        trickleDown(0);//Moves node down to the correct position
        return removed;
    }

    /**
     * Clears the heap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        /* Creates a new clean arr and resets the nelems to 0 */
        this.heap = (T[]) new Comparable[arrSize];
        this.nelems = 0;
    }

    /**
     * Getter method to get the root element
     * @return The root element
     * @throws NoSuchElementException Throws if the heap is empty
     */
    public T element() throws NoSuchElementException {
        /* Gets the root element of the heap without removing */
        if (this.nelems == 0){
            throw new NoSuchElementException();
        } else {
            T root = this.heap[0];
            return root;
        }
    }

    /**
     * Moves the last node element that is moved to the root after a remove to its correct spot
     * @param index index where the element to trickle down is at
     */
    private void trickleDown(int index) {
        /* Moves node down from root until all its child nodes are smaller than it. */
        T currValue = this.heap[index];//Get the furthest left Child node value
        while (index < this.nelems){
            int cIndex = childStart(index);//Get the furthest left Child node index
            //Looking for the largest child node
            if (cIndex > this.heap.length){
                break;
            }
            T swapValue = this.heap[cIndex];
            int swapIndex = cIndex;
            for (int i = cIndex;i<cIndex + d;i++){
                if (this.heap[i] == null){
                    break;
                }
                if (this.isMaxHeap) {
                    if (swapValue.compareTo(this.heap[i]) < 0){
                        swapValue = this.heap[i];
                        swapIndex = i;
                    }
                } else {
                    if (swapValue.compareTo(this.heap[i]) > 0){
                        swapValue = this.heap[i];
                        swapIndex = i;
                    }
                }

            }
            //Compares the value of the nodes, swaps if the child node more than  the curr/parent
            // node
            if (swapValue == null){
                break;
            }
            if (this.isMaxHeap){
                if (swapValue.compareTo(currValue) <= 0){
                    break;
                } else {
                    swap(index, swapIndex);
                    index = swapIndex;
                }
            } else {
                if (swapValue.compareTo(currValue) >= 0){
                    break;
                } else {
                    swap(index, swapIndex);
                    index = swapIndex;
                }
            }

        }
    }

    /**
     * Moves an inserted index up to the position it should be at.
     * @param index Initial index of the node
     */
    private void bubbleUp(int index) {
        /* Moves the node up to its correct position*/
        while (index > 0){ //Loops until the index is the root
            int pIndex = parent(index);
            if (this.isMaxHeap){
                if (this.heap[index].compareTo(this.heap[pIndex]) <= 0) {
                    break;
                } else {
                    swap(pIndex, index);//swaps the parent node with the child node
                    index = pIndex;//Updates the nodes index
                }
            } else {
                if (this.heap[index].compareTo(this.heap[pIndex]) >= 0) {
                    break;
                } else {
                    swap(pIndex, index);//swaps the parent node with the child node
                    index = pIndex;//Updates the nodes index
                }
            }

        }
    }

    /**
     * Resizes the Array
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        /* Doubles the size of the array */
        T[] resize = (T[]) new Comparable[this.heap.length*2];
        if (this.nelems >= 0) System.arraycopy(this.heap, 0, resize, 0, this.nelems);
        this.heap = resize;
    }

    /**
     * Returns the index of the parent of the node at the index given
     * @param index Index of current node
     * @return The index of the parent of the node
     */
    private int parent(int index) {
        /* Returns the index of the parent of the node */
        return (index - 1)/this.d;
    }

    /**
     * Reutrns the starting point of the child indexes
     * @param index Index of the current pNode
     * @return The start index of its child nodes
     */
    private int childStart(int index){
        /* Returns the start of the child indexes */
        return this.d * index + 1;
    }

    /**
     * Swaps the nodes at the 2 given indexes
     * @param pIndex Index of the parent
     * @param index Index of the node
     */
    private void swap (int pIndex, int index){
        /* Helper function to swap nodes */
        T temp = this.heap[index];
        this.heap[index] = this.heap[pIndex];
        this.heap[pIndex] = temp;
    }

}
