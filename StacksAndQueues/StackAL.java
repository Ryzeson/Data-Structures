/**
 * StackAL.java
 * 
 * stack with underlying representation as an ArrayList
 */

import java.util.ArrayList;

public class StackAL<E> {
	
	private ArrayList<E> stack;

	/**
	 * constructs a new ArrayList for stack
	 */
	public StackAL() {
		stack = new ArrayList<E>();
	}
	
	/**
	 * return the size of the stack
	 * @return size of the stack
	 */
	public int size() {
		return stack.size();
	}
	
	/**
	 * return whether or not the stack is empty
	 * @return whether or not the stack is empty
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * pushes new data to the top of the stack
	 * @param d new data to add
	 * 		O(1) if resize is not necessary
	 *  	O(N) resize necessary -- if elements have to be copied individually
	 * 		O(1) resize necessary -- if using System.arraycopy(...) 
	 */
	public void push(E d) {
		stack.add(d);
	}
	
	/**
	 * removes and returns the top of this stack if not empty
	 * @return top of the stack
	 * @throws Exception 
	 */
	public E pop() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException("pop: stack is empty");
//			throw new Exception("pop: stack is empty");
//			System.err.println("pop: stack is empty");
			return null;
		}
		
		return stack.remove(stack.size() - 1);
	}
	
	/**
	 * returns the top of this stack if not empty
	 * @return top of the stack
	 * @throws Exception 
	 * 		O(1)
	 */
	public E top() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException("top: stack is empty");
//			throw new Exception("top: stack is empty");
//			System.err.println("top: stack is empty");
			return null;
		}
		
		return stack.get(stack.size() - 1);
	}

}
