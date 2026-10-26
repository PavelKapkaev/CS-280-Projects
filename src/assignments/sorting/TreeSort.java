package assignments.sorting;
import assignments.datastructures.BinarySearchTree;

/**
 * Implement Tree Sort algorithm when sorting an array.
 * @param <T> type of values
 */
public class TreeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

  /** 
   * Sort the array from smallest to largest.
   * @param array array to sort
   */
  public void sort(T[] array) {
    BinarySearchTree<T> tree = new BinarySearchTree<T>();
    for (int i = 0; i < array.length; i++) { // Add the values to the tree.
      tree.add(array[i]);
    }
    // Copy sorted values back to the array.
    int i = 0;
    for (T value : tree) {
      array[i] = value;
      i++;
    }
  }
  /**
   * Test TreeSort.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SortingAlgorithm.validate(new TreeSort<>());
  }
}