import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateDifferenceTest extends DateDifference{

    @Test
    void TestDaysToStartOfYearStartingJan(){
        DateDifference daysObj = new DateDifference();
        assertEquals(1, daysObj.GetDaysToStartOfYear(1,1,2000));
    }

    @Test
    void TestDaysToStartOfYearStartingMarchLeapYear(){
        DateDifference daysObj = new DateDifference();
        assertEquals(61, daysObj.GetDaysToStartOfYear(1,3,2000));
    }

    @Test
    void TestDaysToStartOfYearStartingMarchNonLeapYear(){
        DateDifference daysObj = new DateDifference();
        assertEquals(60, daysObj.GetDaysToStartOfYear(1,3,1999));
    }

    @Test
    void TestValidDate(){
        DateDifference daysObj = new DateDifference();
        assertTrue(daysObj.ValidDate("15 04 1969"));
    }

    @Test
    void TestInvalidDate(){
        DateDifference daysObj = new DateDifference();
        assertFalse(daysObj.ValidDate("31 02 1969"));
    }

    @Test
    void TestNumberOfDaysInMonth(){
        DateDifference daysObj = new DateDifference();
        try {
            assertEquals(31, daysObj.DaysInMonth(5, 2000));
        }catch  (Exception ex)
        {}
    }

    @Test
    void TestNumberOfDaysNegativeMonth(){
        DateDifference daysObj = new DateDifference();

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            daysObj.DaysInMonth(-1, 2000);
        });

        Assertions.assertEquals("Invalid month", thrown.getMessage());
    }

    @Test
    void TestNumberOfDaysInvalidMonth(){
        DateDifference daysObj = new DateDifference();

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            daysObj.DaysInMonth(13, 2000);
        });

        Assertions.assertEquals("Invalid month", thrown.getMessage());
    }

    @Test
    void TestValidDay(){
        DateDifference daysObj = new DateDifference();
        try{
            Assertions.assertTrue(daysObj.IsValidDay(20,1,1975));

        }catch(Exception ex)
        {
        }
    }

    @Test
    void TestInvalidDay(){
        DateDifference daysObj = new DateDifference();

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            Assertions.assertTrue(daysObj.IsValidDay(2,13,1975));
        });
        Assertions.assertEquals("Invalid month", thrown.getMessage());
    }

    @Test
    void TestCalculateDifference2000sLeapYear() {
        DateDifference daysObj = new DateDifference();
        int noDateDiff = daysObj.CalculateDifferenceBetweenTwoDates(8, 1, 1995, 24,12,2005);
        Assertions.assertEquals(4003, noDateDiff);
    }

    @Test
    void TestCalculateDifferencePre2000LeapYear() {
        DateDifference daysObj = new DateDifference();
        int noDateDiff = daysObj.CalculateDifferenceBetweenTwoDates(12, 9, 1945, 15,4,1969);
        Assertions.assertEquals(8616, noDateDiff);
    }

    @Test
    void TestCalculateDifferenceMax() { //Max supported dates
        DateDifference daysObj = new DateDifference();
        int noDateDiff = daysObj.CalculateDifferenceBetweenTwoDates(1, 1, 1900, 31,12,2010);
        Assertions.assertEquals(40541, noDateDiff);
    }

    @Test
    void TestCalculateDifferenceMin() {
        DateDifference daysObj = new DateDifference();
        int noDateDiff = daysObj.CalculateDifferenceBetweenTwoDates(5, 5, 1950, 5,5,1950);
        Assertions.assertEquals(0, noDateDiff);
    }

}