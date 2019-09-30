/**
 * QueueDNode.java
 * 
 * queue with simplified, non-headed, iterable, doubly-linked list as underlying representation
 */

import java.util.Iterator;

public class QueueDNode<E> implements Iterable<E>{
	
	private DNode<E> first, last;
	private int count;

	class QIterator implements Iterator<E> {

		private DNode<E> current;

		public QIterator() {
			current = first;
		}

		public boolean hasNext() {
			return (current != null);
		}

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

	public Iterator<E> iterator() {
		return new QIterator();
	}

	@Override
	public String toString() {
		String str = "[ ";
		DNode<E> ptr = first;
		while (ptr != null) {
			str += ptr.data + " ";
			ptr = ptr.next;
		}
		return str += "]";
	}


	/**
	 * constructs a new queue
	 */
	public QueueDNode() {

	}

	/**
	 * return the size of the queue
	 * @return size of the queue
	 */
	public int size() {
		return count;
	}

	/**
	 * return whether or not the queue is empty
	 * @return whether or not the queue is empty
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * add new data to the end of the queue
	 * @param d data to be added
	 * O(1) if properly implemented
	 */
	public void add(E d) {
		//count == 0 is faster, but isEmpty makes it very clear what we are trying to do
		//DNode(prev, data, next)
		if (isEmpty()) {
			first = last = new DNode<E>(null, d, null);
		}
		else {
			DNode<E> nn = new DNode<E>(last, d, null);
			last.next = nn;
			last = nn;
		}

		++count;
	}

	/**
	 * returns and removes the front of the queue if not empty
	 * @return front of the queue
	 * @throws Exception 
	 * O(1) if properly implemented
	 */
	public E poll() throws Exception{
		if (isEmpty()) {
			//throw exception
			return null;
		}
		E tmp = first.data;
		first = first.next;
		first.prev = null;
		--count;

		return tmp;
	}

	/**
	 * returns the front of this queue if not empty
	 * @return front of the queue
	 * @throws Exception 
	 * O(1) depending on implementation
	 */
	public E front() throws Exception{
		if (isEmpty()) {
			//throw exception
			return null;
		}

		return first.data;
	}

	public static void main(String[] args) {
		QueueDNode<Character> queue = new QueueDNode<>();
		System.out.println(queue);
		queue.add('C');
		queue.add('E');
		System.out.println(queue);
//		System.out.println("should remove E: " + queue.poll());

		//testing iterator
		
		// 1 Implicit Iterator
		System.out.println("Print  all contents using for each loop");
		for (Character d : queue) {
			System.out.print(d + "");
		}
		System.out.println();
		
//		O(N^2)
//		System.out.println("Print all contents using for loop and get(int)");
//		for (int i = 0; i < queue.size(); ++i) {
//			System.out.println(queue.poll(i) + " ");
//		}
		
		//2 Explicit Iterator
		Iterator<Character> qIter = queue.iterator();
		System.out.println("Print all contents using iterator explicitly");
		while (qIter.hasNext()) {
			//qIter.next();
			System.out.print(qIter.next() + " ");
		}
	}
	
}
