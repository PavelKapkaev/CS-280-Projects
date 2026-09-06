package assignments.sorting;

/**
 * Insert each element one by one until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
  /**
   * Sort an array in place using selection sort.
   * 
   * Postcondition: 'array' is sorted in ascending order.
   * 
   * @param array an array of objects
   */

  public void sort(T[] array) {
    for (int k = 0;k < array.length;k ++) {    // k is the index of the smallest element to be placed.
      int x = k;
      for (int i = k+1; i < array.length; i ++) {    // Search for the smallest element in the array.
        if (array[i].compareTo(array[x]) < 0) {

          x = i; // x is the index of the smallest element.
        }  
      }
    swap(array,k,x); // swap the smallest element with the element at k.
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
    SortingAlgorithm.validate(new SelectionSort<>());
    System.out.println("SelectionSort has passed all tests.");
  }

}
