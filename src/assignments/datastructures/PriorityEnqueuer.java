package assignments.datastructures;
import adt.PriorityQueue;

/**
 * A priority queue that adds values in O(1) time.
 * @param <T> type of values
 */
public class PriorityEnqueuer<T extends Comparable<T>> implements PriorityQueue<T> {
  private Node head;
  private Node tail;

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
  public PriorityEnqueuer() {
    head = null;
    tail = null;
  }

  /**
   * Check if the queue is empty.
   * @return true if empty
   */
  public boolean isEmpty() {
    return head == null; // Check if there is a first node.
  }

  /**
   * Add a value to the end of the queue.
   * @param value value to add
   */
  public void enqueue(T value) {
    Node newNode = new Node(value); // Create a new node.
    if (head == null) {
      head = newNode; // Make new node the first node.
      tail = newNode; // Make new node the last node.
    } 
    else {
      tail.next = newNode; // Connect to the last node.
      tail = newNode; // Update the last node.
    }
  }

  /**
   * Remove and return the smallest value.
   * @return removed value
   */
  public T dequeue() {
    if (head == null) return null; // Check if the queue is empty.
    Node smallest = head; // Start with the first node.
    Node beforeSmallest = null;
    Node current = head;
    while (current.next != null) {
      if (current.next.value.compareTo(smallest.value) < 0) {
        smallest = current.next; // Save the smallest node.
        beforeSmallest = current; // Save the node before it.
      }
      current = current.next; // Move to the next node.
    }
    if (beforeSmallest == null) {
      head = head.next; // Remove the first node.
    } 
    else {
      beforeSmallest.next = smallest.next; // Remove the smallest node.
    }
    if (smallest == tail) tail = beforeSmallest; // Update the last node.
    return smallest.value; // Return the removed value.
  }

  /**
   * Return the smallest value but dont remove it.
   * @return smallest value in the queue
   */
  public T peek() {
    if (head == null) return null; // Check if the queue is empty.
    T smallest = head.value; // Start with the first value.
    Node current = head.next;
    while (current != null) {
      if (current.value.compareTo(smallest) < 0) {
        smallest = current.value; // Save the smallest value.
      }
      current = current.next; // Move to the next node.
    }
    return smallest; // Return the smallest value.
  }

  /**
   * Test the priority queue.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PriorityQueue.validate(new PriorityEnqueuer<Integer>());
    System.out.println("PriorityEnqueuer passes all tests.");
  }
} 
