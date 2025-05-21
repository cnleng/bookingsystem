package com.flexter.bookingsystem.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class TimeUtils {

    /**
     * 
     * @param dateString
     * @return
     */
    public LocalDateTime getLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime;
    }

    /**
     * 
     * @param start
     * @param end
     * @return
     */
    public boolean validate(LocalDateTime start, LocalDateTime end) {
        if (start!=null && end != null) {
            return start.isBefore(end);
        }
        return false;
    }
}
