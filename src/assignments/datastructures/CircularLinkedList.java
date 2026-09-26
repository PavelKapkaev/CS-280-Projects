package assignments.datastructures;
import java.util.Iterator;
import adt.List;
import adt.Queue;



/// An extensible circular list backed by a chain of nodes.
/// Create an empty circular linked list.
/// @param <T> the type of each element
public class CircularLinkedList<T> implements List<T>, Iterable<T>, Queue<T> {
    private Node tail;
    private int size;
    public CircularLinkedList() { 
        this.tail = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
     * @return the number of items
     */
    public int length() {
        return size;
    }
    
    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;
        Node place = tail.link; // Start at the first node.
        for (int i = 0; i < index; i++) {  // Go through the Circular Linked List until the needed index.
          place = place.link; // Move place to the next node.
        }
        return place.data;
        
    }
    
    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;
        Node place = tail.link; // Start at the first node.
        for (int i = 0; i < index; i++) { // Iterate through until the node that have to be changed is found.
          place = place.link;
        }
        place.data = value; // Replace the data in the node with new value.
      
      
    }
    
    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
      Node place = tail.link; // Start at the first node.
      for (int i = 0; i < size; i++) { // Go through every node in the Circular Linked List.
          if (place.data.equals(value)) { // Check if the node has the value we are looking for.
              return true;
          }
          place = place.link; // Move to the next node.
      }
      return false; // Return false if the value wasn't found.
    }
    
  /**
  * Insert an item into the list
  * @param index the location of where to put the item
  * @param value the new value to put at the given location
  */
  public void insert(int index, T value) {
    assert 0 <= index && index <= this.size;
    if (size == 0) { // If list is empty.
        tail = new Node(value, null);
        tail.link = tail; // First node points to itself.
    }
    else if (index == 0) { // Insert at the beginning.
        Node newPlace = new Node(value, tail.link); // Create a new firt node.
        tail.link = newPlace; // Make a link that points to a new first node.
    }
    else {
        Node place = tail.link; // Start at the first node.
        for (int i = 0; i < index - 1; i++) { // Find the node before insertion.
            place = place.link; // Move to the next node.
        }
        Node newPlace = new Node(value, place.link);// Create the new node.
        place.link = newPlace; // Connect the previous node to the new node.
        if (index == size) { // If inserted at the end.
            tail = place.link; // Make the node that was just made the new tail.
        }
    }

    size++; // Increase after adding node.
  }
    
  /**
  * Remove an item from the list.
  * @param index the location to delete from
  * @return the value which was removed
  */
  public T delete(int index) {
    assert 0 <= index && index < this.size;
    T deleted; // To sore the value that will be deleted.
    if (size == 1) { // If there is only one node.
        deleted = tail.data; // Save the value
        tail = null; // List becomes empty.
    }
    else if (index == 0) { // Delete the first node.
        deleted = tail.link.data; // Save.
        tail.link = tail.link.link; // Tail points to the new first node.
    }
    else {
        Node place = tail.link; // Start at the first node.
        for (int i = 0; i < index - 1; i++) { // Find the node before delition.
            place = place.link; // Move to the next node.
        }
        deleted = place.link.data; // Save.
        place.link = place.link.link; // Skip deleted node.
        if (index == size - 1) { // If the last node was deleted.
            tail = place; // Previous node is a new tail.
        }
    }
    size--; // Decrease because Circular Linked List doesnt have one node anymore.
    return deleted; // Return value that was deleted.
  }

    /**
    * An encapsulation of a value with a pointer, allowing us to chain to another value.
    */
    private class Node {
        T data;
        Node link;
        /**
        * Initialize a node.
        * @param data the data value
        * @param link the next node in the chain
        */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }


  /**
  * Creates iterator that will go through each value in the Circular Linked List.
  * @return iterator for this Circular Linked List
  */
  public Iterator<T> iterator() {
    Node first = tail.link;
    return new Iterator<T>() {
        Node cursor = first; // Keeps track of the current node.
        int count = 0; // Keeps track of how many nodes were visited.
        int iSize = size; // Save the size when the iterator is created.
        public boolean hasNext() {
            return count < iSize; // Check if there are more nodes.
        }
        public T next() {
            T value = cursor.data;
            cursor = cursor.link; // Move cursor to the next node.
            count++; // Increase.
            return value; // Return the value from the current node.
        }
    };
  }

  /**  (non-Javadoc)
   * Remove an item from a queue.
   * @return value that was removed
   */
  public T dequeue() {
    return delete(0);
  }

  /**
   *Add an item to a queue.
   *@param value value to add
   */
  public void enqueue(T value) {
    insert(size, value);
  }

  /**  (non-Javadoc)
   * Check if the collection is empty.
   * @return true iff the collection is empty
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**  (non-Javadoc)
   * Next item to be removed from te queue.
   * @return next value to be dequeued
   */
  public T peek() {
    return at(0);
  }
  
  /**
  * Run validation tests.
  * @param args command-line args
  */
  public static void main(String[] args) {
    List.validate(new CircularLinkedList<>());
    Queue.validate(new CircularLinkedList<>());
    
    // Test iterator.
    CircularLinkedList<Integer> list = new CircularLinkedList<>();
    for (int i = 0; i < 5; i++) list.insert(0, i);
    Iterator<Integer> iter = list.iterator();
    for (int i = 5; i < 10; i++) list.insert(0, i);
    for (int i = 5; i > 0; i--) assert iter.next().equals(i - 1);
    assert !iter.hasNext();

    System.out.println("CircularLinkedList passes all tests.");
  }
}