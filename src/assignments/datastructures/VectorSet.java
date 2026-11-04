package assignments.datastructures;
import adt.Set;
import java.util.Iterator;

/**
 * A set that uses a vector to store values.
 * @param <T> type of values
 */
public class VectorSet<T> implements Set<T> {

  private Vector<T> vector = new Vector<>(); // Create a vector.

  /**
   * Get the number of values in the set.
   * @return number of values
   */
  public int length() {
    return vector.length();
  }

  /**
   * Check if a value is in the set.
   * @param value value to find
   * @return true if found, false if not
   */
  public boolean contains(T value) {
      return vector.contains(value); // Search for the value.
  }

  /**
   * Add a new value to the set if it does not exist.
   * @param value value to add
   * @return true if added, false if exists
   */
  public boolean add(T value) {
    if (contains(value)) return false; // Check for duplicates.
    vector.insert(vector.length(), value); // Add the value at the end.
    return true;
  }

  /**
   * Remove a value from the set.
   * @param value value to remove
   * @return true if removed, false if not found
   */
  public boolean remove(T value) {
    for (int i = 0; i < vector.length(); i++) { // Go through the vector.
      if (vector.at(i).equals(value)) { // Check if values match.
        vector.delete(i); // Remove the value.
        return true;
      }
    }
    return false; // Value not found.
  }

  /**
   * Go through the values in the set.
   * @return iterator for the values
   */
  public Iterator<T> iterator() {
    return vector.iterator();
  }

  /**
   * Run Set interface validation.
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Set.validate(new VectorSet<Integer>());
  }
}