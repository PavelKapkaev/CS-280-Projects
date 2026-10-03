package assignments.sorting;

public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
  /**  
   * Sort array using quick sort.
   * @param array array to sort
   */
  public void sort(T[] array) { // Sort the array.
    mySort(array, 0, array.length - 1); // Start at first index and go to last.
  }
  /**
   * Sort from left to right.
   * @param array array to sort
   * @param left first index
   * @param right second index
   */
  private void mySort(T[] array, int left, int right){
    if (left < right) { // Check if there are any values to sort.
      T pivot = array[right]; // Last value is the pivot.
      int small = left; // Index for smallest value.
      for (int i = left; i < right; i++) { // Go through all values.
        if (array[i].compareTo(pivot) < 0){ // Check if value smaller than the pivot.
          swap(array, i, small); // Move smaller value left. 
          small++; // Move to next index
        }
      }
      swap(array, small, right); // Put pivot in the right place.

      mySort(array, left, small - 1); // Sort left side.
      mySort(array, small + 1, right); // Sort right side. 
    }
  }


    /**
   * Swap two elements within array.
   * @param array the array to swap value in
   * @param i first value index
   * @param j second value index
  */
  private void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;

  }
  /** 
   * Test
   * @param args given to program
   */
  public static void main(String[] args) {
    SortingAlgorithm.validate(new QuickSort<>());
    System.out.println("QuickSort has passed all tests.");
  }

  
}
