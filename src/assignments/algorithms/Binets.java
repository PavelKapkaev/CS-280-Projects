package assignments.algorithms;

public class Binets {

    public static double binets(int n) {
        double sqrt5 = Math.sqrt(5);
        double p = (1+sqrt5) / 2;
        double ps = (1-sqrt5) / 2;
        double answer = (Math.pow(p, n) - Math.pow(ps, n)) / sqrt5;

        return answer;
    }

    public static void main(String[] args) {
        for (int N = 10000000; N <= 1000000000; N += 10000000) {
          long start = System.nanoTime();
          double answer = binets(N);
          long end = System.nanoTime();
          double duration = (end - start) / 1e9;
          System.out.println(N + "\t" + duration);
        }
    }
    
}