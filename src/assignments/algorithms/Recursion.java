package assignments.algorithms;

public class Recursion {
  /**
     * Calculate a Fibonacci number using recursion.
     * @param n Fibonacci number position
     * @return calculated Fibonacci number
     */
    public static int recursion(int n) {
        if (n <= 1) {
          return 1;
        }
        return recursion(n-1) + recursion(n-2);
    }
    /**
     * Test the runtime of the recursion method.
     * @param args arguments given to the program
     */
    public static void main(String[] args) {
        for (int N = 1; N <= 40; N++) {
          long start = System.nanoTime();
          int answer = recursion(N);
          long end = System.nanoTime();
          double duration = (end - start) / 1e9;
          System.out.println(N + "\t" + duration);
        }
    }
    
}