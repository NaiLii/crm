package net.gddata.other.tools.DateTime;

import java.util.Date;

/**
 * Created by knix on 16/3/5.
 */
public class Format {
    public static String toString(Date date, String format){
        if(format.equals("")){
            return date.toString();
        }
        if (null != date) {
            return new java.text.SimpleDateFormat(format).format(date);
        }
        return "";
    }
}
