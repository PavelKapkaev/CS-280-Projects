package assignments.sorting;
import assignments.datastructures.MinHeap;

/**
 * Sorts an array using a min heap.
 * @param <T> type of values
 */
public class HeapSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

  /**
   * Sorts the array from smallest to largest.
   * @param array the array to sort
   */
  public void sort(T[] array) {
    MinHeap<T> heap = new MinHeap<T>();
    // Add all values to the heap.
    for (int i = 0; i < array.length; i++) {
      heap.enqueue(array[i]);
    }
    // Put sorted values back into the array.
    for (int i = 0; i < array.length; i++) {
      array[i] = heap.dequeue();
    }
  }

  /**
   * Test HeapSort.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SortingAlgorithm.validate(new HeapSort<>());
  }
}