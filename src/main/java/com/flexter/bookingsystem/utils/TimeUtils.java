package com.flexter.bookingsystem.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.flexter.bookingsystem.exception.TimeRangeValidationException;
import com.flexter.bookingsystem.utils.Constants;

import org.springframework.stereotype.Component;

@Component
public class TimeUtils {

    /**
     * 
     * @param dateString
     * @return
     */
    public LocalDateTime getDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);
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
            if (!start.isBefore(end)) {
                throw new TimeRangeValidationException(start, end);
            }
            return false;
        }
        throw new TimeRangeValidationException( start, end);
    }
}
