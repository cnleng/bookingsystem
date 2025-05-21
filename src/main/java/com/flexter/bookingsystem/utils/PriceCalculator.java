package com.flexter.bookingsystem.utils;

import java.time.LocalDateTime;
import java.time.Duration;
import org.springframework.stereotype.Component;

@Component
public class PriceCalculator {

    public Double getPrice(LocalDateTime fromTime, LocalDateTime toTime, Double rate) {
        Duration duration = Duration.between(fromTime, toTime);
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        if (hours > 0 || minutes > 0) {
            days++;
        }
        return days * rate;
    }

}
