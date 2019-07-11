package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    // Time(hour and minutes) in 24 format
    private static final String TIME_24_FORMAT = "HH:mm";

    /**
     * Returns day order of current date time. 1 as Monday.
     * 
     * @return day order
     */
    public static int getDayOrder() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return dayOfWeek.getValue();
    }

    /**
     * Returns time (hour and minutes) string in 24 format
     * 
     * @return time (hour and minutes) string
     */
    public static String getTime24() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(TIME_24_FORMAT);
        return LocalDateTime.now().format(dtf);
    }
}
