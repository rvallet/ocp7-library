package com.library.msbatch.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTools {

    public static Date yesterday(){
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
