package malakhov.study.wrappers;

import java.math.BigDecimal;

public class DigDecimalVsDouble {
    public static void main(String[] args) {
        BigDecimal value1 = new BigDecimal("0.1");
        BigDecimal value2 = new BigDecimal("0.2");
        BigDecimal result = value1.add(value2);

        System.out.println("BigDecimal result: " + result);  // Output: 0.3

        double dvalue1 = 0.1;
        double dvalue2 = 0.2;
        double dresult = dvalue1 + dvalue2;

        //Floating-point arithmetic in Java uses the IEEE 754 standard,
        // which can introduce rounding errors due to the binary representation of decimal numbers.
        System.out.println("Double result: " + dresult); //0.30000000000000004
    }
}
