package assignments.algorithms;
import assignments.datastructures.LinkedList;

public class LinkedListERA {
    private static LinkedList<Integer> linkedlist(int N) { // Linked list with N values.
        LinkedList<Integer> llist = new LinkedList<Integer>(); // New list.
        for (int i = 0; i < N; i++) { // Add N values to the lsit.
            llist.insert(0, 0); // Add zero in the beginning.
        }
        return llist;
    }

    public static void main(String[] args) {
        for (int N = 10000; N <= 1000000; N += 10000) { // Different values of N.
            LinkedList<Integer> list = linkedlist(N); // Create lsit.
            long start = System.nanoTime(); // Timer starts.
            list.insert(0, 0); // Add zero in the beginning.
            long end = System.nanoTime(); // Timer stops.
            double duration = (end - start) / 1e9; // time in seconds
            System.out.println(N + "\t" + duration);
        }
    }
}