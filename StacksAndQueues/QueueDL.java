/**
 * QueueDL.java
 * 
 * queue with doubly-linked list as underlying representation
 */

public class QueueDL<E> {
	
	private DList<E> q;

	/**
	 * constructs a new ArrayList for stack
	 */
	public QueueDL() {
		q = new DList<E>();
	}
	
	/**
	 * return the size of the stack
	 * @return size of the stack
	 */
	public int size() {
		return q.size();
	}
	
	/**
	 * return whether or not the stack is empty
	 * @return whether or not the stack is empty
	 */
	public boolean isEmpty() {
		return q.empty();
	}
	
	/**
	 * add new data to the top of the stack
	 * @param d data to be removed
	 * O(1) if properly implemented
	 */
	public void add(E d) {
		q.add(d);
	}
	
	/**
	 * returns and removes the front of the queue
	 * @return front of the stack
	 * @throws Exception 
	 * O(1) if properly implemented
	 */
	public E poll() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException(" pop: stack is empty");
//			throw new Exception("pop: stack is empty");
//			System.err.println("pop: stack is empty");
			return null;
		}
		
		return q.remove(0);
	}
	
	/**
	 * returns the front of this queue if not empty
	 * @return front of the stack
	 * @throws Exception 
	 * O(1) depending on implementation
	 */
	public E front() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException("top: stack is empty");
			throw new Exception("top: stack is empty");
//			System.err.println("top: stack is empty");
		}
		
		return q.get(0);
	}

}
