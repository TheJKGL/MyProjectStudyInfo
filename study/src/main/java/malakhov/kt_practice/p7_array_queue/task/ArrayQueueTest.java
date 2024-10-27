package malakhov.kt_practice.p7_array_queue.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayQueueTest {

    @DisplayName("Test add and remove with different values")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testAddAndRemove(int value) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertTrue(queue.add(value));
        assertEquals(1, queue.size());
        assertEquals(value, queue.remove());
        assertEquals(0, queue.size());
    }

    @DisplayName("Add should throw NullPointerException and offer return false if passed value is null")
    @Test
    void tetsNullAddAndOffer() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertThrows(NullPointerException.class, () -> queue.add(null));
        assertFalse(queue.offer(null));
    }

    @DisplayName("Test offer and poll with different values")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testOfferAndPoll(int value) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertTrue(queue.offer(value));
        assertEquals(1, queue.size());
        assertEquals(value, queue.poll());
    }

    @DisplayName("Remove should throw NoSuchElementException and pull return null if queue is empty")
    @Test
    void tetsEmptyRemoveAndPull() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertThrows(NoSuchElementException.class, queue::remove);
        assertNull(queue.poll());
    }

    @DisplayName("Test element and peek with different values")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testElementAndPeek(int value) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertTrue(queue.offer(value));
        assertEquals(value, queue.element());
        assertEquals(value, queue.peek());
    }

    @DisplayName("Test element and peek on empty queue")
    @Test
    void testElementAndPeekOnEmpty() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertThrows(NoSuchElementException.class, () -> queue.element());
        assertNull(queue.peek());
    }

    @DisplayName("Test contains with different values")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testContains(int value) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertFalse(queue.contains(value));
        queue.add(value);
        assertTrue(queue.contains(value));
    }

    @DisplayName("Test clear with different values")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testClear(int value) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        queue.add(value);
        assertFalse(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @DisplayName("Test add and remove with circular buffer")
    @Test
    void testAddAndRemoveCircular() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        assertTrue(queue.add(1));
        assertTrue(queue.add(2));
        assertEquals(1, queue.remove());
        assertEquals(1, queue.size());
        assertTrue(queue.add(3));
        assertEquals(2, queue.size());
        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
        assertTrue(queue.isEmpty());
    }

    @DisplayName("Test offer and poll with circular buffer")
    @Test
    void testOfferAndPollCircular() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertEquals(1, queue.poll());
        assertEquals(1, queue.size());
        assertTrue(queue.offer(3));
        assertEquals(2, queue.size());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertNull(queue.poll());
    }

    @DisplayName("Test element and peek with circular buffer")
    @Test
    void testElementAndPeekCircular() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertEquals(1, queue.element());
        assertEquals(1, queue.peek());
        queue.remove();
        assertEquals(2, queue.element());
        assertEquals(2, queue.peek());
        queue.remove();
        assertThrows(NoSuchElementException.class, queue::element);
        assertNull(queue.peek());
    }

    @DisplayName("Test contains with circular buffer")
    @Test
    void testContainsCircular() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        queue.add(1);
        queue.add(2);
        queue.remove();
        queue.add(3);
        assertFalse(queue.contains(1));
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));
    }

    @DisplayName("Test clear with circular buffer")
    @Test
    void testClearCircular() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        queue.add(1);
        queue.add(2);
        queue.remove();
        queue.add(3);
        assertFalse(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @DisplayName("Test circle buffer proper work")
    @Test
    void testCircularBufferProperWork() throws NoSuchFieldException, IllegalAccessException {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.remove();
        queue.remove();
        queue.add(4);
        queue.add(5);
        assertTrue(queue.contains(3));
        assertTrue(queue.contains(4));
        assertTrue(queue.contains(5));

        Field elements = queue.getClass().getDeclaredField("elements");
        elements.setAccessible(true);
        assertEquals(4, ((Object[]) elements.get(queue)).length);
    }
}