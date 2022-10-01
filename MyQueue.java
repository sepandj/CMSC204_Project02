
/**
 * @author Sepand Jahrominejad
 * 
 * CMSC204 - Project 2
 */

import java.util.ArrayList;



public class MyQueue<T> implements QueueInterface<T> {
	
	ArrayList<T> queue;
	
	/**
	 * Creates a queue with the size user enters
	 * @param size The size of queue
	 */
	public MyQueue(int size) {
		queue = new ArrayList<T>(size);
		for(int i=0; i<size; i++) {
			queue.add(i, null);
		}
	}
	
	
	/**
	 * Creates a queue using default size
	 */
	public MyQueue() {
		queue = new ArrayList<T>();
	}
	
	
	/**
	 * Checks to see if the queue is empty
	 * @return False if it's not empty
	 * @return True if it's empty
	 */
	public boolean isEmpty() {
		for(int i=0; i<queue.size(); i++) {
			if(queue.get(i)!=null)
				return false;
		}
		return true;
	}
	
	
	/**
	 * Checks to see if the queue is full
	 * @return False if it's not full
	 * @return True if it's full
	 */
	public boolean isFull() {
		
		for(int i=0; i<queue.size(); i++) {
			if(queue.get(i) == null)
				return false;
		}
		return true;
	}
	
	
	
	/**
	 * Removes the element of the queue that was added before others.
	 * @return The removed element.
	 * @exception Throws QueueUnderflowException if the queue is empty
	 */
	public T dequeue() throws QueueUnderflowException{
		T front;
		if(queue.get(0) == null)
			throw new QueueUnderflowException();
		for(int i=0; i<queue.size(); i++)
		{
			if(queue.get(i)==null) {
				front = queue.get(i-1);
				queue.remove(i-1);
				return front;
			}
		}
		front = queue.get(queue.size()-1);
		queue.remove(queue.size()-1);
		return front;
	}
	
	
	
	/**
	 * Indicates the size of the queue.
	 * @return The size of the queue.
	 */
	public int size() {
		for(int i=0; i<queue.size(); i++) {
			if(queue.get(i)==null)
				return i;
		}
		return queue.size();
	}
	
	
	
	
	/**
	 * Adds a new elements to the end of the queue
	 * @return True if the operation is successful
	 * @exception Throws QueueOverflowException if the queue if full
	 */
	public boolean enqueue(T e) throws QueueOverflowException{
		if(queue.get(queue.size()-1)!=null)
			throw new QueueOverflowException();
		
		for(int i=0; i<queue.size(); i++) {
			if(queue.get(0)==null) {
				queue.set(0, e);
				return true;
			}
			else if(queue.get(i)==null) {
				while(queue.get(0)!=null) {
					queue.set(i, queue.get(i-1));
					queue.set(i-1, null);
					i--;
				}
			}
		}
		queue.set(0, e);
		return true;
	}
	
	
	
	/**
	 * @return All the elements of the queue without a delimiter
	 */
	public String toString() {
		String str = "";
		for(int i=queue.size()-1; i>-1; i--) {
			if(queue.get(i) != null)
				str += queue.get(i);
		}
		return str;
	}
	
	
	
	/**
	 * @return returns all the elements of the queue with a user specified delimiter
	 */
	public String toString(String delimiter) {
		String str = "", dummy="";
		for(int i=queue.size()-1; i>=0; i--) {
			if(queue.get(i)!=null) {
				str += dummy + queue.get(i);
				dummy = delimiter;
			}
		}
		return str;
	}
	
	
	
	/**
	 * Copies all the elements of an ArrayList into a queue.
	 * @param ArrayList
	 */
	public void fill(ArrayList<T> list) {
		for(int i=0; i<list.size(); i++) {
			queue.set(i, list.get(list.size()-1-i));
		}
	}
}
