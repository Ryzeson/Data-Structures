/**
 * Ryzeson Maravich
 * 10-18-2019
 * BSTree.java (hw5)
 * 
 * Binary Search Tree with Generic Data
 */

import java.util.Comparator;

public class BSTree<E> {

	public BTNode<E> root;
	public Comparator<E> cmp;
	public int count;

	public BSTree(Comparator<E> cmp) {
		this.cmp = cmp;
		root = null;
		count = 0;
	}

	/**
	 * adds data (in the new node) to the correct place in the tree according to the comparator given @ construction
	 * following the binary search tree insertion algorithm. Note that duplicate values should be added to the left of the first appearance
	 * @param data data to be added
	 */
	public void add(E data) {
		if (root == null) {
			root = new BTNode<>(data, null, null);
		}
		else {
			BTNode<E> curr = root;
			BTNode<E> trail = null;
			while (curr != null) {
				trail = curr;
				if (cmp.compare(data, curr.data) <= 0) {
					curr = curr.left;
				}
				else {
					curr = curr.right;
				}
			}

			//at this point, curr is null, so add it here
			curr = new BTNode<>(data, null, null);
			//set trail's child appropriately 
			if (cmp.compare(data, trail.data) <= 0) {
				trail.left = curr;
			}
			else {
				trail.right = curr;
			}
		}
		++count;
	}

	/**
	 * returns true if there is at least one data matching target using the binary search algorithm
	 * @param target data to be searched for
	 * @return true if there is at least one data matching target using the binary search algorithm
	 */
	public boolean contains(E target) {
		BTNode<E> curr = root;

		while (curr != null)  {
			if (curr.data.equals(target)) {
				return true;
			}
			else if (cmp.compare(target, curr.data) < 0) {
				curr = curr.left;
			}
			else {
				curr = curr.right;
			}
		}
		return false;
	}

	/**
	 * removes the first occurrence (closest to root) of target from this binary search tree and returns the removed value or null if target does not exist in this tree
	 * @param target data to be removed
	 * @return data removed
	 */
	public E remove(E target) {
		if (isEmpty()) {
			return null;
		}

		BTNode<E> curr = root;
		if (curr.data.equals(target)) {
			//if the root does not have a left node, set root equal to right child
			if (curr.left == null) {
				root = curr.right;
			}
			//otherwise set the root
			else {
				BTNode<E> largestLeft = removeLargestFromLeft(curr);
				root.data = largestLeft.data;
			}
			--count;
			return curr.data;
		}          

		BTNode<E> trail = null;
		while (curr != null && !curr.data.equals(target)) {
			trail = curr;
			if (cmp.compare(target, curr.data) < 0) {
				curr = curr.left;
			}
			else {
				curr = curr.right;
			}
		}

		if (curr == null) {
			return null;
		}
		else if (curr.isLeaf()) {
			if (trail.left == curr) {
				trail.left = null;
			}
			else {
				trail.right = null;
			}
		}
		else if (curr.left == null) {
			if (trail.left == curr) {
				trail.left = curr.right;
			}
			else {
				trail.right = curr.right;
			}
		}
		else {
			BTNode<E> largestLeft = removeLargestFromLeft(curr);
			E replacedData = curr.data;
			curr.data = largestLeft.data;
			--count;
			return replacedData;
		}
		--count;
		return curr.data;
	}

	/**
	 * returns the root node (necessary for visualization)
	 * @return the root node
	 */
	public BTNode<E> getRoot() {
		return root;
	}

	/**
	 * returns the number of data items stored in this tree
	 * @return the number of data items stored in this tree
	 */
	public int size() {
		return count;
	}

	/**
	 * returns true if the tree is empty and false otherwise
	 * @return true if the tree is empty and false otherwise
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * clears the tree
	 */
	public void clear() {
		root = null;
		count = 0;
	}

	/**
	 * returns a print-friendly String with entire content of this tree in level-order
	 * @return a print-friendly String with entire content of this tree in level-order
	 */
	public String toString() {
		return BTNode.levelorder(root);
	}

	/**
	 * removes and returns the largest node in the left subtree of the given node
	 * @param node node whose subtree is to be checked
	 * @return the largest node in the left subtree of the given node
	 */
	public static <E> BTNode<E> removeLargestFromLeft(BTNode<E> node) {
		BTNode<E> trail = node;
		BTNode<E> curr = node.left;

		//while curr has a right child, then there is a larger value
		while (curr.right != null) {
			trail = curr;
			curr = curr.right;
		}

		//found the largest value at this point
		//if trail.left is the current, then it is the largest (there was no right child)
		if (trail.left == curr) {
			trail.left = curr.left;
		}
		//otherwise, connect trail.right to the furthest right's left child
		else {
			trail.right = curr.left;
		}
		return curr;
	}

}