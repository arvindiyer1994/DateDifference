import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DateDifferenceMain {
    public static void main(String []args) throws Exception{
        // Using the main as a wrapper
        DateDifference runCode = new DateDifference();
        runCode.Start();
    }
}

class DateDifference {
    public DateDifference() { }

    public void Start() throws Exception{
        GetDate();

        int day        = m_secondDate[0];
        int month      = m_secondDate[1];
        int year       = m_secondDate[2];
        int daysToStartOfYear = day;

        int daysToStartOfFirstYear = m_firstDate[0];
        int firstMonth = m_firstDate[1];
        int firstYear = m_firstDate[2];

        CalculateDifferenceBetweenTwoDates(daysToStartOfFirstYear, firstMonth, firstYear, daysToStartOfYear, month, year);

        System.out.println(m_firstDate[0] + " " + m_firstDate[1] + " " + m_firstDate[2] + ", " +
                m_secondDate[0] + " " + m_secondDate[1] + " " + m_secondDate[2] + ", "
                + m_differenceDays
        );
    }

     protected int CalculateDifferenceBetweenTwoDates(int daysFirstYear, int monthFirstYear, int yearFirstYear, int daysSecondYear, int monthSecondYear, int yearSecondYear) {
        // For second date calculate time till start of the year
        daysSecondYear = GetDaysToStartOfYear(daysSecondYear, monthSecondYear, yearSecondYear);
        yearSecondYear = yearSecondYear - 1;

        // Add the days in a year till we reach the year of first date
        while (yearSecondYear >= yearFirstYear){
            daysSecondYear = daysSecondYear + (DaysInFeb(yearSecondYear--) == 28 ? 365 : 366);
        }

        // For first date calculate the number of days till the start of the year


        daysFirstYear = GetDaysToStartOfYear(daysFirstYear, monthFirstYear, yearFirstYear);

        m_differenceDays = daysSecondYear - daysFirstYear;

        return m_differenceDays;

    }

    protected int GetDaysToStartOfYear(int days, int month, int year) {
        try {
            month--;
            while (month != 0) {
                days = days + DaysInMonth(month--, year);
            }
            return days;
        }catch(Exception ex)
        {
            // The places where this function should be called should have verified date, but just in case.
            System.out.println("Invalid date");
            return 0;
        }
    }


    private void GetDate() {
        String str;
        String[] dates;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            str = reader.readLine();
            dates = str.split(", ");
            if (!ValidDate(dates[0])) throw new Exception("Invalid date");
            if (!ValidDate(dates[1])) throw new Exception("Invalid date");

            String[] tempDateArray = (dates[0]).split(" ");

            m_firstDate[0] = Integer.parseUnsignedInt(tempDateArray[0]);
            m_firstDate[1] = Integer.parseUnsignedInt(tempDateArray[1]);
            m_firstDate[2] = Integer.parseUnsignedInt(tempDateArray[2]);

            tempDateArray = (dates[1]).split(" ");

            m_secondDate[0] = Integer.parseUnsignedInt(tempDateArray[0]);
            m_secondDate[1] = Integer.parseUnsignedInt(tempDateArray[1]);
            m_secondDate[2] = Integer.parseUnsignedInt(tempDateArray[2]);

            // Swap dates if first date is larger.
            if (m_firstDate[2] > m_secondDate[2]) SwapDates();

            if (m_firstDate[2] == m_secondDate[2]
                    && GetDaysToStartOfYear(m_firstDate[0], m_firstDate[1], m_firstDate[2])
                    > GetDaysToStartOfYear(m_secondDate[0], m_secondDate[1], m_secondDate[2])
            ){
                SwapDates();
            }
        }
        catch(Exception ex) {
            System.out.println("Oops something happened");
            System.exit(1);
        }
    }

    protected boolean ValidDate(String date) {
        String[] dateArray = date.split(" ");

        try {
            if (dateArray.length != 3) throw new Exception("Invalid date");

            int day = Integer.parseUnsignedInt(dateArray[0]);
            int month = Integer.parseUnsignedInt(dateArray[1]);
            int year = Integer.parseUnsignedInt(dateArray[2]);

            if (year < 1900 || year > 2010) throw new Exception("Year out of range");
            if (month > 12) throw new Exception("Month out of range");
            if (!IsValidDay(day, month, year)) throw new Exception("Day out of range");

        } catch (Exception ex) {
            return false; //Invalid date.
        }
        return true;
    }

    protected boolean IsValidDay(int day, int month, int year)throws Exception{
        return DaysInMonth(month, year) >= day;
    }

    protected int DaysInMonth(int month, int year) throws Exception{
        switch (month)
        {
            case 1 : return 31;
            case 2 : return DaysInFeb(year);
            case 3 : return 31;
            case 4 : return 30;
            case 5 : return 31;
            case 6 : return 30;
            case 7 : return 31;
            case 8 : return 31;
            case 9 : return 30;
            case 10: return 31;
            case 11: return 30;
            case 12: return 31;
            default: throw new Exception("Invalid month");
        }
    }
    private int DaysInFeb(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) return 29;
            }
            else return 29;
            return 28; // Weird case where 2000 is a leap year but 2100 isn't a leap year
        }
        return 28;
    }

    private void SwapDates(){
        int temp;
        for (int index = 0; index < 3 ; index++) {
            temp = m_firstDate[index];
            m_firstDate[index] = m_secondDate[index];
            m_secondDate[index] = temp;
        }
    }

    private int[] m_firstDate = new int[3];
    private int[] m_secondDate= new int[3];
    private int      m_differenceDays = 0;

}

