package net.gddata.other.tools.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by knix on 16/3/6.
 */
public class Translate {

    public static Date calendar2Date(Calendar calendar) {
        return new Date(calendar.getTimeInMillis());
    }

    public static Calendar date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
