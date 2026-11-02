package assignments.algorithms;
import assignments.datastructures.MinHeap;

public class MinHeapERA {
  /**
   * Create a min heap with N  values.
   * @param N number of values in the heap
   * @return created min heap
   */
  private static MinHeap<Integer> MinHeap(int N) {
    MinHeap<Integer> heap = new MinHeap<Integer>(); // Empty queue.
    for (int i = 0; i < N; i++) { // Add N values.
      heap.enqueue((int) (Math.random() * N) + 1); // Random number between 1 and N.
    }
    return heap;
  }

  /**
   * Test the runtime of dequeuing from a minheap.
   * @param args arguments given to the program
   */
  public static void main(String[] args) {
    for (int N = 10; N <= 1000000; N = N * 11 / 10 + 1) {
      MinHeap<Integer> heap = MinHeap(N);
      long start = System.nanoTime();
      heap.dequeue();
      long end = System.nanoTime();
      double duration = (end - start) / 1e9;
      System.out.println(N + "\t" + duration);
    }
  }
}