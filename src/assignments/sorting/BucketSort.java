
package assignments.sorting;

/**
 * Sorts integers using bucket sort.
 */
public class BucketSort extends SortingAlgorithm<Integer> {

  /** Suppress default constructor warning. */
  public BucketSort() {}

  /**
   * Sorts an array in ascending order.
   * @param array the array to sort
   */
  public void sort(Integer[] array) {
    if (array.length < 2) return; // Stop if 2 numbers or less.

    int min = array[0]; // Start with the first number as minimum and maximum.
    int max = array[0]; 

    // Find the smallest and largest numbers.
    for (int i = 0; i < array.length; i++) { // Go through each number.
      if (array[i] < min) min = array[i]; // Update min.
      if (array[i] > max) max = array[i]; // Update max.
    }
    
    int[] buckets = new int[max - min + 1]; // Create an array of empty buckets.

    // Put numbers into the buckets.
    for (int i = 0; i < array.length; i++) { // Go through every number.
      int bucket = array[i] - min; // Find right bucket.
      buckets[bucket]++;
    }

    int position = 0;

    // Take numbers out of buckets.
    for (int i = 0; i < buckets.length; i++) { // Go through every bucket.
      for (int j = 0; j < buckets[i]; j++) { // Repeat for every number.
        array[position] = i + min; // Put the number back to the array.
        position++;
      }
    }
  }

  /**
   * Tests bucket sort.
   */
  public static void main(String[] args) {
    SortingAlgorithm.validate(new BucketSort());
  }
}
