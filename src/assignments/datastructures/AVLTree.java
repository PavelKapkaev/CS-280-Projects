package assignments.datastructures;
import java.util.Iterator;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
///  comprised of nodes which have at most two children.
/// 
/// The binary search tree satisfies the binary search tree condition:
///  every node has a value greater than that of its less child,
///  and less than or equal to that of its right child.
/// 
/// @param <T> the type of each element, which must have a natural ordering
public class AVLTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty tree.
     */
    public AVLTree() {
        this.root = null;
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        return length(root);
    }
    /**
     * Count all nodes starting from this node.
     * @param root current node
     * @return number of nodes
     */
    private int length(Node root) {
        if (root == null) return 0; // If no node.
        return 1 + length(root.left) + length(root.right); // Count this node and both sides.
    }

    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        return degree(root);
    }

    /**
     * Find the largest number of children from this node.
     * @param root current node
     * @return largest number of children
     */
    private int degree(Node root) {
        int children = 0; // Number of children.
        if (root == null) return 0; // Stop if no node.
        children += (root.left != null) ? 1 : 0; // Count left child.
        children += (root.right!= null) ? 1 : 0; // Count right child.
        int left = degree(root.left); // Check left side.
        int right = degree(root.right); // Check right side.
        int max = children; // Start with this node.
        max = left > max ? left : max; // Check if left is bigger.
        max = right > max ? right : max; // Check if right i bigger.
        return max; // Return the biggest number.

    }

    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Find the height of a node.
     * @param node current node
     * @return height of the node
     */
    private int height(Node node) {
        if (node == null) return 0; // If no node.
        return node.height;
    }

    /**
     * Find the balance condition of a node.
     * @param node current node
     * @return difference between left and right heights
     */

    private int balanceCond(Node node) {
        if (node == null) return 0; // No node.
        return height(node.left) - height(node.right); // Find height difference.
    }

    /**
     * Update the height of a node after doing changes to it.
     * @param node current node
     */
    private void updateHeight(Node node) {
        int left = height(node.left); // Get left height.
        int right = height(node.right); // Get right height.
        int max = left > right ? left : right; // Find taller side.
        node.height = 1 + max; // Update height.
    }

    /**
     * Rotate a node to the right.
     * @param node current node
     * @return new root of the subtree
     */
    private Node rotateRight(Node node) {
        Node left = node.left; // Get left child.
        node.left = left.right; // Move right child to the left.
        left.right = node; // Move current node to the right.
        updateHeight(node); // Update old root height.
        updateHeight(left); // Update new root height.
        return left; // Return new root.
    }

    /**
     * Rotate a node to the left.
     * @param node current node
     * @return new root of the subtree
     */
    private Node rotateLeft(Node node) {
        Node right = node.right; // Get right child.
        node.right = right.left; // Move left child to the right.
        right.left = node; // Move current node to the left.
        updateHeight(node); // Update old root height.
        updateHeight(right); // Update new root height.
        return right; // Return new root.
    }

    /**
     * Balance a node using rotations.
     * @param node current node
     * @return balanced node
     */
    private Node rebalance(Node node) {
        updateHeight(node); // Update height.
        int balance = balanceCond(node); // Check balance.
        if (balance > 1) { // Left side is too heavy.
            if (balanceCond(node.left) < 0) node.left = rotateLeft(node.left); // Rotate left child.
            return rotateRight(node); // Rotate right.
        }
        if (balance < -1) { // Right side is too heavy.
            if (balanceCond(node.right) > 0) node.right = rotateRight(node.right); // Rotate right child.
            return rotateLeft(node); // Rotate left.
        }
        return node; // Already balanced.
    }

    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        LinkedList<T> list = new LinkedList<T>(); // Create an empty list.
        preorder(root, list); // Start at the root.
        return list.iterator(); // Return iterator.
    }

    /**
    * Add values to a list in preorder.
    * @param root current node
    * @param list list to add values to
    */
    private void preorder(Node root, LinkedList<T> list) {
        if (root == null) return; // If no node.
        list.insert(list.length(), root.data); // Add current node.
        preorder(root.left, list); // Go left.
        preorder(root.right, list); // Go right.
    }

    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        LinkedList<T> list = new LinkedList<T>(); // Create an empty list.
        inorder(root, list); // Start at the root.
        return list.iterator(); // Return iterator.
    }

    /**
     * Add values to a list in order.
     * @param root current node
     * @param list list to add values to
     */
    private void inorder(Node root, LinkedList<T> list) {
        if (root == null) return; // If no node.
        inorder(root.left, list); // Go left.
        list.insert(list.length(), root.data); // Add current node.
        inorder(root.right, list); // Go right.
    }

    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        LinkedList<T> list = new LinkedList<T>(); // Create an empty list.
        postorder(root, list); // Start at the root.
        return list.iterator(); // Return iterator.
    }

    /**
     * Add values to a list in postorder.
     * @param root current node
     * @param list list to add values to
     */
    private void postorder(Node root, LinkedList<T> list) {
        if (root == null) return; // If no node.
        postorder(root.left, list); // Go left.
        postorder(root.right, list); // Go right.
        list.insert(list.length(), root.data); // Add current node.
    }

    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        LinkedList<T> list = new LinkedList<T>(); // List for values.
        CircularLinkedList<Node> queue = new CircularLinkedList<Node>(); // Queue for nodes.
        if (root == null) return list.iterator(); // Stop if tree is empty.
        queue.enqueue(root); // Add root first.
        while (!queue.isEmpty()) { // While not empty.
            Node node = queue.dequeue(); // Get first node.
            list.insert(list.length(), node.data); // Add value to list.
            if (node.left != null) queue.enqueue(node.left); // Add left child.
            if (node.right != null) queue.enqueue(node.right); // Add right child.
        }
        return list.iterator();
    }

    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }


    /**
     * Check if a value is in the tree.
     * @param value value to find
     * @return true if found, false if not
     */
    public boolean contains(T value) {
        return contains(value, root); // Start at the root.
    }

    /**
     * Search for a value in the tree.
     * @param value value to find
     * @param node current node
     * @return true if found, false if not
     */
    private boolean contains(T value, Node node) {
        if (node == null) return false; // No node found.
        int compareResult = value.compareTo(node.data); // Compare values.
        if (compareResult < 0) return contains(value, node.left); // Search left.
        else if (compareResult > 0) return contains(value, node.right); // Search right.
        else return true; // Match.
    }

    /**
     * Add a value to the tree.
     * @param value value to add
     * @return true if added, false if exists
     */
    public boolean add(T value) {
        if (contains(value)) return false; // Value already exists.
        root = add(value, root); // Start at the root.
        return true; // Value added.
    }

    /**
     * Add a value starting from this node.
     * @param value value to add
     * @param node current node
     * @return updated node
     */
    private Node add(T value, Node node) {
        if (node == null) return new Node(value); // Create new node.
        int compareResult = value.compareTo(node.data); // Compare values.
        if (compareResult < 0) node.left = add(value, node.left); // Add left.
        else if (compareResult > 0) node.right = add(value, node.right); // Add right.
        return rebalance(node); // Balance the tree.
    }

    /**
     * Remove a value from the tree.
     * @param value value to remove
     * @return true if removed, false if not
     */
    public boolean remove(T value) {
        if (!contains(value)) return false; // Value not found.
        root = remove(value, root); // Start at the root.
        return true; // Value removed.
    }

    /**
     * Remove a value starting from this node.
     * @param value value to remove
     * @param node current node
     * @return updated node
     */
    private Node remove(T value, Node node) {
        if (node == null) return node; // Value not found.
        int compareResult = value.compareTo(node.data); // Compare values.
        if (compareResult < 0) node.left = remove(value, node.left); // Search left.
        else if (compareResult > 0) node.right = remove(value, node.right); // Search right.
        else if (node.left != null && node.right != null) { // Two children.
            Node min = node.right; // Start on the right.
            while (min.left != null) min = min.left; // Find the smallest node.
            node.data = min.data; // Replace value.
            node.right = remove(min.data, node.right); // Remove old node.
        }
        else node = (node.left != null) ? node.left : node.right; // Zero or one child.
        if (node == null) return null; // No node to balance.
        return rebalance(node); // Balance the tree.
    }

    /**
     * An encapsulation of a value with two pointers, suitable for a binary tree.
     */
    private class Node {
        T data;
        Node left;      // Left child.
        Node right;     // Right child.
        int height; // Height of this node.

        /**
         * Initialize a node with no children.
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new AVLTree<>());

        // Test right rotation.
        AVLTree<Integer> test = new AVLTree<>();
        test.add(30);
        test.add(20);
        test.add(10);
        assert test.height() == 2;
        assert testTraversal(test.preorder(), new int[]{20, 10, 30});

        // Test left rotation.
        test = new AVLTree<>();
        test.add(10);
        test.add(20);
        test.add(30);
        assert test.height() == 2;
        assert testTraversal(test.preorder(), new int[]{20, 10, 30});

        // Test left-right rotation.
        test = new AVLTree<>();
        test.add(30);
        test.add(10);
        test.add(20);
        assert test.height() == 2;
        assert testTraversal(test.preorder(), new int[]{20, 10, 30});

        // Test right-left rotation.
        test = new AVLTree<>();
        test.add(10);
        test.add(30);
        test.add(20);
        assert test.height() == 2;
        assert testTraversal(test.preorder(), new int[]{20, 10, 30});

        // Test contains.
        assert test.contains(10);
        assert test.contains(20);
        assert test.contains(30);
        assert !test.contains(40);

        // Test add.
        assert test.add(40);
        assert !test.add(20);
        assert test.length() == 4;
        assert test.degree() == 2;
        assert testTraversal(test.inorder(), new int[]{10, 20, 30, 40});

        // Test remove.
        assert test.remove(10);
        assert test.remove(30);
        assert !test.remove(100);
        assert test.length() == 2;
        assert testTraversal(test.inorder(), new int[]{20, 40});

        // Test empty tree.
        test = new AVLTree<>();
        assert test.length() == 0;
        assert test.height() == 0;
        assert test.degree() == 0;
        assert !test.contains(10);
        assert !test.remove(10);
        
        // Test many insertions.
        test = new AVLTree<>();
        for (int i = 1; i <= 100; i++) test.add(i);

        assert test.length() == 100;
        assert test.height() <= 7; // Check tree height.

        for (int i = 1; i <= 100; i++) assert test.contains(i); // Check all values.

        // Test many removals.
        for (int i = 1; i <= 50; i++) assert test.remove(i);

        assert test.length() == 50;
        assert test.height() <= 7; // Check tree height.

        for (int i = 1; i <= 50; i++) assert !test.contains(i); // Check removed values.
        for (int i = 51; i <= 100; i++) assert test.contains(i); // Check remaining values.

        System.out.println("AVLTree passes all tests.");
    }

    /**
     * Convenience method to test tree traversals.
     * @param iterator newly-constructed iterator
     * @param array expected values
     */
    private static boolean testTraversal(Iterator<Integer> iterator, int[] array) {
        boolean expected = true;
        for (int i = 0; i < array.length; i ++) {
            Integer next = iterator.next();
            expected = expected && next.equals(array[i]);
        }
        return expected && !iterator.hasNext();
    }
}