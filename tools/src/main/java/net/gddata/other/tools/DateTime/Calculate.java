package net.gddata.other.tools.DateTime;

import java.util.Calendar;
import java.util.Date;

import static net.gddata.other.tools.DateTime.Translate.date2Calendar;

/**
 * Created by knix on 16/3/6.
 */
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
            calendar.add(Calendar.MONTH,1);
            i = calendar.get(Calendar.DAY_OF_YEAR);

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return i;
    }
}
