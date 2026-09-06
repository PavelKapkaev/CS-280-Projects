package assignments.sorting;

/**
 * Insert each element one by one until the array is sorted.
 */
public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
  /**
   * Sort an array in place using insertion sort.
   * 
   * Postcondition: 'array' is sorted in ascending order.
   * 
   * @param array an array of objects
   */

  public void sort(T[] array) {
    for (int k = 1;k < array.length;k ++) {       // k is the index of elements we are sorting.
      for (int i = k; i > 0; i --) {    // Compare the current element with the element before it.
        if (array[i-1].compareTo(array[i]) > 0) {
          // Swap adjacent items (i-1 and i).
          swap(array, i-1, i);
        }
        else {
          // Stop when elements are in the correct place.
          break;

        }
        
      }
    }
  }
  /**
   * Swap two elements within array.
   * @param array the array to swap value in
   * @param i
   * @param j
  */
  private void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;

  }
  /**
   * Run validation test.
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SortingAlgorithm.validate(new InsertionSort<>());
    System.out.println("InsertionSort has passed all tests.");
  }

}
