package assignments.sorting;

public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
  /** 
   * Sort the array using merge sort.
   * @param array array to sort.
   */
  public void sort(T[] array) {
    T[] temporary = (T[]) new Comparable[array.length]; // Array used for sorting.
    mySort(array, temporary, 0, array.length - 1); // Starts at the first index and goes to last.
  }


  /**
   * Divide the array into small parts and sort them.
   * @param array array sorted
   * @param left first index
   * @param right last index
   * @param temporary temporary array
   */
  private void mySort(T[] array, T[] temporary, int left, int right) { // Divide the array to smaller parts
    if (left < right) { // Continue while there is more than 1 value.
        int middle = (left + right) / 2; // Find the middle.
        mySort(array, temporary, left, middle); //Divide left part.
        mySort(array, temporary, middle + 1, right); // Divide right part.
        merge(array, temporary, left, middle, right); // Merge left and right.
    }
  }

  /**
   * Merge sorted parts back together.
   * @param array array sorted
   * @param temporary temporary array
   * @param left first index
   * @param middle middle index
   * @param right last index
   */
  private void merge(T[] array, T[] temporary, int left, int middle, int right) { // To merge two parts together.
    for (int x = left; x <= right; x++) {
        temporary[x] = array[x];
    }
    int i = left;
    int j = middle + 1; // +1 to start the right side.
    int k = left;
    while (i <= middle && j <= right) { // Need it to continue while both parts have values in them.
      if (temporary[i].compareTo(temporary[j]) <= 0) { // Check if left value is smaller.
        array[k] = temporary[i]; // Put left value in the array.
        i++; // Go to the next value.
      }
      else {
        array[k] = temporary[j]; // Put right value in the array.
        j++; // Go to the next value.
      }
      k++; // Go to next position.
    }
    while (i <= middle) { // Check if left part has any values left.
      array[k] = temporary[i]; // Save left value into array.
      i++; // Move to the next left value.
      k++; // Move to the next position.
    }

    while (j <= right) { // Check if right part has any values left.
      array[k] = temporary[j]; // Save right value into array.
      j++; // Move to the next right value.
      k++; // Move to the next position.
    }
  }
  /**
   * Test
   * @param args args given to the program
   */
  public static void main(String[] args) {
    SortingAlgorithm.validate(new MergeSort<>());
    System.out.println("MergeSort has passed all tests.");
  }
}