package assignments.datastructures;
import adt.PriorityQueue;

/**
 * A min heap that stores values in a Vector.
 * The smallest value is always at the top.
 * Adding and removing values takes O(log N) time.
 * @param <T> type of values
 */
public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {
  private Vector<T> heap; // Vector that stores the heap values.

  /**
   * Creates an empty min heap.
   */
  public MinHeap() {
      heap = new Vector<T>(); // Create an empty vector.
  }

  /**
   * Checks if the heap is empty.
   * @return true if empty, false otherwise
   */
  public boolean isEmpty() {
      return heap.length() == 0; // Check if there are no values.
  }

  /**
   * Adds a value to the heap.
   * Moves the value up until the heap is in order.
   * @param value the value to add
   */
  public void enqueue(T value) {
    heap.insert(heap.length(), value); // Add the value at the end.
    int index = heap.length() - 1; // Get the index of the new value.
    while (index > 0) { // Continue until we reach the root.
      int parent = (index - 1) / 2; // Find the parent index.
      if (heap.at(index).compareTo(heap.at(parent)) >= 0) break; // Stop if the parent is smaller.
      T temp = heap.at(index); // Save the current value.
      heap.set(index, heap.at(parent)); // Move the parent down.
      heap.set(parent, temp); // Move the current value up.
      index = parent; // Move to the parent index.
    }
  }

  /**
   * Removes and returns the smallest value.
   * Moves values down to keep the heap in order.
   * @return the smallest value
   */
  public T dequeue() { 
    if (isEmpty()) return null; // Return null if the heap is empty.
    T smallest = heap.at(0); // Save the smallest value.
    T last = heap.at(heap.length() - 1); // Save the last value.
    heap.delete(heap.length() - 1); // Remove the last value.
    if (isEmpty()) return smallest; // Return if the heap is now empty.
    heap.set(0, last); // Move the last value to the root.
    int index = 0; // Start at the root.
    while (true) { // Continue until the heap is in order.
      int left = 2 * index + 1; // Find the left child.
      int right = 2 * index + 2; // Find the right child.
      int smaller = index; // Start with the current index.
      if (left < heap.length() && heap.at(left).compareTo(heap.at(smaller)) < 0) smaller = left; // Choose the left child if smaller.
      if (right < heap.length() && heap.at(right).compareTo(heap.at(smaller)) < 0) smaller = right; // Choose the right child if smaller.
      if (smaller == index) break; // Stop if the current value is smallest.
      T temp = heap.at(index); // Save the current value.
      heap.set(index, heap.at(smaller)); // Move the smaller child up.
      heap.set(smaller, temp); // Move the current value down.
      index = smaller; // Continue from the child's index.
    }
    return smallest; // Return the removed value.
  }

  /**
   * Returns the smallest value without removing it.
   * @return the smallest value, or null if empty
   */
  public T peek() {
    if (isEmpty()) return null; // Return null if empty.
    return heap.at(0); // Return the smallest value.
  }


  /**
   * Tests the MinHeap.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PriorityQueue.validate(new MinHeap<Integer>());
    System.out.println("MinHeap passes all tests.");
  }

}