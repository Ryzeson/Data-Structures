/**
 * QueueArray.java
 * 
 * queue with underlying representation as an Array
 */

public class QueueArray<E> {
	
	private int count;
	private int first, last;
	private E[] q;
	
	public static final int INIT_CAP = 8;
	
	/**
	 * constructs a new Array for queue
	 */
	public QueueArray() {
		q = (E[]) new Object[INIT_CAP];
		first = -1;		//index of first actual data
		last = -1;		//index of most recent addition
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
		return (count == 0);
	}
	
	/**
	 * offers new data to the top of the stack
	 * @param d data to be removed
	 */
	public void offers(E d) {
		//resize if necessary
		//first == (last + 1) fails if the indices do not cross (no removal)
		if (count == q.length) {
			
			// create double capacity array
			E[] tmp = (E[]) new Object[q.length * 2];
			
			// copy all from first to 0 in new array
			//System.arraycopy(src, fromIndex, dst, toIndex, length)
			System.arraycopy(q, first, tmp, 0, q.length - first);
			
			//copy all from 0 up to last to wherever prev step finished
			System.arraycopy(q, 0, tmp, q.length - first, last + 1);
			
			//update first and last
			first = 0;
			last = q.length - 1;
			
			//reset q to point to the new array
			q = tmp;
			
		}
		
		//q[++last] = d;	//could result in ArrayIndexOutOfBoundsException
		last = (last + 1) % q.length;
		q[last] = d;
		++count;
		
	}
	
	/**
	 * returns and removes front of the queue if not empty
	 * @return front of the queue
	 * @throws Exception 
	 */
	public E poll() throws Exception{
		if (isEmpty()) {
			//throw exception
			return null;
		}
		
		E toReturn = q[first];
		
		first = (first + 1) % q.length;
		--count;
		
		return toReturn;
	}
	
	/**
	 * returns front of the queue if not empty
	 * @return front of the queue
	 * @throws Exception 
	 */
	public E front() throws Exception{
		if (isEmpty()) {
			//throw exception
			return null;
		}
		
		return q[first];
	}

}
