/**
 * hw1
 * DList.java
 * Wed Sept 11-2019
 * 
 * @author Ryzeson Maravich
 * 
 * represents a headed, doubly-linked list data structure
 */

import java.util.Comparator;
import java.util.Iterator;

public class DList<E> implements Iterable<E> {

	private DNode head;
	private DNode tail;
	private int count;
	private Comparator<E> cmp;

	class DNode{
		public DNode() { }

		public DNode(DNode p, E d, DNode n) {
			prev = p;
			data = d;
			next = n;
		}

		@Override
		public String toString() {
			return data.toString();
		}

		DNode prev;
		E data;
		DNode next;		//null if it's the last item in the list

	}

	//check to see if iterator is correct on Monday
	class DListIterator implements Iterator<E> {

		//need to know which node we are on right now
		private DNode current;

		//constructor
		DListIterator() {
			current = head.next;
		}

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			if (!hasNext()) {
				//throw exception
				return null;
			}

			E tmp = current.data;
			current = current.next;
			return tmp;
		}
	}

	/**
	 * 	Return a new Iterator object for this list
	 * @ return a new Iterator object for this list
	 */
	public Iterator<E> iterator() {
		return new DListIterator();
	}

	/**
	 * Set up an empty list by initializing member variables appropriately.
	 */
	public DList() {
		//use most specific constructor, and set extra parameters to null
		//otherwise you are doing extra work
		this(null);
	}

	/**
	 * Set up an empty list by initializing member variables appropriately.
	 * This list will main sorted order (in increasing order according to the passed Comparator).
	 * @param comp comparator
	 */
	public DList(Comparator<E> comp) {
		head = new DNode();
		tail = head;
		cmp = comp;
	}

	/**
	 * Return a print-friendly String representation of this list from front to back.
	 * For an empty list return "[ ]" (one space between the opening and closing square brackets);
	 * and for a non-empty list return "[ d1 d2 d3 ]"".
	 * @return string representation
	 */
	public String toString() {
		String str = "[ ";

		DNode ptr = head.next;
		while (ptr != null) {
			str += ptr + " ";
			ptr = ptr.next;
		}

		return str + "]";
	}

	/**
	 * Return a print-friendly String representation of this list from back to front (reverse order).
	 * For an empty list, return "[ ]" and for a non-empty list return ""[ d3 d2 d1 ]"".
	 * @return string representation from back to front
	 */
	public String toStringBwd() {
		String str = "[ ";

		DNode ptr = tail;
		while (ptr != head) {
			str += ptr + " ";
			ptr = ptr.prev;
		}

		return str + "]";
	}

	/**
	 * Add item at the end of the list if not sorted OR in the correct location if sorted.
	 * @param item the item to add
	 */
	public void add(E item) {
		DNode nn = new DNode(null, item, null);

		//make tail point to the new node with item
		if (empty() || !isOrdered()) {
			//never want to update the head node
			tail.next = nn;
			nn.prev = tail;
			tail = nn;
		}
		else {
			DNode trail = head;
			DNode ptr = head.next;
			while (ptr != null && cmp.compare(item, ptr.data) > 0) {
				trail = ptr;
				ptr = ptr.next;
			}

			trail.next = nn;
			nn.next = ptr;
			nn.prev = trail;

			if(ptr != null) {
				//inserting somewhere in the middle
				ptr.prev = nn;
			}
			else {
				tail = nn;
			}
		}

		++count;
	}

	/**
	 * If the list is not sorted, insert the item at index position.
	 * This method should behave the same way as the add(int index, E item) method in the ArrayList class. 
	 * @param index the position to add
	 * @param item the item to add
	 */
	public void add(int index, E item) {
		if (!isOrdered()) {
			//index error check
			//should just be count
			if (index < 0 || index > count) {
				//			throw new IndexOutOfBoundsException("add: valid range is [" + 0 + ", " + (count-1) + "] "); //can be a little bit more descriptive
				return;
			}

			//move a pointer to index
			DNode ptr = head.next;
			DNode trail = head;
			for(int i = 0; i < index; ++i) {
				trail = ptr;
				ptr = ptr.next;
			}

			DNode nn = new DNode(trail, item, ptr);

			if(ptr != null) {
				//inserting somewhere in the middle
				ptr.prev = nn;
			}
			else {
				//inserting at end
				tail = nn;
			}

			//update the trail
			trail.next = nn;
			++count;
		}
	}

	/**
	 * Remove all data (and associated nodes) from this list by setting the head/tail to null.
	 */
	public void clear() {
		head.next = null;
		tail = head;
		count = 0;
	}

	/**
	 * If index is valid, return the data at index. Otherwise, return null. 0-based indexing.
	 * @param index the index to add
	 * @return data at index
	 */
	public E get(int index) {
		//index error check
		if (index < 0 || index >= count) {
			//			throw new IndexOutOfBoundsException("add: valid range is [" + 0 + ", " + (count-1) + "] "); //can be a little bit more descriptive
			return null;
		}

		DNode ptr = head.next;
		for(int i = 0; i < index; ++i) {
			ptr = ptr.next;
		}

		return ptr.data;
	}

	/**
	 * If the list is not sorted and if index is valid, replace the data at index and return the old data that is being replaced.
	 * If the list is sorted and if index is valid, replace the data at index and return the old data only if item is 
	 * greater than or equal to the data @ prev DNode (if existing) AND smaller than or equal to the data @ next DNode (if existing).
	 * Otherwise, return null. 0-based indexing.
	 * @param index the position to add
	 * @param item the item to add
	 * @return item that is being replaced
	 */
	public E set(int index, E item) {
		//index error check
		if (index < 0 || index >= count) {
			//			throw new IndexOutOfBoundsException("add: valid range is [" + 0 + ", " + (count-1) + "] "); //can be a little bit more descriptive
			return null;
		}

		DNode ptr = head.next;
		E replaced;
		for(int i = 0; i < index; ++i) {
			ptr = ptr.next;
		}

		if(isOrdered()) {
			//as long as the data belongs between the nodes it is being set between, and the nodes exist
			if ((ptr.prev.data == null || cmp.compare(item, ptr.prev.data) >= 0) && (ptr.next == null || cmp.compare(item, ptr.next.data) <= 0)) {
				replaced = ptr.data;
				ptr.data = item;
				return replaced;
			}
			else {
				return null;
			}
		}
		else {
			replaced = ptr.data;
			ptr.data = item;
		}
		return replaced;
	}

	/**
	 * Return true if this list contains item - use equals method for equality check.
	 * @param item the item being checked
	 * @return whether or not the list contains the item
	 */
	public boolean contains(E item) {
		DNode ptr = head.next;
		for( ; ptr != null && !item.equals(ptr.data); ptr = ptr.next) ;

		return (ptr != null);
	}

	/**
	 * Return the index of the first occurrence of item.
	 * Return -1 if this list does not contain item. Do NOT use contains method
	 * @param item the item being checked
	 * @return the index of the first occurrence of item
	 */
	public int indexOf(E item) {

		int index = 0;
		DNode ptr = head.next;
		while (ptr != null && !item.equals(ptr.data)) {
			ptr = ptr.next;
			++index;
		}

		//maybe not the best implementation (?)
		//if the while loop was exited, and the data does not match at this point, it is not in the list
		if (ptr == null) {
			return -1;
		}
		else {
			return index;
		}
	}

	/**
	 * Return the index of the last occurrence of item.
	 * Return -1 if this list does not contain item. Do NOT use contains method
	 * @param item the item being checked
	 * 
	 * @return the index of the last occurrence of item
	 */
	public int lastIndexOf(E item) {
		int index = count - 1;
		DNode ptr = tail;
		while (ptr != head && !item.equals(ptr.data)) {
			ptr = ptr.prev;
			--index;
		}

		//maybe not the best implementation (?)
		//if the while loop was exited, and the data does not match at this point, it is not in the list
		if (ptr == head) {
			return -1;
		}
		else {
			return index;
		}
	}


	/**
	 * Return the number of data elements in this list.
	 * @return the number of data elements in this list
	 */
	public int size() {
		return count;
	}

	/**
	 * Return true if this list is sorted (non-null comparator), false otherwise.
	 * @return whether or not the list is sorted
	 */
	public boolean isOrdered() {
		return (cmp != null);
	}

	/**
	 * Return true if this list is empty, false otherwise.
	 * @return whether or not the list is empty
	 */
	public boolean empty() {
		return (count == 0);
	}

	//HW 2 METHODS

	/**
	 * In O(N) time, if index is valid remove the element at index position in this list and return the removed data
	 * @param index the position to remove
	 * @return removed data
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public E remove(int index) {
		//index error check
		if (index < 0 || index >= count) {
			//			throw new IndexOutOfBoundsException("add: valid range is [" + 0 + ", " + (count-1) + "] "); //can be a little bit more descriptive
			return null;
		}

		DNode ptr = head.next;
		DNode trail = head;;
		for(int i = 0; i < index; ++i) {
			trail = ptr;
			ptr = ptr.next;
		}

		//lead is ptr after the ptr
		DNode lead = ptr.next;
		trail.next = lead;
		if (lead != null) {
			//update node's pointer after the removed
			lead.prev = trail;

		}
		//if lead is null, then the trail is the last node
		else {
			tail = trail;
		}

		--count;
		return ptr.data;	
	}

	/**
	 * In O(N) time, remove the first occurrence of target in this list
	 * @param target the item to be removed
	 * @return whether or not the target has been found
	 */
	public boolean remove(E target) {
		DNode trail = head;
		DNode ptr = head.next;
		DNode lead = null;
		if (ptr != null) {
			lead = ptr.next;
		}
		for(int i = 0; i < size(); ++i) {
			if (ptr.data.equals(target)) {
				trail.next = lead;
				if (lead != null) {
					lead.prev = trail;
				}
				else {
					tail = trail;
				}
				--count;
				return true;
			}
			trail = ptr;
			ptr = lead;
			if (ptr != null) {
				lead = ptr.next;
			}
		}

		return false;
	}

	/**
	 * In O(N) time, remove from this list all of the elements whose index is between fromIndex (inclusive) and toIndex (exclusive). 
	 * If fromIndex is too small (< 0) remove from the beginning of the list and if toIndex is too large (> size)remove until the end of the list.
	 * @param fromIndex starting index (inclusive)
	 * @param toIndex ending index (exclusive
	 * @throws IndexOutOfBoundsException if the list is empty (regardless of indices)
	 * @throws IllegalArgumentException if the indices are out of order (i.e., fromIndex > toIndex)
	 */
	public void removeRange(int fromIndex, int toIndex) {
		if (empty()) {
			throw new IndexOutOfBoundsException();
		}
		//if the indices are out of order, throw exception
		if(fromIndex > toIndex) {
			throw new IllegalArgumentException();
		}
		//if index is smaller than 0, just start at 0
		if (fromIndex < 0) {
			fromIndex = 0;
		}
		//if index is larger than size, just remove until the last element
		if (toIndex > size()) {
			toIndex = size();
		}

		DNode ptr = head.next;
		DNode trail = head;
		for(int i = 0; i < fromIndex; ++i) {
			trail = ptr;
			ptr = ptr.next;
		}
		//could i take trail out of for loop and just make trail = ptr.prev; (?)

		for (int i = fromIndex; i < toIndex - 1; ++i) {
			if(ptr.next != null) {
				ptr = ptr.next;
			}
		}

		//lead is ptr after the ptr
		DNode lead = ptr.next;
		trail.next = lead;
		if (lead != null) {
			//update node's pointer after the removed
			lead.prev = trail;
		}
		//if lead is null, then the trail is the last node
		else {
			tail = trail;
		}
		
		count = size() - (toIndex - fromIndex);
	}

	/**
	 * Insert all elements in the given collection starting at the given index in O(M + N) time
	 * where M is the size of current list and N is the size of collection ONLY IF the list is not supposed to be ordered.
	 * Use an iterator to traverse the given collection. 
	 * Similiar to add(int, E), allow user to add to the end of the list 
	 * @param index			position to add elements
	 * @param collection	elements to be inserted
	 * @throws NullPointerException if collection is null
	 * @throws IndexOutOfBoundsException if the specified index is invalid
	 */
	public void addAll(int index, Iterable<E> collection) {
		//might just be index > count bc you can insert at last index
		if (index < 0 || index > count) {
			//			throw new IndexOutOfBoundsException("add: valid range is [" + 0 + ", " + (count-1) + "] "); //can be a little bit more descriptive
		}

		if (collection == null) {
			//			throw new NullPointerException();
		}

		if (!isOrdered()) {
			//iterate to index
			DNode ptr = head.next;
			DNode trail = head;
			for(int i = 0; i < index; ++i) {
				trail = ptr;
				ptr = ptr.next;
			}

			DNode lastAdded = null;
			//		add the elements (between trail and ptr)
			for (E item: collection) {
				DNode nn = new DNode(trail, item, ptr);
				trail.next = nn;
				trail = nn;

				//connecting last node added to the ptr
				lastAdded = nn;
				++count;
			}

			//update tail
			if (ptr != null) {
				ptr.prev = lastAdded;
			}
			else {
				tail = lastAdded;
			}
			
		}

	}
}