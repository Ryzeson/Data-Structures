/**
 * StackDL.java
 * 
 * stack with underlying representation as a a doubly-linked list
 */

public class StackDL<E> {
	
	private DList<E> stack;

	/**
	 * constructs a new DList for stack
	 */
	public StackDL() {
		stack = new DList<E>();
	}
	
	/**
	 * return the size of the stack
	 * @return size of the stack
	 */
	public int size() {
		return stack.size();
	}
	
	/**
	 * returns true if this stack is empty, false otherwise
	 * @return whether or not the stack is empty
	 */
	public boolean isEmpty() {
		return stack.empty();
	}
	
	/**
	 * pushes new data to the top of the stack
	 * @param d data to be removed
	 * O(1) if properly implemented
	 */
	public void push(E d) {
		stack.add(d);
	}
	
	/**
	 * returns and removes the top of the stack if not empty
	 * @return top of the stack
	 * @throws Exception 
	 * O(1) if properly implemented
	 */
	public E pop() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException(" pop: stack is empty");
//			throw new Exception("pop: stack is empty");
//			System.err.println("pop: stack is empty");
			return null;
		}
		
		return stack.remove(stack.size() - 1);
		//use this after we implement remove
//		return stack.remove(stack.size() - 1);
	}
	
	/**
	 * returns the top of this stack if not empty
	 * @return top of the stack
	 * @throws Exception 
	 * O(1) depending on implementation
	 */
	public E top() throws Exception {
		if (isEmpty()) {
//			throw new StackUnderflowException("top: stack is empty");
			throw new Exception("top: stack is empty");
//			System.err.println("top: stack is empty");
		}
		
		return stack.get(stack.size() - 1);
	}

}
