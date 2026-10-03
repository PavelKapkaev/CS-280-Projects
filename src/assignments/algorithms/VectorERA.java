package assignments.algorithms;
import assignments.datastructures.Vector;

public class VectorERA {
    /**
     * Create a vector with N values.
     * @param N number of values in the vector
     * @return created vector
     */
    private static Vector<Integer> Vector(int N) { // Vector with N values.
        Vector<Integer> vector = new Vector<Integer>(); // New vector.
        for (int i = 0; i < N; i++) { // Add N values.
            vector.insert(vector.length(), 0); // Add zero in the beginning. 
        }
        return vector;
    }
    /**
     * Test the runtime of prepending to a vector.
     * @param args arguments given to the program
     */
    public static void main(String[] args) {
        for (int N = 1000; N <= 100000; N += 1000) {
            Vector<Integer> vector = Vector(N);
            long start = System.nanoTime();
            vector.insert(0, 0);
            long end = System.nanoTime();
            double duration = (end - start) / 1e9;
            System.out.println(N + "\t" + duration);
        }
    }
}