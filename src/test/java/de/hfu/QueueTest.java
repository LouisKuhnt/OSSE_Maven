package de.hfu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class QueueTest
{
	private Queue q;
	private int parameter,parameter1,parameter2,parameter3;
	
	@Before
	public void erzeugeKlasse() {
		q = new Queue(3);
		this.parameter=5; 
		this.parameter1=10;
		this.parameter2=45; 
		this.parameter3=1;
	}
	
	@Test
	public void testGetFirstInQueue() {
		
		q.enqueue(parameter);
		assertEquals(parameter, q.dequeue());
		
		q.enqueue(parameter);
		q.enqueue(parameter1);
		q.enqueue(parameter2);
		q.enqueue(parameter3);
		assertEquals(parameter, q.dequeue());
		assertEquals(parameter1, q.dequeue());
		assertEquals(parameter3, q.dequeue());
		try {
			q.dequeue();
			fail("Sollte eine Exeption werfen!");
		}catch(IllegalStateException e) {
		}
	}
	
	@Test
	public void testHeadAndTailPosition() {
		
		q.enqueue(parameter);
		assertEquals(0, q.tail);
		assertEquals(0, q.tail);
		
		q.enqueue(parameter1);
		q.enqueue(parameter2);
		assertEquals(2, q.tail);
		assertEquals(0, q.head);
		
		q.enqueue(parameter3);
		assertEquals(2, q.tail);
		assertEquals(0, q.head);
		
		q.dequeue();
		assertEquals(2, q.tail);
		assertEquals(1, q.head);
		
		q.dequeue();
		assertEquals(2, q.tail);
		assertEquals(2, q.head);
		
		q.enqueue(parameter);
		assertEquals(3, q.tail);
		assertEquals(2, q.head);
	}
}