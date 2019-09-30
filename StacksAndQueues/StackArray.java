/**
 * stack with underlying representation as an Array
 */

public class StackArray<E> {
	
	private E[] stack;
	private int count; //size of stack (things in the array)
	private int tos; //index for top of stack (most recent push)
	
	public static final int INTI_SIZE = 8;

	/**
	 * constructs a new ArrayList for stack
	 */
	public StackArray() {
		stack = (E[]) (new Object[INTI_SIZE]);
		tos = -1;
	}
	
	/**
	 * return the size of the stack
	 * @return size of the stack
	 */
	public int size() {
		return count;
	}
	
	/**
	 * return whether or not the stack is empty
	 * @return whether or not the stack is empty
	 */
	public boolean isEmpty() {
		return (count == 0);
	}
	
	/**
	 * pushes new data to the top of the stack
	 * @param d data to be removed
	 * O(1) is resize is not necessary
	 * 0(N) otherwise -- if elements have to be copied individually
	 * 0(1) resize necessary -- if using System.arraycopy(...)
	 */
	public void push(E d) {
		if (count == stack.length) {
			E[] tmp = (E[]) new Object[stack.length * 2];
			
			//copy over
			// (1) O(N)
			for (int i = 0; i < count; ++i) {
				tmp[i] = stack[i];
			}
			
			// (2) 
			// O(1) using System.arraycopy(src, fromIndex, dst, toIndex, length)
			System.arraycopy(stack, 0, tmp, 0, stack.length);
			
			//stack pointer is updated to this new array
			stack = tmp;
		}
		
		//at this point, top-of-stack (tos or top) is still valid
		++tos;					//stack[++tos] = d;
		stack[tos] = d;
		++count;
	}
	
	/**
	 * returns the top of this stack if not empty
	 * @return top of the stack
	 * O(1) since no shifting to the left/right is necessary
	 */
	public E pop() {
		if (isEmpty()) {
//			throw new StackUnderflowException("pop: stack is empty");
//			throw new Exception("pop: stack is empty");
//			System.err.println("pop: stack is empty");
		}
		
		E popped = stack[tos];
		--tos;
		--count;
		
		return popped;
	}
	/**
	 * returns the top of this stack if not empty
	 * @return top of stack
	 *O(1)
	 */
	public E top() {
		if (isEmpty()) {
//			throw new StackUnderflowException("top: stack is empty");
			System.err.println("top: stack is empty");
		}
		
		return stack[tos];
	}

}
