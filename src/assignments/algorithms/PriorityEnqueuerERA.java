package assignments.algorithms;
import assignments.datastructures.PriorityEnqueuer;

public class PriorityEnqueuerERA {
  /**
   * Create a priority queue with N values.
   * @param N number of values in the queue
   * @return created priority queue
   */
  private static PriorityEnqueuer<Integer> PriorityEnqueuer(int N) {
    PriorityEnqueuer<Integer> queue = new PriorityEnqueuer<Integer>();
    for (int i = 0; i < N; i++) {
      queue.enqueue((int) (Math.random() * N) + 1);
    }
    return queue;
  }

  /**
   * Test the runtime of dequeuing from a priority queue.
   * @param args arguments given to the program
   */
  public static void main(String[] args) {
    for (int N = 10; N <= 1000000; N = N * 11 / 10 + 1) {
      PriorityEnqueuer<Integer> queue = PriorityEnqueuer(N);
      long start = System.nanoTime();
      queue.dequeue();
      long end = System.nanoTime();
      double duration = (end - start) / 1e9;
      System.out.println(N + "\t" + duration);
    }
  }
}