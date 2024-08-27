package malakhov.kt_practice.p1_stream_collectors_practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import malakhov.kt_practice.p1_stream_collectors_practice.entity.Payment;
import malakhov.kt_practice.p1_stream_collectors_practice.entity.Employee;

import static malakhov.kt_practice.p1_stream_collectors_practice.KTStreams.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KTStreamsTest {

    private static final List<Payment> payments = Arrays.asList(
            new Payment("1", "John Doe", new BigDecimal("100.50"),
                    Payment.PaymentStatus.APPROVED, LocalDate.now(),
                    Payment.Currency.USD, "Credit Card", "REF123456"),
            new Payment("2", "Mary Jane", new BigDecimal("200.75"),
                    Payment.PaymentStatus.PENDING, LocalDate.now().minusDays(60),
                    Payment.Currency.EUR, "Bank Transfer", "REF654321"),
            new Payment("3", "John Doe", new BigDecimal("50.00"),
                    Payment.PaymentStatus.APPROVED, LocalDate.now(),
                    Payment.Currency.USD, "Credit Card", "REF789012"),
            new Payment("4", "Bob Smith", new BigDecimal("150.25"),
                    Payment.PaymentStatus.REJECTED, LocalDate.now(),
                    Payment.Currency.GBP, "PayPal", "REF345678"),
            new Payment("5", "Mary Jane", new BigDecimal("75.00"),
                    Payment.PaymentStatus.PENDING, LocalDate.now().minusDays(15),
                    Payment.Currency.USD, "Credit Card", "REF987654"),
            new Payment("6", "Alex Johnson", new BigDecimal("60.00"),
                    Payment.PaymentStatus.APPROVED, LocalDate.now(),
                    Payment.Currency.JPY, "Bank Transfer", "REF543210"),
            new Payment("7", "Emily Davis", new BigDecimal("80.75"),
                    Payment.PaymentStatus.FAILED, LocalDate.now().minusDays(10),
                    Payment.Currency.USD, "Crypto", "REF112233"),
            new Payment("8", "Michael Brown", new BigDecimal("120.50"),
                    Payment.PaymentStatus.REJECTED, LocalDate.now().minusDays(30),
                    Payment.Currency.EUR, "PayPal", "REF445566"),
            new Payment("9", "Linda White", new BigDecimal("95.00"),
                    Payment.PaymentStatus.PENDING, LocalDate.now(),
                    Payment.Currency.USD, "Credit Card", "REF778899"),
            new Payment("10", "David Clark", new BigDecimal("130.00"),
                    Payment.PaymentStatus.APPROVED, LocalDate.now().minusDays(45),
                    Payment.Currency.CAD, "Bank Transfer", "REF556677")
    );

    private static final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "Joe", 23, "Male", "First Department", 2020, 2000),
                    new Employee(2, "Mary", 24, "Female", "First Department", 2010, 3000),
                    new Employee(3, "Mike", 26, "Male", "Second Department", 2021, 2210),
                    new Employee(4, "Tom", 27, "Male", "Third Department", 2017, 1300),
                    new Employee(5, "Jack", 13, "Male", "Second Department", 2019, 2010),
                    new Employee(6, "Bob", 20, "Male", "First Department", 2022, 2100),
                    new Employee(7, "Kate", 43, "Female", "Fifth Department", 2021, 5000),
                    new Employee(8, "Roger", 36, "Male", "Fifth Department", 2015, 4000),
                    new Employee(9, "Sara", 67, "Female", "Third Department", 2010, 2600),
                    new Employee(10, "Ann", 27, "Female", "Fifth Department", 2021, 2070)
            )
    );

    @Test
    @Order(1)
    public void testFilterPaymentsByStatus() {
        List<Payment> approvedPayments = filterPaymentsByStatus(payments);
        Assertions.assertEquals(4, approvedPayments.size());
        Assertions.assertNotEquals(33, approvedPayments.size());
    }

    @Test
    @Order(2)
    void testTotalAmountByCurrency() {
        BigDecimal result = totalAmountByCurrency(payments);
        Assertions.assertEquals(new BigDecimal("401.25"), result);
        Assertions.assertNotEquals(new BigDecimal("500.00"), result);
    }

    @Test
    @Order(3)
    void testFindDistinctCustomers() {
        Set<String> result = findDistinctCustomers(payments, 9);
        Assertions.assertEquals(3, result.size());
        Assertions.assertNotEquals(4, result.size());
        Assertions.assertNotEquals(2, result.size());
        Assertions.assertTrue(result.contains("John Doe"));
        Assertions.assertTrue(result.contains("Bob Smith"));
        Assertions.assertFalse(result.contains("David Clark"));
    }

    @Test
    @Order(4)
    void testCountByPaymentMethod() {
        Map<String, Long> result = countByPaymentMethod(payments);
        Assertions.assertEquals(4L, (long) result.get("Credit Card"));
        Assertions.assertEquals(3L, (long) result.get("Bank Transfer"));
        Assertions.assertNotEquals(5L, (long) result.get("Bank Transfer"));
    }

    @Test
    @Order(5)
    void testFindEarliestPaymentDate() {
        LocalDate result = findEarliestPaymentDate(payments);
        Assertions.assertEquals(LocalDate.now().minusDays(60), result);
        Assertions.assertNotEquals(LocalDate.now().minusDays(30), result);
        Assertions.assertNotEquals(LocalDate.now(), result);
    }

    @Test
    @Order(6)
    void testGroupByCustomer() {
        Map<Payment.PaymentStatus, String> result = groupByCustomer(payments);
        Assertions.assertEquals("Mary Jane, Mary Jane, Linda White", result.get(Payment.PaymentStatus.PENDING));
        Assertions.assertEquals("Emily Davis", result.get(Payment.PaymentStatus.FAILED));
        Assertions.assertEquals("Bob Smith, Michael Brown", result.get(Payment.PaymentStatus.REJECTED));
    }

    @Test
    @Order(7)
    void testAllPaymentsApproved() {
        boolean result = allPaymentsApproved(payments);
        Assertions.assertFalse(result);
    }

    @Test
    @Order(8)
    void testFindMaxPaymentAmount() {
        BigDecimal result = findMaxPaymentAmount(payments);
        Assertions.assertEquals(new BigDecimal("200.75"), result);
        Assertions.assertNotEquals(new BigDecimal("200.00"), result);
    }

    @Test
    @Order(9)
    void testGetReferencesForCustomer() {
        List<String> resultForTwo = getReferencesForCustomer(payments, "Mary Jane");
        List<String> resultForOne = getReferencesForCustomer(payments, "Michael Brown");
        List<String> resultForNone = getReferencesForCustomer(payments, "Chuck Norris");
        Assertions.assertEquals(2, resultForTwo.size());
        Assertions.assertNotEquals(1, resultForTwo.size());
        Assertions.assertEquals(1, resultForOne.size());
        Assertions.assertNotEquals(2, resultForOne.size());
        Assertions.assertEquals(0, resultForNone.size());
        Assertions.assertNotEquals(1, resultForNone.size());
    }

    @Test
    @Order(10)
    void testPartitionByAmount() {
        Map<Boolean, List<Payment>> result = partitionByAmount(payments, new BigDecimal("100.00"));
        Assertions.assertEquals(5, result.get(true).size());
        Assertions.assertEquals(5, result.get(false).size());
        Assertions.assertNotEquals(4, result.get(false).size());
    }

    @Test
    @Order(11)
    void testGroupByCurrencyAndMapToCustomerNames() {
        Map<Payment.Currency, List<String>> result = groupByCurrencyAndMapToCustomerNames(payments);

        Assertions.assertEquals(5, result.get(Payment.Currency.USD).size());
        Assertions.assertEquals(1, result.get(Payment.Currency.JPY).size());
        Assertions.assertNotEquals(2, result.get(Payment.Currency.JPY).size());
        Assertions.assertTrue(result.get(Payment.Currency.EUR).contains("Mary Jane"));
        Assertions.assertTrue(result.get(Payment.Currency.JPY).contains("Alex Johnson"));
    }

    @Test
    @Order(12)
    void testGroupByStatusAndMapToPaymentMethods() {
        Map<Payment.PaymentStatus, Set<String>> result = groupByStatusAndMapToPaymentMethods(payments);

        Assertions.assertEquals(2, result.get(Payment.PaymentStatus.APPROVED).size());
        Assertions.assertEquals(1, result.get(Payment.PaymentStatus.FAILED).size());
        Assertions.assertTrue(result.get(Payment.PaymentStatus.REJECTED).contains("PayPal"));
    }

    @Test
    @Order(13)
    void testGroupByCustomerAndMapToTotalAmount() {
        Map<String, BigDecimal> result = groupByCustomerAndMapToTotalAmount(payments);

        Assertions.assertEquals(new BigDecimal("150.50"), result.get("John Doe"));
        Assertions.assertEquals(new BigDecimal("275.75"), result.get("Mary Jane"));
    }

    @Test
    @Order(14)
    void testGroupByDateAndMapToReferences() {
        Map<LocalDate, List<String>> result = groupByDateAndMapToReferences(payments);

        Assertions.assertEquals(5, result.get(LocalDate.now()).size());
        Assertions.assertTrue(result.get(LocalDate.now().minusDays(60)).contains("REF654321"));
        Assertions.assertTrue(result.get(LocalDate.now().minusDays(10)).contains("REF112233"));
    }

    @Test
    @Order(15)
    public void testCountEmployeesInDepartments() {
        Map<String, Long> result = countEmployeesInDepartments(employees);
        Assertions.assertEquals(Long.valueOf(3), result.get("First Department"));
        Assertions.assertEquals(Long.valueOf(2), result.get("Second Department"));
        Assertions.assertNull(result.get("Fourth Department"));
    }

    @Test
    @Order(16)
    public void testAverageSalaryByGender() {
        Map<String, Double> result = averageSalaryByGender(employees);
        Assertions.assertEquals(2270.0, result.get("Male"), 0.1);
        Assertions.assertEquals(3167.5, result.get("Female"), 0.1);
        Assertions.assertNotEquals(3000.0, result.get("Female"), 0.1);
    }

    @Test
    @Order(17)
    public void testGetHighestPaidEmployee() {
        Employee result = getHighestPaidEmployee(employees);
        Assertions.assertEquals("Kate", result.name());
        Assertions.assertEquals(5000, result.salary(), 0);
    }

    @Test
    @Order(18)
    public void testAverageAgeInDepartments() {
        Map<String, Double> result = averageAgeInDepartments(employees);
        Assertions.assertEquals(22.33, result.get("First Department"), 0.1);
        Assertions.assertEquals(19.5, result.get("Second Department"), 0.1);
        Assertions.assertEquals(35.3, result.get("Fifth Department"), 0.1);
        Assertions.assertNotEquals(135.3, result.get("Fifth Department"), 0.1);
    }

    @Test
    @Order(19)
    public void testGetMostExperiencedEmployee() {
        Employee result = getMostExperiencedEmployee(employees);
        Assertions.assertEquals("Mary", result.name());
        Assertions.assertEquals(2010, result.yearOfJoining());
    }

    @Test
    @Order(20)
    public void testCountEmployeesByGender() {
        Map<String, Long> result = countEmployeesByGender(employees);
        Assertions.assertEquals(Long.valueOf(6), result.get("Male"));
        Assertions.assertEquals(Long.valueOf(4), result.get("Female"));
    }

    @Test
    @Order(21)
    public void testSumSalariesByDepartment() {
        Map<String, Double> result = sumSalariesByDepartment(employees);
        Assertions.assertEquals(11070, result.get("Fifth Department"), 0.1);
        Assertions.assertEquals(3900, result.get("Third Department"), 0.1);
        Assertions.assertNotEquals(23900, result.get("Third Department"), 0.1);
    }

    @Test
    @Order(22)
    public void testOldestEmployeeInDepartment() {
        Map<String, Employee> result = oldestEmployeeInDepartment(employees);
        Assertions.assertEquals("Mike", result.get("Second Department").name());
        Assertions.assertEquals("Sara", result.get("Third Department").name());
        Assertions.assertEquals("Kate", result.get("Fifth Department").name());
        Assertions.assertNotEquals("Joe", result.get("Fifth Department").name());
    }

    @Test
    @Order(23)
    public void testGroupEmployeesByAgeRange() {
        Map<String, List<Employee>> result = groupEmployeesByAgeRange(employees);
        Assertions.assertEquals(2, result.get("Under 20").size());
        Assertions.assertEquals(5, result.get("20-30").size());
        Assertions.assertEquals(3, result.get("Above 30").size());
    }

}