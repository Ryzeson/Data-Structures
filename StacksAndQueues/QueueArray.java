
public class QueueArray<E> {
	
	private int count;
	private int first, last;
	private E[] q;
	
	public static final int INIT_CAP = 8;
	
	public QueueArray() {
		q = (E[]) new Object[INIT_CAP];
		first = -1;		//index of first actual data
		last = -1;		//index of most recent addition
	}
	
	public boolean isEmpty() {
		return (count == 0);
	}
	
	public int size() {
		return count;
	}
	
	public void add (E d) {
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
		
		//++last;
		//q[last] = d;
		//q[++last] = d;	//could result in ArrayIndexOutOfBoundsException
		last = (last + 1) % q.length;
		q[last] = d;
		++count;
		
	}
	
	public E poll() {
		if (isEmpty()) {
			//throw exception
			return null;
		}
		
		E toReturn = q[first];
		
		/*
		++first;
		if (first == q.length) {
			first = 0;
		}
		*/
		
		first = (first + 1) % q.length;
		--count;
		
		return toReturn;
	}
	
	public E front() {
		if (isEmpty()) {
			//throw exception
			return null;
		}
		
		return q[first];
	}

}
