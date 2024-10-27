package malakhov.kt_practice.p7_array_queue.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircularListTest {
    @Test
    public void circularListTest() {
        final CircularList<String> xs = new CircularList<>("one", "two", "three");
        assertEquals("one", xs.next());
        assertEquals("two", xs.next());
        assertEquals("three", xs.next());
        assertEquals("one", xs.next());
        assertEquals("one", xs.previous());
        assertEquals("three", xs.previous());
        assertEquals("two", xs.previous());
        assertEquals("one", xs.previous());

        final CircularList<Integer> ys = new CircularList<>(1, 2, 3, 4, 5);
        assertEquals(5, ys.previous());
        assertEquals(4, ys.previous());
        assertEquals(3, ys.previous());
        assertEquals(2, ys.previous());
        assertEquals(1, ys.previous());
        assertEquals(5, ys.previous());
        assertEquals(4, ys.previous());
        assertEquals(3, ys.previous());
        assertEquals(2, ys.previous());
        assertEquals(1, ys.previous());
        assertEquals(5, ys.previous());
        assertEquals(4, ys.previous());
        assertEquals(3, ys.previous());
        assertEquals(2, ys.previous());
        assertEquals(1, ys.previous());
        assertEquals(5, ys.previous());
        assertEquals(4, ys.previous());
        assertEquals(4, ys.next());
        assertEquals(5, ys.next());
        assertEquals(1, ys.next());
        assertEquals(2, ys.next());
        assertEquals(3, ys.next());
        assertEquals(3, ys.previous());
        assertEquals(2, ys.previous());
        assertEquals(2, ys.next());
        assertEquals(3, ys.next());
        assertEquals(4, ys.next());
        assertEquals(5, ys.next());
        assertEquals(1, ys.next());
        assertEquals(2, ys.next());
    }
}