package assignments.datastructures;

import adt.List;

/// An extensible list backed by an array buffer.
/// 
/// The idea here is to store your data in an array larger than it has to be.
/// This gives you room to add more items to the end, without having to reallocate memory every time.
/// You simply need to keep track of which parts of the array are currently in use.
/// 
/// Eventually, the array buffer *will* run out of space.
/// Then you need to allocate an even larger buffer, and copy the present buffer to the new one.
/// This is a very expensive operation, so you want to make sure it occurs very infrequently.
/// 
/// @param <T> the type of each element
public class Vector<T> implements List<T> {
    /** The initial amount of buffer space in a newly-created vector. */
    public static final int INITIAL_BUFFER_SIZE = 10;
    private T[] array;
    private int size;

    /**
     * Initialize an empty vector.
     */
    @SuppressWarnings("unchecked")
    public Vector() {
        // Generic types (i.e. `T`) don't technically exist at runtime, so you have to allocate arrays generically and then cast them.
        // This is normally bad practice and generates a warning, hence the @SuppressWarnings tag before the method.
        this.array = (T[])(new Object[INITIAL_BUFFER_SIZE]);
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
     * @return the number of items
     */
    public int length() {
        return size; // Return the number of items in the vector.
    }
    
    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size; // Make sure the index is valid.

        return array[index]; // Return the items at that index.
    }
    
    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size; // Make sure the index is valid.

        array[index] = value; // Replace the item at that index.
    }
    
    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        for (int i =0; i < size; ++i) { // Check every item for the value.
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false; // If the item was not found.
    }
    
    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        // You may use assert statements to enforce pre-conditions at runtime.
        // Note this function has a somewhat different pre-condition!
        assert 0 <= index && index <= this.size; // Make sure the index is valid.

        if (size == array.length) { // Extend the array if needed.
            resize(2 * array.length);

        } 

        for (int i = size - 1; i >= index; i--) { // Move all items to the right.
            array[i+1] = array [i];
        }    
        array[index] = value; // Add new item.
        size = size + 1;

    }
    
    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        // You may use assert statements to enforce pre-conditions at runtime.
        assert 0 <= index && index < this.size; // Make sure the index is valid.

        T removed = array[index]; // Save item before removing it.

        for (int i = index; i < size - 1; i++ ) { // Move items to the left.
            array[i] = array[i+1];
        }
        array[size-1] = null; // Delete the old item at the end.
        size = size -1; // Make the size smaller.
        return removed; // Return removed item.
    }

    /**
     * Resize the internal buffer array.
     * 
     * This method involves copying from the current buffer to a newly allocated one.
     * 
     * @param newSize the new size of the internal buffer array
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newArray = (T[])(new Object[newSize]); // Make array that is bigger.
        for (int i = 0; i < size; i++) { // Copy from old to new Array.
            newArray[i] = array[i];
        }
        array = newArray; // Use new array.

    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new Vector<>());
        System.out.println("Vector passes all tests.");
    }
    
}
