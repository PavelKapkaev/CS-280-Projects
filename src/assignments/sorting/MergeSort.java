package assignments.sorting;

public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
  public void sort(T[] array) {// Sort the array.
    mySort(array, 0, array.length - 1); // Start at first index and go to last.
  }

  private void mySort(T[] array, int left, int right) { // Divide the array to smaller parts
    if (left < right) { // Continue while there is more than 1 value.
        int middle = (left + right) / 2; // Find the middle.
        mySort(array, left, middle); //Divide left part.
        mySort(array, middle + 1, right); // Divide right part.
        merge(array, left, middle, right); // Merge left and right.
    }
  }
  private void merge(T[] array, int left, int middle, int right) { // To merge to parts together.

    T[] temporary = java.util.Arrays.copyOf(array, array.length); // I copied the array to a temporary array here.
    int i = left;
    int j = middle + 1; // +1 to satrt the right side.
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
  public static void main(String[] args) {
    SortingAlgorithm.validate(new MergeSort<>());
    System.out.println("MergeSort has passed all tests.");
  }
}