/**
 * BTree.java
 * Ryzeson Maravich
 * 10-25-2019
 * 
 * Generic B-Tree
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BTree<E> {

	public Comparator<E> cmp;
	public int degree;
	public int count;

	public BTreeNode<E> root;

	/**
	 * Sets up and initializes instance variables accordingly
	 * @param c comparator
	 * @param deg degree
	 */
	public BTree(Comparator<E> c, int deg) {
		cmp = c;
		degree = deg;
	}

	/**
	 * looks through the data values in ptr and returns the child which might belong to target
	 * @param ptr node being examined
	 * @param target target data
	 * @return the child to which target might belong. If ptr is a leaf node, it returns null
	 */
	private static <E> BTreeNode<E> stepDown(BTreeNode<E> ptr, E target) {
		if (ptr.isLeaf()) {
			return null;
		}
		//finds where the data should go, which is the same index as which child it should go to
		int index = ptr.findInsertPos(target);
		return ptr.getChild(index);
	}

	/**
	 * finds and returns the leaf node to which target should be added OR belong by repeatedly calling the stepDown method from above until a leaf node is reached.
	 * @param target to be hypothetically placed
	 * @return the leaf node to which target should be added OR belong
	 */
	private BTreeNode<E> findLeaf(E target) {
		if (root.isLeaf()) {
			return root;
		}
		else {
			BTreeNode<E> node = stepDown(root, target);
			while (!node.isLeaf())	{
				node = stepDown(node, target);
			}
			return node;
		}
	}

	/**
	 * add new_data to the tree according to the algorithm described below assuming that no duplicate data is passed to this method
	 * @param new_data data to be added
	 */
	public void add(E new_data) {
		if(root == null) {
			root = new BTreeNode<E>(null, cmp, degree);
			root.add(new_data);
		}
		else {
			BTreeNode<E> node = findLeaf(new_data);
			BTreeNode<E> newParent = node.add(new_data);

			//set root if necessary (new parent was created)
			if(newParent != null) {
				root = newParent;
			}
		} 
		count++;
	}

	/**
	 * finds and returns the node containing target if it exists in this tree
	 * @param target data node is supposed to hold
	 * @return the node containing target if it exists in this tree or null if target is not in the tree
	 */
	private BTreeNode<E> getNode(E target) {
		BTreeNode<E> ptr = root;
		while(ptr != null) {
			if(ptr.contains(target)) {
				return ptr;
			}
			ptr = stepDown(ptr, target);
		}
		return null;
	}

	/**
	 * returns the content of the tree as a String for easy printing following the level-order traversal algorithm
	 * @return the content of the tree as a String for easy printing following the level-order traversal algorithm
	 */
	public String toString() {
		if (root == null) {
			return "";
		}
		BTreeNode<E> node = root;

		String str = "";

		Queue<BTreeNode<E>> q = new LinkedList<BTreeNode<E>>();
		q.add(node);

		while (!q.isEmpty()) {
			BTreeNode<E> curr = q.remove();

			str += curr.data + " ";

			if(!curr.isLeaf()) {
				q.addAll(curr.getChildren());
			}
			
		}

		return str;
	}

	/**
	 * returns the degree of this tree
	 * @return the degree of this tree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * returns the pointer to the root of this tree
	 * @return the pointer to the root of this tree
	 */
	public BTreeNode<E> getRoot() {
		return root;
	}
	
	/**
	 * returns true if target is found in this tree, false otherwise
	 * @param target	target being searched for
	 * @return true if target is found in this tree, false otherwise
	 */
	public boolean contains(E target) {
		return getNode(target) != null;
	}
	
	/**
	 * Removes target if it exists in this tree
	 * @param target	target being removed
	 * @return true if target was removed, false if not in the tree
	 */
	public boolean remove(E target) {
		BTreeNode<E> node = getNode(target);
		if (node == null) {
			return false;
		}
		else {
			BTreeNode<E> joinedNode = node.remove(target);
			
			if (joinedNode != null) {
				root = joinedNode;
				root.parent = null;
			}
			if(root.dataSize() == 0 ) {
				root = null;
			}
			--count;
			return true;
		}
	}
}