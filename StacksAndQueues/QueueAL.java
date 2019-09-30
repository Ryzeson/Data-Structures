import java.util.ArrayList;

public class QueueAL<E> {

	private ArrayList<E> q;

	/**
	 * constructs a new ArrayList for stack
	 */
	public QueueAL() {
		q = new ArrayList<E>();
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
		return q.isEmpty();
	}

	/**
	 * pushes new data to the top of the stack
	 * @param d data to be removed
	 */
	public void offer(E d) {
		q.add(d);
	}

	/**
	 * 
	 * @return top of the stack
	 * @throws Exception 
	 */
	public E poll() throws Exception {
		if (isEmpty()) {
			//			throw new StackUnderflowException(" pop: stack is empty");
			throw new Exception("pop: stack is empty");
			//			System.err.println("pop: stack is empty");
		}

		return q.get(0);
	}

}