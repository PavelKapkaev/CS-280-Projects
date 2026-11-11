package assignments.algorithms;
import assignments.sorting.SelectionSort;

public class SelectionSortERA {
  /**
   * Create an array with N random values.
   * @param N number of values in the array
   * @return created array
   */
  private static Integer[] SelectionSort(int N) {
    Integer[] array = new Integer[N];
    for (int i = 0; i < N; i++) {
      array[i] = (int) (Math.random() * N); // Random number between 0 and N-1.
    }
    return array;
  }

  /**
   * Test the runtime of selection sort.
   * @param args arguments given to the program
   */
  public static void main(String[] args) {
    for (int N = 10; N <= 10000; N = N * 11 / 10 + 1) {
      Integer[] array = SelectionSort(N);
      SelectionSort<Integer> sort = new SelectionSort<Integer>();
      long start = System.nanoTime();
      sort.sort(array);
      long end = System.nanoTime();
      double duration = (end - start) / 1e9;
      System.out.println(N + "\t" + duration);
    }
  }
}