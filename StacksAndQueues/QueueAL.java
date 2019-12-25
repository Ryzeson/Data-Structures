/**
 * QueueAL.java
 * 
 * queue with underlying representation as an ArrayList
 */

import java.util.ArrayList;

public class QueueAL<E> {

	private ArrayList<E> q;

	/**
	 * constructs a new ArrayList for queue
	 */
	public QueueAL() {
		q = new ArrayList<E>();
	}

	/**
	 * return the size of the queue
	 * @return size of the queue
	 */
	public int size() {
		return q.size();
	}

	/**
	 * return whether or not the queue is empty
	 * @return whether or not the queue is empty
	 */
	public boolean isEmpty() {
		return q.isEmpty();
	}

	/**
	 * offers new data to the top of the stack
	 * @param d data to be removed
	 */
	public void offer(E d) {
		q.add(d);
	}

	/**
	 * returns and removes front of the queue if not empty
	 * @return front of the queue
	 * @throws Exception 
	 */
	public E poll() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException("poll: queue is empty");
//			throw new Exception("poll: queue is empty");
//			System.err.println("poll: queue is empty");
			return null;
		}

		return q.remove(0);
	}
	
	/**
	 * returns the front of the queue if not empty
	 * @return front of the queue
	 * @throws Exception
	 */
	public E front() throws Exception{
		if (isEmpty()) {
//			throw new StackUnderflowException("front: queue is empty");
//			throw new Exception("front: queue is empty");
//			System.err.println("front: queue is empty");
			return null;
		}
		
		return q.get(0);
	}

}