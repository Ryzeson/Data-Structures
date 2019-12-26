/**
 * Ryzeson Maravich
 * DisjointSet.java
 * Represents a disjoint set data structure
 */

import java.util.HashMap;

public class DisjointSet<E> {

	HashMap<E, DSNode> set;

	/**
	 * Disjoint Set Node
	 */
	public class DSNode {
		E data;
		E rep;
		int rank;

		public DSNode(E d) {
			data = rep = d;
			rank = 0;
		}

		/**
		 * to string method
		 * @return string representation of DSNode
		 */
		public String toString() {
			return "(" + data + ", " + rep + ", " + rank + ")";
		}
	}

	/**
	 * initializes a new DisjointSet
	 * @param items items
	 */
	public DisjointSet(Iterable<E> items) {
		set = new HashMap<>();
		for (E item : items) {
			DSNode nn = new DSNode(item);
			set.put(item, nn);
		}
	}

	/**
	 * finds the DSNode object associated with item in the hash map and path compresses
	 * @param item	item
	 * @return the representative of the set item belongs to
	 */
	public E findRep(E item) {
		if (set.get(item) == null) {
			return null;
		}
		else {
			DSNode node = set.get(item);
			if (node.rep != item) {
				node.rep = findRep(node.rep);
			}
			return node.rep;
		}
	}

	/**
	 * finds the DSNode objects associated with d1 and d2 (sets d1 and d2 belong to respectively) and perform a union operation on them if necessary
	 * @param d1	item one
	 * @param d2	item two
	 */
	public void union(E d1, E d2) {
		E rep1 = findRep(d1);
		E rep2 = findRep(d2);
		if (rep1 != rep2) {
			DSNode node1 = set.get(rep1);
			DSNode node2 = set.get(rep2);
			if (node1.rank > node2.rank) {
				node2.rep = node1.rep;
			}
			else if (node1.rank < node2.rank) {
				node1.rep = node2.rep;
			}
			else {
				node2.rep = node1.rep;
				node1.rank++;
			}
		}
	}
}
