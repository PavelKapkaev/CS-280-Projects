package assignments.datastructures;
import java.util.Iterator;
import adt.List;
import adt.Stack;


/// An extensible list backed by a chain of nodes.
/// 
/// The idea here is to wrap each datum in a larger structure, a *node*,
///  which also contains a pointer to the node containing the *next* element in the list.
/// This structure permits efficient insertion and deletion,
///  in the sense that it only requires rearranging pointers nearby where the change takes place.
/// 
/// However, this structure foregoes *random access*, i.e. easy access to arbitrary locations in the list.
/// In order to make any changes to a location in the middle of the list,
///  one must first traverse through the chain of nodes from the beginning of the list.
/// 
/// @param <T> the type of each element
public class LinkedList<T> implements List<T>, Iterable<T>, Stack<T> {
    private Node head;
    private int size;

    /**
     * Initialize an empty linked list.
     */
    public LinkedList() {
        this.head = null;
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
        Node place = head; // Variable place points to the head which is first node of the Linked List.
        for (int i = 0; i < index; i++) {  // Goes through the Linked List and returns needed index starting at 0.
          place = place.link; // Moves place variable to the next node.
          
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
        Node place = head; // Start at the first node.
        for (int i = 0; i < index; i++) { // Iterate through until the node that have to be changes is found.
          place = place.link;
        }
        place.data = value; // replace the data in the node with new value.
      
      
      }
    
    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        Node place = head; // Start at the beginning.
        while (place != null) { // Iterate untill the end of the list..
          if (place.data.equals(value)) { // If value in the node equals to the value that was requested return true.
            return true;
          }
          place = place.link; // if no match was found than move to the next node
        }
        return false; // In case nothing was found.
    }
    
    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;
        if (index == 0) { // If insertion happens at the head of the Linked List.
          head = new Node(value, head); // Create a new node and make it point to the previous first node.
        } 
        else { // If not first node.
          Node place = head; // start at the first node.
          for (int i = 0; i < index-1; i++) { // Go untill index -1 because i need the node im inserting to point to the node that goes after it.
            place = place.link;
          }
          Node newPlace = new Node(value, place.link); // Creates a new node with the value and make it point to the next node.
          place.link = newPlace; // Make place point to the new node.
        }
        size ++; // Increase the amount of nodes in the Linked List.

    }
    
    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;
        T deleted; // Stores variable that will be deleted.
        if (index == 0) { // If node that needs to be deleted is at index 0.
          deleted = head.data; // Deleted is the data at the head node. Saving it before deleting.
          head = head.link; // Since previous head node was deleted, new head node will be the next node in the list.
        }
        else { // If we are deleting not the head node.
          Node place = head; // Start at the beginning.
          for (int i= 0; i < index - 1; i++) { // Iterate through the Linked List to find the node that has to be deleted until the (index - 1)
            place = place.link; // Move place to the next node.
          }
          deleted = place.link.data;  // Stores the value of the node that will be deleted.
          place.link = place.link.link; // Previous node points to the node after deleted node.
        }
        size--; // Reduce size because one of the nodes was deleted.
        return deleted; // Return what was deleted.
    }

    /**
     * An encapsulation of a value with a pointer, allowing us to chain to another value.
     */
    private class Node {
        T data;
        Node link;

        /**
         * Initialize a node with no children.
         * @param data the data value
         * @param link the next node in the chain
         */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }


    /**
     * Creates iterator that will go through each value in the Linked List.
     *@return iterator for this Linked List
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node cursor = head; //keeps track of a node.
            public boolean hasNext() {return cursor != null;} // Check if there are more nodes.
            public T next() {T value = cursor.data; cursor = cursor.link; return value; } // Gets a value and moves to the next node.
        };

    }

    /** 
     * Check if stack is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
      return size == 0; //If size is 0, there are no nodes in the stack.
    }

    /**
     * Return value at the top of the stack, no removing.
     * @return value at the top of the stack
     */
    public T peek() {
      return head.data;
    }

    /**
     *  Remove and return the value at the top of the stack.
     * @return value removed from the stack
     */
    public T pop() {
      T value = head.data; // Save the value from the head.
      head = head.link; // Move head to the next node.
      size--; // Make the size smaller after removing a node.
      return value; // Return the value that was removed.
    }

    /**
     * Add a value to the top of the stack.
     * @param value value to add
     */
    public void push(T value) {
      head = new Node(value, head); // Create a new node and make it the new head.
      size ++; // Increase the size after adding a node.
    }
    
    /**
     * Run validation tests.
     * @param args command-line args
     */
      public static void main(String[] args) {
        List.validate(new LinkedList<>());
        Stack.validate(new LinkedList<>());

        // Test iterator.
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i ++) list.insert(0, i);
        Iterator<Integer> iter = list.iterator();
        for (int i = 5; i > 0; i --) assert iter.next().equals(i-1);
        assert !iter.hasNext();

        System.out.println("LinkedList passes all tests.");
      }
}