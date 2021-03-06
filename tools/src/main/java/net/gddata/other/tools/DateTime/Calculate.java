package net.gddata.other.tools.DateTime;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static net.gddata.other.tools.DateTime.Translate.date2Calendar;

public class Calculate {
    public static int getTodayOfYear(Date date) {
        int i = -1;
        if (null == date) {
            return i;
        }
        try {
            i = date2Calendar(date).get(Calendar.DAY_OF_YEAR);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return i;
    }

    public static int getNextMonthDayOfYear(Date date) {
        int i = -1;
        if (null == date) {
            return i;
        }
        try {
            Calendar calendar = date2Calendar(date);
            calendar.add(Calendar.MONTH, 1);
            i = calendar.get(Calendar.DAY_OF_YEAR);

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return i;
    }

    public static Timestamp thisMonthStart() {
        return getMonthStart(0);
    }

    public static Timestamp thisMonthEnd() {
        return getMonthEnd(0);
    }

    public static Timestamp getMonthStart(Integer month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTime().getTime());
    }
    public static Timestamp getMonthEnd(Integer month){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month+1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        return new Timestamp(calendar.getTime().getTime());
    }
}
