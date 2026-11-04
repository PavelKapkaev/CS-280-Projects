package assignments.datastructures;
import adt.Set;
import java.util.Iterator;

/**
 * A set that uses binary search tree to store values.
 * @param <T> type of values
 */
public class TreeSet<T extends Comparable<T>> implements Set<T> { 

  private BinarySearchTree<T> tree = new BinarySearchTree<>(); // Create a tree.

  /**
   * Get the number of values in the set.
   * @return number of values
   */
  public int length() {
    return tree.length();
  }

  /**
   * Check if a value is in the set.
   * @param value value to find
   * @return true if found, false if not
   */
  public boolean contains(T value) {
    return tree.contains(value); // Search for the value.
  }

  /**
   * Add a new value to the set if it not exsts.
   * @param value value to add
   * @return true if added, false if exists
   */
  public boolean add(T value) {
    if (tree.contains(value)) return false; // Check for duplicates.
    return tree.add(value); 
  }

  /**
   * Remove a value from the set.
   * @param value value to remove
   * @return true if removed, false if not found
   */
  public boolean remove(T value) {
    return tree.remove(value);
  }

  /**
   * Go through the values in order.
   * @return iterator for the values
   */
  public Iterator<T> iterator() {
    return tree.iterator();
  }

  /**
   * Run Set interface validation.
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Set.validate(new TreeSet<Integer>());
  }
}