package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(isEmpty()) { // Om den här kön är tom
			last = q.last;
			size = q.size;
		}
		else if(last == q.last && last.next == q.last.next && size == q.size) {
			throw new IllegalArgumentException();
		}
		else if(!q.isEmpty()){ // Om q inte är tom
			QueueNode<E> n = last.next; // spara undan första noden
			last.next = q.last.next; //Första noden i q länkas till sista noden i queue
			q.last.next = n; //Sista noden i q länkas till första noden i queue
			last = q.last; // Sista noden är sista noden i q
			size = size + q.size; //antalet element summeras
		}
		
		q.last = null; // q raderas
		q.size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		if(isEmpty()) {
			last = n;
			last.next = last;
		}
		else {
			//QueueNode<E> temp;
			//temp = last.next;
			//last = n;
			//n.next = temp;
			//last.next = temp; 
			n.next = last.next;
			last.next = n;
			last = n;
		}
		size++;
		return true;
		
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(isEmpty()) {
			return null;
		}
		else {
			return last.next.element;
		}
		
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(isEmpty()) {
			return null;
		}
		
		QueueNode<E> n = last.next;
		last.next = n.next;
		size--;
		return n.element;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private int counter;
		
		private QueueIterator() {
			this.pos = last;
			counter = 0;
		}

		@Override
		public boolean hasNext() {
			if(this.pos == null) {
				return false;
			}
			if(counter >= size()) {
				return false;
			}
			
			return true;//this.pos.next != last;
			
		}

		@Override
		public E next() {
			E e;
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				this.pos = this.pos.next;
				e = this.pos.element;
				counter++;
				return e;
			}	
		}
		
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
		
	}

}
