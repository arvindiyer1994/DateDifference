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



}