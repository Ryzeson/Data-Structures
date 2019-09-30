/**
 * QueueDL.java
 * 
 * queue with doubly-linked list as underlying representation
 */

public class QueueDL<E> {
	
	private DList<E> q;

	/**
	 * constructs a new ArrayList for queue
	 */
	public QueueDL() {
		q = new DList<E>();
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
		return q.empty();
	}
	
	/**
	 * add new data to the end of the queue
	 * @param d data to be added
	 * O(1) if properly implemented
	 */
	public void add(E d) {
		q.add(d);
	}
	
	/**
	 * returns and removes the front of the queue
	 * @return front of the queue
	 * @throws Exception 
	 * O(1) if properly implemented
	 */
	public E poll() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException(" poll: queue is empty");
			return null;
		}
		
		return q.remove(0);
	}
	
	/**
	 * returns the front of this queue if not empty
	 * @return front of the queue
	 * @throws Exception 
	 * O(1) depending on implementation
	 */
	public E front() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException("front: queue is empty");
			return null;
		}
		
		return q.get(0);
	}

}
