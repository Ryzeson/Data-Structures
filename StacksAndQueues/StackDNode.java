/**
 * StackDNode.java
 * 
 * stack with direct mini list implementation using DNode
 */

public class StackDNode<E> {
	
	//member variables
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
		return (tos == null);
		//or
		//return count == 0;
	}
	
	/**
	 * pushes new data to the top of the stack
	 * @param d new data to add
	 * O(1)
	 */
	public void push(E d) {
		tos = new DNode<E>(null, d, tos);	//prev is whatever current top of stack is pointing to
		++count;
	}
	
	/**
	 * removes and returns the top of this stack if not empty
	 * @return top of the stack
	 * @throws Exception 
	 */
	public E pop() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException(" pop: stack is empty");
			throw new Exception("pop: stack is empty");
//			System.err.println("pop: stack is empty");
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
//			throw new StackUnderflowException("top: stack is empty");
//			throw new Exception("top: stack is empty");
			System.err.println("top: stack is empty");
		}
		
		return tos.data;
	}

}
