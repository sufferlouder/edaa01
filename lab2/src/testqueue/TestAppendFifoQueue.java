package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}
	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}
	@Test
	public void BothQueuesEmpty() {
		q1.append(q2);
		assertTrue(q1.size() == 0);
		assertTrue(q1.poll() == null);
	}
	@Test
	public void emptyWithNotEmpty() {
		q2.offer(1);
		q2.offer(2);
		q2.offer(3);
		q1.append(q2);
		assertTrue(q1.size() == 3);
		assertTrue(q1.poll() == 1);
		assertTrue(q1.poll() == 2);
		assertTrue(q1.poll() == 3);
		assertTrue(q2.size() == 0);
		assertTrue(q2.poll() == null);
		//fail("Not yet implemented");
	}
	@Test
	public void notEmptyWithEmpty() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q1.append(q2);
		assertTrue(q1.size() == 3);
		assertTrue(q1.poll() == 1);
		assertTrue(q1.poll() == 2);
		assertTrue(q1.poll() == 3);
		assertTrue(q2.size() == 0);
		assertTrue(q2.poll() == null);
	}
	@Test
	public void BothQueuesNotEmpty() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q2.offer(1);
		q2.offer(2);
		q2.offer(3);
		q1.append(q2);
		assertTrue(q1.size() == 6);
		assertTrue(q1.poll() == 1);
		assertTrue(q1.poll() == 2);
		assertTrue(q1.poll() == 3);
		assertTrue(q1.poll() == 1);
		assertTrue(q1.poll() == 2);
		assertTrue(q1.poll() == 3);
		assertTrue(q2.size() == 0);
		assertTrue(q2.poll() == null);
	}
	@Test
	public void AppendQueueWithItself() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		try{
			q1.append(q1);
			fail("Should raise IllegalArgumentException");
		}
		catch(IllegalArgumentException e){
			
		}
	}
	

}
