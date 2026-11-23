package assignments.algorithms;
import assignments.datastructures.VectorSet;

public class VectorSetERA {
  /**
   * Create a set with N random values.
   * @param N number of values in the set
   * @return created set
   */
  private static VectorSet<Integer> VectorSet(int N) {
    VectorSet<Integer> set = new VectorSet<Integer>(); // Empty set.
    for (int i = 0; i < N; i++) { // Add N values.
      set.add((int) (Math.random() * N) + 1); // Random number between 1 and N.
    }
    return set;
  }

  /**
   * Test the runtime of searching a vector set.
   * @param args arguments given to the program
   */
  public static void main(String[] args) {
    for (int N = 10; N <= 200000; N = N * 11 / 10 + 1) {
      VectorSet<Integer> set = VectorSet(N);
      long start = System.nanoTime();
      set.contains(N + 1);
      long end = System.nanoTime();
      double duration = (end - start) / 1e9;
      System.out.println(N + "\t" + duration);
    }
  }
}