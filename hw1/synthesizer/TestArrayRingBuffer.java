package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        //test is Empty
        assertTrue(arb.isEmpty());
        //arb.dequeue();
        for (int i = 0; i < 10; i ++) {
            arb.enqueue(i);
        }
        assertEquals(0,arb.peek());
        assertEquals(10,arb.fillCount());
        assertEquals(0,arb.dequeue());
        assertEquals(9,arb.fillCount());
        arb.enqueue(0);
        assertEquals(10,arb.fillCount());
        assertEquals(1,arb.peek());

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
