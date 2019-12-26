/**
 * MinHeap.java
 * Ryzeson Maravich
 *
 * MinHeap
 */

import java.util.ArrayList;

public class MinHeap {

	private ArrayList<Integer> heap;

	public MinHeap() {
		heap = new ArrayList<>();
	}

	/**
	 * returns the parent index of given child
	 * @param ci	child index
	 * @return the parent index of given child
	 */
	private int parentIndex(int ci) {
		return ( (ci - 1) / 2 );
	}

	private int leftIndex(int pi) {
		return (pi * 2 + 1);
	}

	private int rightIndex(int pi) {
		return (pi * 2 + 2);
	}

	private Integer getFirst() {
		return isEmpty() ? null : heap.get(0);
	}

	private Integer getLast() {
		return isEmpty() ? null : heap.get(size() -1);
	}

	private boolean isLeaf(int index) {
		int li = leftIndex(index);

		if (li >= 0 && li < size()) {
			return false;
		}
		else {
			return true;
		}
	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	//level-order
	public String toString() {
		return heap.toString();
	}

	private void upheap(int ci) {
		while (ci > 0) {
			int pi = parentIndex(ci);

			// swap if necessary
			if (heap.get(ci) < heap.get(pi)) {
				// swap
				swap(ci, pi);

				// update ci to pi and continue
				ci = pi;
			}
			else {
				return;
			}
		}
	}

	private void add(Integer d) {
		//add to end
		heap.add(d);

		//heapify "upward" --> upheap

		//stop when root is reached OR no swap occurred
		int ci = heap.size() - 1;
		upheap(ci);

		/*
		//prettier version
		int ci = heap.size() - 1;
		int pi = parentIndex(ci);
		while (ci > 0 && heap.get(ci) > heap.get(pi)) {
			swap (ci, pi);

			ci = pi;
			pi = parentIndex(pi);
		}
		 */
	}

	private void swap(int i, int j) {
		Integer tmp = heap.get(i);
		heap.set(i,  heap.get(j));
		heap.set(j,  tmp);
	}

	private Integer removeTop() {
		if (isEmpty()) {
			return null;
		}

		Integer ret = heap.get(0);

		if (size() == 1) {
			heap.clear();		// make sure heap becomes empty
			return ret;
		}

		//move last element to root
		else {
			heap.set(0, heap.remove(size() - 1));

			//downheap
			int pi = 0;
			int li = leftIndex(pi);
			int ri = rightIndex(pi);

			//as long as pi (current node) is not a leaf node
			while (!isLeaf(pi)) {

				int smallerValue = heap.get(li);
				int smallerI = li;

				//update smallerValue to right-value
				//if right exists and is smaller
				if (ri < size() && heap.get(ri) < smallerValue) {
					smallerValue = heap.get(ri);
					smallerI = ri;
				}

				//swap if smaller child is < parent
				//when we swap, make sure to update pi, li, and ri
				if (smallerValue < heap.get(pi)) {
					swap(smallerI, pi);
					pi = smallerI;
					li = leftIndex(pi);
					ri = rightIndex(pi);
				}
				//infinite loop if no swap occurred
				else {
					return ret;
					//or add boolean to while condition, set it false here
					//break;
					//return makes the most sense here though
				}

			}
		}

		return ret;
	}

	public void decreaseKey(int index, Integer new_val) {
		if (index < 0 || index > size()) {
			return;
		}
		heap.set(index, new_val);
		int ci = index;
		while (ci != 0 && heap.get(parentIndex(ci)) > heap.get(ci)) 
		{ 
			upheap(ci);
		} 
	} 

	public static void main(String[] args) {
		MinHeap h1 = new MinHeap();

		int[] data = {11, 2, 6, 1, 7, 3, 5, -5, -7, -9, -10};

		for (int d : data) {
			h1.add(d);
			System.out.println("after adding " + d + ": " + h1);
		}

		h1.decreaseKey(7, -3);
		System.out.println("after decreasing 11 to -3 " + h1.toString());
		h1.decreaseKey(0, -20);
		System.out.println("after decreasing 11 to -3 " + h1.toString());

		while (!h1.isEmpty()) {
			System.out.println("after removing: " + h1.removeTop() + " " + h1.size() + " left");
			System.out.println(h1);
		}
	}

}
