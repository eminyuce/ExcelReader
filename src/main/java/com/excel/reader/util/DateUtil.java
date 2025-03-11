package com.excel.reader.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String YYYY_M_MDD = "yyyyMMdd";

    public static String getFormattedCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(YYYY_M_MDD);
        return dateFormat.format(date);
    }

    public static String getCurrentDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Format the current date to string
        return currentDate.format(formatter);
    }
}
