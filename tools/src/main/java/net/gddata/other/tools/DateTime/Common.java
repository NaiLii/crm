package net.gddata.other.tools.DateTime;

import javafx.beans.DefaultProperty;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by knix on 16/3/7.
 */
public class Common {
    public static Integer Toyear(){
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.YEAR);
    }

    public static Integer getYear(Date date){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.YEAR);
    }
}
