/**
 * Ryzeson Maravich
 * 10-18-2019
 * BTNode.java
 * 
 * Represents a node in a binary tree
 */
import java.util.LinkedList;
import java.util.Queue;

public class BTNode<E> {

	//package level access modifier: (none) or default
	E data;
	BTNode<E> left, right;

	public BTNode() {

	}

	public BTNode(E d, BTNode<E> l, BTNode<E> r) {
		data = d;
		left = l;
		right = r;
	}

	public String toString() {
		return (data == null) ? "" : data.toString();
	}

	/**
	 * returns the total number of nodes in the tree rooted at the node
	 * @param node		root of the tree
	 * @return total number of nodes in the tree
	 */
	public static <E> int countNodes(BTNode<E> node) {
		if(node == null) {
			return 0;
		}
		return 1 + countNodes(node.left) + countNodes(node.right);
	}

	/**
	 * returns the height of the tree
	 * @param node	root
	 * @return the height of the tree
	 */
	public static <E> int getHeight(BTNode<E> node) {
		if (node == null) {
			return 0;
		}
		int hL = getHeight(node.left);
		int hR = getHeight(node.right);

		return (1 + Math.max(hL, hR));
	}

	//tree traversal algorithm
	/**
	 * returns a print-friendly String representation of the tree visited in preorder
	 * @param node	root of the tree
	 * @return preorder traversal String of the tree
	 */
	public static <E> String preorder(BTNode<E> node) {
		if (node == null) {
			return "";
		}

		return node.data + " " + preorder(node.left) + " " + preorder(node.right);
	}

	/**
	 * returns a print-friendly String representation of the tree visited in inorder
	 * @param node	root of the tree
	 * @return inorder traversal String of the tree
	 */
	public static <E> String inorder(BTNode<E> node) {
		if (node == null) {
			return "";
		}

		return inorder(node.left) + "" + node.data + " " + inorder(node.right);
	}

	/**
	 * returns a print-friendly String representation of the tree visited in postorder
	 * @param node	root of the tree
	 * @return postorder traversal String of the tree
	 */
	public static <E> String postorder(BTNode<E> node) {
		if (node == null) {
			return "";
		}

		return postorder(node.left) + " " + postorder(node.right) + "" + node.data;
	}

	/**
	 * returns a print-friendly String representation of the tree visited in levelorder
	 * @param node	root of the tree
	 * @return levelorder traversal String of the tree
	 */
	public static <E> String levelorder(BTNode<E> node) {
		if (node == null) {
			return "";
		}

		String str = "";

		Queue<BTNode<E>> q = new LinkedList<BTNode<E>>();
		q.add(node);

		while (!q.isEmpty()) {
			BTNode<E> curr = q.remove();

			str += curr + " ";

			if(curr.hasLeft()) {
				q.add(curr.left);
			}

			if(curr.hasRight()) {
				q.add(curr.right);
			}
		}

		return str;
	}

	/**
	 * returns true if the node has a left child, false otherwise
	 * @return true if the node has a left child, false otherwise
	 */
	private boolean hasLeft() {
		return (left != null);
	}

	/**
	 * returns true if the node has a right child, false otherwise
	 * @return true if the node has a right child, false otherwise
	 */
	private boolean hasRight() {
		return (right != null);
	}

	/**
	 * returns true if the node has no children, false otherwise
	 * @return true if the node has no children, false otherwise
	public boolean isLeaf() {
		return (left == null && right == null);
	}

}