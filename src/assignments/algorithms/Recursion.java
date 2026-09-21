package assignments.algorithms;

public class Recursion {

    public static int recursion(int n) {
        if (n <= 1) {
          return 1;
        }
        return recursion(n-1) + recursion(n-2);
    }

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