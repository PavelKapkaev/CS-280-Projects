package assignments.sorting;

/**
 * Swap all elements one by one until the whole array is sorted.
 */
public class InsertionSort extends SortingAlgorithm {
  /**
   * sort an arrray in-place using insetion sort.
   * 
   * Post conditon: 'array' is sorted in ascentding order.
   * 
   * @param array an array of integers
   */

  public void sort(Integer[] array) {
    for (int k = array.length;k >= 2  ;k --) {       // k is the length of thesub-array we are
      for (int i = 0; i < k-1; i ++) {    //go up yo k - 1 because we dont need to campare the last index to its' neighbour
        if (array[i] > array[i-1]) {
          // swap asjacent itemsI (i and i - 1)
          swap(array, i, i - 1);
        }
        else {
          break;

        }
        
      }
    }
  }
  /**
   * 
   * swap two elements within array
   * @param array the array to swap value in
   * @param i
   * @param j
   * 
  */
  private void swap(Integer[] array, int i, int j) {
    Integer temp = array[i];
    array[i] = array[j];
    array[j] = temp;;

  }
  
  /**
   * Run validation tests
   * @param args command-line args
   */
  public static void main(String[] args) {
    SortingAlgorithm.validate(new InsertionSort());
    System.out.println("Insertion has passed all tests.");

    // Fill an array with random numbers
    int N = 1433;
    Integer[] array = new Integer[N];
    for (int i = 0; i < array.length; i++) {
      array[i] = (int)(N*Math.random());
    }

    //Measuring runtime.
    SortingAlgorithm sorter = new InsertionSort();
    long start = System.nanoTime();
    sorter.sort(array);
    long end = System.nanoTime();
    double duration = (end-start)/(1e9);
    System.out.println("Array size: "+N);
    System.out.println("Total duration: "+duration);


  }

}
