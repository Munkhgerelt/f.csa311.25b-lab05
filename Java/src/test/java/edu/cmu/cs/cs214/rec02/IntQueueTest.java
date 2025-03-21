package edu.cmu.cs.cs214.rec02;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        mQueue = new LinkedIntQueue();
    //    mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        IntQueue queue = new ArrayIntQueue();
        queue.enqueue(1);
        assertFalse(queue.isEmpty());  // Check if queue is not empty
    }

    @Test
    public void testPeekEmptyQueue() {
        IntQueue queue = new ArrayIntQueue();
        assertNull(queue.peek());  // Check if peek returns null when queue is empty
    }    

    @Test
    public void testPeekNoEmptyQueue() {
        IntQueue queue = new ArrayIntQueue();
        queue.enqueue(5);
        assertEquals(Integer.valueOf(5), queue.peek());  // Check if peek returns correct value
    }    

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeueEmptyQueue() {
        IntQueue queue = new ArrayIntQueue();
        assertNull(queue.dequeue()); // If dequeue() returns null when empty
    }
    
    @Test
    public void testDequeueOrder() {
        IntQueue queue = new ArrayIntQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        assertEquals(Integer.valueOf(10), queue.dequeue()); // First in, first out
        assertEquals(Integer.valueOf(20), queue.dequeue()); // Next element
    }    

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testClear() {
        IntQueue queue = new ArrayIntQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.clear();
        assertFalse(queue.isEmpty()); // Queue should be empty after clear
    }

    @Test
    public void testSize() {
        IntQueue queue = new ArrayIntQueue();
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        queue.enqueue(2);
        assertEquals(2, queue.size());
    }

    @Test
    public void testEnsureCapacity() {
        IntQueue queue = new ArrayIntQueue();
        for (int i = 0; i < 100; i++) { // Assuming default capacity is small
            queue.enqueue(i);
        }
        assertEquals(100, queue.size()); // Ensure all elements are stored
    }
    
}
