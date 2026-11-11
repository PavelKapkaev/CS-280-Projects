package assignments.datastructures;
import adt.Set;
import java.util.Iterator;

/**
 * A set that uses a hash table to store values.
 * Each bucket can have up to five values.
 * @param <T> type of values
 */
public class HashSet<T> implements Set<T> {
  private VectorSet<T>[] table; // Array of buckets.
  private int size = 0; // Numbers of values.
  /**
   * Create an empty hash set.
   */
  public HashSet() {
    table = (VectorSet<T>[]) new VectorSet[10]; // Create buckets.
    for (int i = 0; i < table.length; i++) { // Go through every bucket.
      table[i] = new VectorSet<>(); // Empty set for every bucket.
    }
  }

  /**
   * Find the bucket index for a value.
   * @param value value to find
   * @return bucket index
   */
  private int index(T value) {
    int result = value.hashCode() % table.length; // Find index of bucket.
    if (result < 0) result += table.length; // If negative index.
    return result;
  }

  /**
   * Get the number of values in the set.
   * @return number of values
   */
  public int length() {
    return size;
  }

  /**
   * Check if a value is in the set.
   * @param value value to find
   * @return true if found, false if not
   */
  public boolean contains(T value) {
    return table[index(value)].contains(value);
  }

  /**
   * Add a new value if its not in the set yet.
   * @param value value to add
   * @return true if added and false if it alreasy exists
   */
  public boolean add(T value) {
    if (!table[index(value)].add(value)) return false; // No duplicates.
    size++;
    if (table[index(value)].length() > 5) rehash(); // If bucket has more than 5 values rehash.
    return true;
  }

  /**
   * Remove a value from the set.
   * @param value value that needs removed
   * @return true if removed or false if not found
   */
  public boolean remove(T value) {
    if (!table[index(value)].remove(value)) return false; // Check if was removed.
    size--;
    return true;
  }

  /**
   * Increase the table size if more than 5 in one bucket and move all values.
   * Doubles the table so more ways to spread numbers in to buckets
   */
  private void rehash() {
    VectorSet<T>[] oldTable = table; // Save old table.
    int newSize = table.length * 2; // Double the size.
    int maxBucket = 6; // Start over 5.
    while (maxBucket > 5) { 
      table = (VectorSet<T>[]) new VectorSet[newSize]; // New table.
      for (int i = 0; i < table.length; i++) {
        table[i] = new VectorSet<>(); // Create empty bucket.
      }
      maxBucket = 0;
      for (VectorSet<T> bucket : oldTable) { // Go through all buckets from old table.
        for (T value : bucket) { // Go through values in buckets.
          int i = index(value); // Find bucket.
          table[i].add(value); // Add value to the new table.
          if (table[i].length() > maxBucket) { // Check bucket size.
            maxBucket = table[i].length(); // Update max.
          }
        }
      }
      newSize = newSize * 2; // Double size again.
    }
  }

  /**
   * Go through all values in the set.
   * @return iterator for the values
   */
  public Iterator<T> iterator() {
    VectorSet<T> values = new VectorSet<>(); // Set to store values.
    for (VectorSet<T> bucket : table) { // Go through buckets and values.
      for (T value : bucket) {
        values.add(value); // Add.
      }
    }
    return values.iterator();
  }

  /**
   * Tests and validation.
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Set.validate(new HashSet<Integer>());
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < 6; i++) { // Add 6 values to one bucket.
      set.add(i * 10);
    }
    assert set.table.length > 10; // Check if rehashed.
    for (int i = 0; i < 6; i++) {
      assert set.contains(i * 10); // Check if values there.
    }
    assert set.length() == 6; // Check size
    System.out.println("HashSet passes all tests.");
  }
}
