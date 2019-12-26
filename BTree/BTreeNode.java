/**
 * BTreeNode.java
 * Ryzeson Maravich
 * 10-25-2019
 * 
 * Generic B-Tree node
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BTreeNode<E> {

	BTreeNode<E> parent;
	Comparator<E> cmp;
	ArrayList<E> data;
	ArrayList<BTreeNode<E>> children;
	int degree;

	/**
	 * instantiates a BTree node with parent p, degree deg, and comparator c. It also should create empty array lists for data and children.
	 * @param p parent
	 * @param c comparator
	 * @param deg degree
	 */
	public BTreeNode(BTreeNode<E> p, Comparator<E> c, int deg) {
		parent = p;
		cmp = c;
		degree = deg;
		data = new ArrayList<>(deg);
		children = new ArrayList<>(deg + 1);
	}

	/**
	 * returns the degree of this node
	 * @return degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * returns the data content of this node as a String enclosed in a pair of square bracket
	 * @return the data content of this node
	 */
	public String toString() {
		return data.toString();
	}

	/**
	 * returns true if this node is a leaf node
	 * @return true if this node is a leaf node
	 */
	public boolean isLeaf() {
		return children.isEmpty();
	}

	/**
	 * adds the newChild to the correct place in children array
	 */
	public void addChild(BTreeNode<E> newChild) {
		int index = 0;
		for (BTreeNode<E> node : children) {
			if (node == null || cmp.compare(newChild.getData(0), node.data.get(0)) > 0) {
				index++;
			}
		}
		children.add(index, newChild);
		newChild.parent = this;
	}

	/**
	 * populates the children list with the children in newChildren. Assume that the children array list of this BTreeNode is empty.
	 */
	public void setChildren(List<BTreeNode<E>> newChildren) {
		//not adding in order, but does not matter because list is empty, and you will only call this method when the children are in order
		children.addAll(newChildren);
		for (BTreeNode<E> child: newChildren) {
			child.parent = this;
		}
	}

	/**
	 * returns the child of this node at position pos (0-based). If there is no child at pos, return null
	 * @param pos position of child
	 * @return the child of this node at position pos (0-based). If there is no child at pos, return null
	 */
	public BTreeNode<E> getChild(int pos) {
		if (pos >= 0 && pos < children.size()) {
			return children.get(pos);
		}
		else {
			return null;
		}
	}

	/**
	 * returns the children of this node in an arraylist. If there are only two children, the arraylist should contain exactly two pointers to the two children.
	 * @return the children of this node in an arraylist
	 */
	public ArrayList<BTreeNode<E>> getChildren() {
		return children;
	}

	/**
	 * returns the # of data elements in this node
	 * @return the # of data elements in this node
	 */
	public int dataSize() {
		return data.size();
	}

	/**
	 * returns the content of this node in an arraylist. If there is only one value, the arraylist should contain exactly one data value.
	 * @return the content of this node in an arraylist
	 */
	public ArrayList<E> getData() {
		return data;
	}

	/**
	 * returns the data at position pos (0-based) in this node. If there is no data at pos, return null
	 * @param pos position of data
	 * @return the data at position pos (0-based) in this node. If there is no data at pos, return null
	 */
	public E getData(int pos) {
		if (pos >= 0 && pos < dataSize()) {
			return data.get(pos);
		}
		else {
			return null;
		}
	}

	/**
	 * populate the data list with the elements in newDataSet using either of the following ArrayList methods
	 * @param newDataSet data to be added
	 */
	public void setData(List<E> newDataSet) {
		data.addAll(newDataSet);
	}

	/**
	 * returns the position (index) at which new_data should be placed in the data array list
	 * @param new_data data to determine index
	 * @return the position (index) at which new_data should be placed in the data array list
	 */
	public int findInsertPos(E new_data) {
		int index = 0;
		//cycles to the proper index
		while (index < dataSize() && cmp.compare(new_data, data.get(index)) > 0) {
			index++;
		}
		return index;
	}

	/**
	 * returns the index in which target is found in this node's data array list or -1 if not found.
	 * @param target item being searched
	 * @return the index in which target is found in this node's data array list or -1 if not found.
	 */
	public int indexOf(E target) {
		int first = 0;
		int last = data.size() - 1;

		while (first <= last) {
			int mid = (first + last) / 2;
			if (cmp.compare(target, data.get(mid)) == 0) {
				return mid;
			}
			else if (cmp.compare(target, data.get(mid)) < 0) {
				last = mid - 1;
			}
			else {
				first = mid + 1;
			}
		}

		return -1;
	}

	/**
	 * returns true if this node has target among its data or false otherwise
	 * @param target data being searched for
	 * @return true if this node has target among its data or false otherwise
	 */
	public boolean contains(E target) {
		return indexOf(target) > -1;
	}

	/**
	 * returns true if there is an overflow in this node
	 * @return true if there is an overflow in this node
	 */
	public boolean isOverflow() {
		return (children.size() >= degree + 1) || (data.size() >= degree);
	}

	/**
	 * returns true if there is an underflow in this node
	 * @return true if there is an underflow in this node
	 */
	public boolean isUnderflow() {
		return (dataSize() < Math.ceil(degree / 2.0) - 1);
	}

	/**
	 * handles the initial and subsequent overflow of this node by splitting
	 * @return parent
	 */
	public BTreeNode<E> split() {
		BTreeNode<E> right = new BTreeNode<>(parent, cmp, degree);
		int dataSize = data.size();
		int midIndex = dataSize / 2;

		//move data right of mid to new node
		ArrayList<E> rightData = new ArrayList<E>(data.subList(midIndex + 1, dataSize));
		data.removeAll(rightData);
		right.setData(rightData);

		//if it has children, move them over
		if(!isLeaf()) {
			//gets the children that should go to right sibling
			ArrayList<BTreeNode<E>> rightChildren = new ArrayList<BTreeNode<E>>(children.subList(midIndex + 1, children.size()));

			//removes it from node, and established proper relationship
			children.removeAll(rightChildren);
			right.setChildren(rightChildren);
		}

		boolean newParent = false;
		//if it does not have a parent node, make one and connect them properly
		if (parent == null) {
			parent = new BTreeNode<>(null, cmp, degree);
			parent.addChild(this);

			newParent = true;
		}

		//make parent point to right node
		parent.addChild(right);

		//add the data to the parent and remove it from node
		E midData = data.remove(midIndex);

		int insertPos = parent.children.indexOf(this);
		parent.data.add(insertPos, midData);

		//if the parent was newly created, return it 
		if (newParent) {
			return parent;
		}
		//else if it has overflow, handle it; if not, return null
		else {
			if (parent.isOverflow()) {
				return parent.split();
			}
			else {
				return null;
			}
		}
	}

	/**
	 * adds d to this node's data collection in the right place (all data in the data arraylist must be in increasing order) and split if there is an overflow
	 * @param d data to add
	 * @return the newly created node (parent) at the top-most level if split in this node resulted in new nodes, or null if there is no overflow after adding d
	 */
	public BTreeNode<E> add(E d) {
		int index = findInsertPos(d);
		data.add(index, d);

		if(isOverflow()) {
			return split();
		}
		else {
			return null;
		}
	}

	/**
	 * checks the close siblings (immediately to the left and to the right) and returns the richer sibling (whoever has more data values) 
	 * @return returns the richer sibling (return left if tie)
	 */
	public BTreeNode<E> findDonor() {
		int index = parent.children.indexOf(this);
		BTreeNode<E> right = parent.getChild(index + 1);
		BTreeNode<E> left = parent.getChild(index - 1);
		if (index == 0) {
			return right;
		}
		else if ( (index == parent.children.size() - 1) || left.dataSize() >= right.dataSize()) {
			return left;
		}
		else {
			return right;
		}
	}

	/**
	 * returns true if this node can donate one data value without becoming deficient
	 * @return true if this node can donate one data value without becoming deficient
	 */
	public boolean canDonate() {
		return (dataSize() >= Math.ceil(degree / 2.0));
	}

	/**
	 * called when this node underflows and it has a sibling which can donate without becoming deficient itself
	 * @param sib			node donating
	 * @param myGainIndex	index gaining a value
	 * @param sibLossIndex	index losing a value
	 * @param pSepIndex		index separating the two nodes
	 */
	private void rotate(BTreeNode<E> sib, int myGainIndex, int sibLossIndex, int pSepIndex) {
		//add separating value
		data.add(myGainIndex, parent.getData(pSepIndex));
		//move donated value to parent
		parent.data.set(pSepIndex, sib.data.remove(sibLossIndex));
		if (!sib.isLeaf()) {
			int childIndex = sibLossIndex;
			if (sibLossIndex != 0) {
				childIndex++;
			}
			BTreeNode<E> child = sib.children.remove(childIndex);
			addChild(child);
		}
	}

	/**
	 * called when a node underflows and its siblings cannot donate a value without becoming deficient
	 * @param left		left node
	 * @param right		right node
	 * @param pSepIndex index separating the two nodes
	 * @return merged node if there was a merge; null otherwise
	 */
	public static <E> BTreeNode<E> join(BTreeNode<E> left, BTreeNode<E> right, int pSepIndex) {
		BTreeNode<E> parent = left.parent;
		left.add(parent.getData(pSepIndex));
		for (E key : right.data) {
			left.data.add(key);
		}
		right.data.clear();
		//can you use setChildren?
		for (BTreeNode<E> child: right.children) {
			left.addChild(child);
		}
		parent.children.remove(right);
		parent.data.remove(pSepIndex);
		//if the parent is the root and has no elements, return left so it can be set to root
		if (parent.parent == null && parent.dataSize() == 0) {
			return left;
		}
		//otherwise if it is not the root, handle the underflow
		else if (parent.parent != null && parent.isUnderflow()) {
			return parent.handleUnderflow();
		}
		else {
			return null;
		}
	}

	/**
	 * balances the tree after deletion
	 * @return joined node if merge; otherwise return null
	 */
	public BTreeNode<E> handleUnderflow() {
		BTreeNode<E> donor = findDonor();
		int thisIndex = parent.children.indexOf(this);
		if (donor.canDonate()) {
			BTreeNode<E> parent = donor.parent;
			int donorIndex = parent.children.indexOf(donor);
			//rotate(donor, myGainIndex, sibLossIndex, pSepIndex);
			//if the donor is on the left, add the new data to the beginning of the deficient node; otherwise add it to the end
			if (donorIndex < thisIndex) {
				rotate(donor, 0, donor.dataSize() - 1, donorIndex);
			}
			else {
				rotate(donor, dataSize(), 0, thisIndex);
			}
			return null;
		}
		else {
			//the only time you will merge right, is if the deficient node is the first child
			BTreeNode<E> joinedNode;
			if (thisIndex == 0) {
				joinedNode = join(this, donor, thisIndex);
			}
			else {
				joinedNode = join(donor, this, thisIndex - 1);
			}

			return joinedNode;
		}
	}

	/**
	 * called when this node contains target from BTree remove method to actually remove target from this node
	 * @param target target to be removed
	 * @return return joined node if it causes a merge, or null if no overflow
	 */
	public BTreeNode<E> remove(E target) {
		//important to return the joined node if it causes a merge so the root can be set in BTree if necessary

		if (isLeaf()) {
			data.remove(target);
			//if it is not the root, handle overflow
			if (parent != null && isUnderflow()) {
				return handleUnderflow(); //if it rotates then it returns nothing bc void method
			}
		}
		//the target is an internal node
		else {
			int index = data.indexOf(target);
			BTreeNode<E> succNode = children.get(index);
			while (!succNode.isLeaf()) {
				succNode = succNode.getChild(succNode.children.size() - 1);
			}
			//replace target data with successor data (largest data value) in target node
			data.set(index, succNode.data.remove(succNode.dataSize() - 1));

			//handle underflow 
			if (succNode.isUnderflow()) {
				return succNode.handleUnderflow();
			}
		}
		return null;
	}
}