package assignments.datastructures;
import adt.Map;
import adt.OrderedPair;
import java.util.Iterator;

/**
 * Map that stores key-value pairs in hash table.
 * Every bucket can have 5 pairs or less.
 * @param <K> key type
 * @param <V> valur typr
 */
public class HashMap<K, V> implements Map<K, V> {
    private Vector<KeyValuePair<K, V>>[] table; // Array of buckets.
    private int size = 0; // Number of key value pairs in the map.

    /**
     * Create empty hash map.
     */
    public HashMap() {
        table = (Vector<KeyValuePair<K, V>>[]) new Vector[10]; // Array with 10 buckets.
        for (int i = 0; i < table.length; i++) { // Go through every bucket.
            table[i] = new Vector<>(); // Empty vector for every bucket.
        }
    }

    /**
     * Find the bucket index for a key.
     * @param key key to find
     * @return bucket index
     */
    private int index(K key) {
        int bucket = key.hashCode() % table.length; // Find bucket index. For ex. 25 % 10 = 5 means bucket 5.
        if (bucket < 0) bucket += table.length; // Fix if negative number. If -5 then -5 + 10 = 5.
        return bucket;
    }

    /**
     * Get the number of key value pairs in the map.
     * @return number of pairs
     */
    public int length() {
        return size;
    }

    /**
     * Check if a key is in the map.
     * @param key key needed to find
     * @return true if found, false if not
     */
    public boolean contains(K key) {
        for (KeyValuePair<K, V> pair : table[index(key)]) { // Check every pair in the bucket.
            if (pair.first().equals(key)) return true; // If equals than true.
        }
        return false; // No.
    }

    /**
     * Get the value for a key.
     * @param key the key to look for
     * @return value connected to key, or null if not found
     */
    public V get(K key) {
        for (KeyValuePair<K, V> pair : table[index(key)]) { // Check every pair in the bucket.
            if (pair.first().equals(key)) return pair.second(); // Check if mstches and return value if does.
        }
        return null; // No.
    }

    /**
     * Insert or replace a key value pair in the map.
     * @param key the key to look for
     * @param value the value to assign to key
     * @return the value previously associated with key, or null if key was not previously in the map
     */
    public V put(K key, V value) {
        for (KeyValuePair<K, V> pair : table[index(key)]) { // Check every pair in the bucket to find where key is at.
            if (pair.first().equals(key)) { // Check if key matches.
                V oldValue = pair.second(); // Save old value before change it.
                pair.setValue(value); // And put new value instead old.
                return oldValue;
            }
        }
        table[index(key)].push(new KeyValuePair<>(key, value)); // Add new pair. Creates new pair, then finds bucket that is correct and adds it.
        size++; // Increae size by one because added new pair.
        if (table[index(key)].length() > 5) rehash(); // Check if more than 5, and rehash if bucket has more than 5 pairs.
        return null;
    }

    /**
     * Remove a key-value pair from the map.
     * @param key the key to look for
     * @return the value previously associated with key, or null if key was not previously in the map
     */
    public V remove(K key) {
        Vector<KeyValuePair<K, V>> bucket = table[index(key)]; // Find the right bucket and store it.
        for (int i = 0; i < bucket.length(); i++) { // Go through every pair in this bucket.
            if (bucket.at(i).first().equals(key)) { // Check if the key matches.
                V oldValue = bucket.at(i).second(); // Save old value.
                bucket.delete(i); // Remove pair from the bucket.
                size--;
                return oldValue;
            }
        }
        return null; // If key wasnt found.
    }

    /**
     * Increase the table size if more than 5 pairs are in one bucket.
     * Doubles the old table and moves all pairs into the new table.
     */
    private void rehash() {
        Vector<KeyValuePair<K, V>>[] oldTable = table; // Save old table.
        int newSize = table.length * 2; // Double the size.
        int maxBucket = 6; // Start over 5.
        while (maxBucket > 5) { // To repeat until there is no bucket that has more than 5 pairs.
            table = (Vector<KeyValuePair<K, V>>[]) new Vector[newSize]; // New table more bucketsm.
            for (int i = 0; i < table.length; i++) { // Go through every bucket.
                table[i] = new Vector<>(); // Create empty vector for every bucket.
            }
            maxBucket = 0;
            for (Vector<KeyValuePair<K, V>> bucket : oldTable) { // Go through every bucket.
                for (KeyValuePair<K, V> pair : bucket) { // Go through every pair.
                    int i = index(pair.first()); // Find bucket index in the new table.
                    table[i].push(pair); // Add pair to new bucket.
                    if (table[i].length() > maxBucket) { // Check if this bucket has the most pairs so far.
                        maxBucket = table[i].length(); // Save the biggest bucket size.
                    }
                }
            }
            newSize = newSize * 2; // Prepare ouble size if needed again.
        }
    }

    /**
     * Go through all key value pairs in the map.
     * @return iterator for the pairs
     */
    public Iterator<OrderedPair<K, V>> iterator() {
        Vector<OrderedPair<K, V>> values = new Vector<>(); // Vector to store all pairs.
        for (Vector<KeyValuePair<K, V>> bucket : table) { // Go thrugh every bucket in the table.
            for (KeyValuePair<K, V> pair : bucket) { // Through every pair.
                values.push(pair); // Add pair to the vector.
            }
        }
        return values.iterator();
    }

    /**
     * Tests and validation.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Map.validate(new HashMap<String, Integer>());
        HashMap<Integer, String> map = new HashMap<>(); // Create new map.
        for (int i = 0; i < 6; i++) { // Add 6 pairs to the same bucket.
            map.put(i * 10, "Value" + i); // Add keys and values
        }
        assert map.table.length > 10; // Check if table was rehashed.
        for (int i = 0; i < 6; i++) { // Check all 6 pairs.
            assert map.contains(i * 10); // Check if keys exist.
            assert map.get(i * 10).equals("Value" + i); // Check if values correct.
        }
        assert map.length() == 6; // Check siif map has 6 pairs.
        System.out.println("HashMap passes all tests.");
    }
}