package assignments.datastructures;
import adt.PriorityQueue;

/**
 * A priority queue that removes values in O(1).
 * @param <T> type of values
 */
public class PriorityDequeuer<T extends Comparable<T>> implements PriorityQueue<T> {
  private Node head;
  private class Node {
    T value;
    Node next;
    Node(T value) {
      this.value = value;
      this.next = null;
    }
  }

  /**
   * Create an empty queue.
   */
  public PriorityDequeuer() {
    head = null;
  }

  /**
   * Check if the queue is empty.
   * @return true if empty
   */
  public boolean isEmpty() {
    return head == null; // Check if there is a first node.
  }

  /**
   * Add a value to the queue in priority order.
   * @param value value to add
   */
  public void enqueue(T value) {
    Node newNode = new Node(value); // Create a new node.

    if (head == null || value.compareTo(head.value) < 0) {
      newNode.next = head; // Connect new node to the first node.
      head = newNode; // Make new node the first node.
      return;
    }
    Node current = head; // Start at the first node.
    while (current.next != null && value.compareTo(current.next.value) >= 0) {
      current = current.next; // Move to the next node.
    }
    newNode.next = current.next; // Connect to the next node.
    current.next = newNode; // Add new node after current.
  }

  /**
   * Remove and return the first value in the queue.
   * @return removed value
   */
  public T dequeue() {
    if (head == null) return null; // Check if the queue is empty.
    T value = head.value; // Save the first value.
    head = head.next; // Move head to the next node.
    return value; // Return the removed value.
  }

  /**
   * Return the first value without removing it.
   * @return first value in the queue
   */
  public T peek() {
    if (head == null) return null; // Check if the queue is empty.
    return head.value; // Return the first value.
  }

  /**
   * Test the priority queue.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PriorityQueue.validate(new PriorityDequeuer<Integer>());
    System.out.println("PriorityDequeuer passes all tests.");
  }

}