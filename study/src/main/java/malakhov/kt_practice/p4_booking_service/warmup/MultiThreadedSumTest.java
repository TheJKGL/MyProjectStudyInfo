package malakhov.kt_practice.p4_booking_service.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiThreadedSumTest {

    @Test
    public void testCalculateSum() {
        MultiThreadedSum sumCalculator = new MultiThreadedSum();
        int[] array = new int[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        int result = sumCalculator.calculateSum(array);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start));
        //assertEquals(887459712, result, "The sum should be 55.");
    }
}
