package assignments.algorithms;
import assignments.datastructures.PriorityDequeuer;

public class PriorityDequeuerERA {
  /**
   * Create a priority queue with N values.
   * @param N number of values in the queue
   * @return created priority queue
   */
  private static PriorityDequeuer<Integer> PriorityDequeuer(int N) {
    PriorityDequeuer<Integer> queue = new PriorityDequeuer<Integer>(); // Empty queue.
    for (int i = 0; i < N; i++) { // Add N values.
      queue.enqueue((int) (Math.random() * N) + 1); // Random number between 1 and N.
    }
    return queue;
  }

  /**
   * Test the runtime of dequeuing from a priority queue.
   * @param args arguments given to the program
   */
  public static void main(String[] args) {
    for (int N = 10; N <= 40000; N = N * 11 / 10 + 1) {
      PriorityDequeuer<Integer> queue = PriorityDequeuer(N);
      long start = System.nanoTime();
      queue.dequeue();
      long end = System.nanoTime();
      double duration = (end - start) / 1e9;
      System.out.println(N + "\t" + duration);
    }
  }
}