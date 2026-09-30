package assignments.algorithms;
import assignments.datastructures.CircularLinkedList;

public class CircularLinkedListERA {
    private static CircularLinkedList<Integer> list(int N) { // Create list with N values.
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>(); // Create a new list.
        for (int i = 0; i < N; i++) { // Add N values to the lsit.
            list.insert(0, 0); // Add zero at the beginning.
        }
        return list;
    }

    public static void main(String[] args) {
        for (int N = 10000; N <= 1000000; N += 10000) { // Test different values of N.
            CircularLinkedList<Integer> list = list(N); // Create the lsit.
            long start = System.nanoTime(); // Timer starts.
            list.insert(0, 0); // Add zero at the beginning of the lsit.
            long end = System.nanoTime(); // Timer stops.
            double duration = (end - start) / 1e9; // Find time in seconds.
            System.out.println(N + "\t" + duration);
        }
    }
}