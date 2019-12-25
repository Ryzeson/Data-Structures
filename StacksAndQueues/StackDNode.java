/**
 * StackDNode.java
 * 
 * stack with direct mini list implementation using DNode
 */

public class StackDNode<E> {
	
	private DNode<E> tos;	//head; add to front
	private int count;

	/**
	 * constructs a new ArrayList for stack
	 */
	public StackDNode() {

	}
	
	/**
	 * return the size of the stack
	 * @return size of the stack
	 */
	public int size() {
		return count;
	}
	
	/**
	 * returns true if this stack is empty, false otherwise
	 * @return whether or not the stack is empty
	 */
	public boolean isEmpty() {
		return (tos == null); //or return (count == 0)
	}
	
	/**
	 * pushes new data to the top of the stack
	 * @param d new data to add
	 * O(1)
	 */
	public void push(E d) {
		tos = new DNode<E>(null, d, tos);
		++count;
	}
	
	/**
	 * removes and returns the top of this stack if not empty
	 * @return top of the stack
	 * @throws Exception 
	 */
	public E pop() throws Exception{
		if (isEmpty()) {
			//throw exception
			return null;
		}
		
		E temp = tos.data;
		tos = tos.next;
		--count;
		
		return temp;
	}
	
	/**
	 * returns the top of this stack if not empty
	 * @return top of the stack
	 * @throws Exception 
	 * O(1)
	 */
	public E top() throws Exception {
		if (isEmpty()) {
			//throw exception
			return null;
		}
		
		return tos.data;
	}

}
