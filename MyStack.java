/**
 * @author Sepand Jahrominejad
 * 
 * CMSC204 - Project 2
 */

import java.util.ArrayList;



public class MyStack<T> implements StackInterface<T> {
	ArrayList<T> stack;
	
	
	
	/**
	 * Creates a stack with user specified size
	 * @param size
	 */
	public MyStack(int size) {
		stack = new ArrayList<T>(size);
		for(int i=0; i<size; i++) {
			stack.add(i, null);
		}
	}
	
	
	
	/**
	 * Creates a stack with a default size
	 */
	public MyStack() {
		stack = new ArrayList<T>();
	}
	
	
	
	/**
	 * Checks to see if the stack is empty
	 * @return False if it's not empty
	 * @return True if it's empty
	 */
	public boolean isEmpty() {
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i)!=null)
				return false;
		}
		return true;
	}
	
	
	/**
	 * Checks to see if the stack is full
	 * @return False if it's not full
	 * @return True if it's full
	 */
	public boolean isFull() {
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i)==null)
				return false;
		}
		return true;
	}
	
	
	
	/**
	 * Removes the last entered element from the top of the stack
	 * @return returns the removed element
	 * @throws Throws StackUnderflowException if the stack is empty
	 */
	public T pop() throws StackUnderflowException{
		if(stack.get(0)==null)
			throw new StackUnderflowException();
		
		
		T top;
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i)==null) {
				top = stack.get(i-1);
				stack.remove(i-1);
				return top;
			}
		}
		top = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		return top;
	}
	
	
	
	/**
	 * Peeks at the latest added element to the stack
	 * @return The latest added element to the stack
	 * @throws Throws StackOverflowException if stack is empty
	 */
	public T top() throws StackUnderflowException{
		if(stack.get(0)==null)
			throw new StackUnderflowException();
		
		
		if(stack.get(0)==null)
			throw new StackUnderflowException();
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i)==null) {
				return stack.get(i-1);
			}
		}
		 return stack.get(stack.size()-1);
	}
	
	
	
	/**
	 * Gets the size of the stack
	 * @return The size of the stack
	 */
	public int size() {
		int count = 0;
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i)!= null)
				count++;
		}
		return count;
	}
	
	
	
	/**
	 * Adds anew element on top of the stack
	 * @param Any Type
	 * @return True if the operation is successful
	 * @throws Throws StackOverflowException if the stack is full
	 */
	public boolean push(T e) throws StackOverflowException{
		if(stack.get(stack.size()-1) != null)
			throw new StackOverflowException();
		
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i)==null) {
				stack.set(i, e);
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * @return A string of the stack's elements with no space
	 */
	public String toString() {
		String str="";
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i) != null)
				str += stack.get(i);
		}
		return str;
	}
	
	
	
	/**
	 * @param delimiter
	 * @return A string of the tack's elements with user defined delimiter
	 */
	public String toString(String delimiter) {
		String str="" , dummy="";
		for(int i=0; i<stack.size(); i++) {
			if(stack.get(i) != null) {
				str += dummy + stack.get(i);
				dummy = delimiter;
			}
			
		}
		return str;
	}
	
	public void fill(ArrayList<T> list) {
		for(int i=0; i<list.size(); i++) {
			stack.add(i, list.get(i));
		}
	}

}
