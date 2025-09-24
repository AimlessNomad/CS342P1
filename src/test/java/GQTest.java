import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

class GQTest {

    @Test
    void constructorTest() {
        GenericList.Node<Integer> n1 = new GenericList.Node<>(7);
        assertEquals(n1.data, 7);

        GenericList.Node<Integer> n2 = new GenericList.Node<>(7, 1984);
        assertEquals(7, n2.data);
        assertEquals(1984, n2.code);

        GenericQueue<Integer> queue = new GenericQueue<>(7);
        assertEquals(7, queue.getHead().data);
    }

    @Test
    void addTest() {
        GenericQueue<Integer> queue = new GenericQueue<>(7);
        queue.add(8);
        queue.add(9);
        queue.add(11);
        queue.enqueue(12);
        queue.enqueue(12);
        queue.enqueue(12);
        assertEquals(7, queue.getLength());

        int[] expected = {7, 8, 9, 11, 12, 12, 12};
        int[] actual = new int[7];
        GenericList.Node<Integer> curr = queue.getHead();
        for(int i = 0; i < queue.getLength(); i++) {
            actual[i] = curr.data;
            curr = curr.next;
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        GenericQueue<Integer> queue = new GenericQueue<>(7);
        queue.delete();
        assertNull(queue.getHead());
        assertNull(queue.getTail());
        assertNull(queue.delete());

        queue.add(8);
        queue.add(9);
        queue.delete();
        assertEquals(queue.getHead(), queue.getTail());

        queue.add(11);
        queue.enqueue(12);
        queue.enqueue(12);
        queue.enqueue(27);

        int d1 = queue.delete();
        int d2 = queue.dequeue();
        assertEquals(27, d1);
        assertEquals(12, d2);
        assertEquals(3, queue.getLength());

        int[] expected = {8, 11, 12};
        int[] actual = new int[3];
        GenericList.Node<Integer> curr = queue.getHead();
        for(int i = 0; i < queue.getLength(); i++) {
            actual[i] = curr.data;
            curr = curr.next;
        }
        assertArrayEquals(expected, actual);

        queue.delete();
        queue.delete();
        queue.delete();
        queue.delete();
        assertNull(queue.getHead());
    }

    @Test
    void dumpListTest() {
        GenericQueue<Integer> queue = new GenericQueue<>(7);
        queue.delete();
        ArrayList<Integer> actual = queue.dumpList();
        assertTrue(actual.isEmpty());

        queue.add(8);
        queue.add(9);
        queue.add(11);
        queue.enqueue(12);
        queue.enqueue(12);
        queue.enqueue(27);

        actual = queue.dumpList();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(new Integer[]{8, 9, 11, 12, 12, 27}));
        assertEquals(expected, actual);
    }
}
