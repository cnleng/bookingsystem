package com.flexter.bookingsystem.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class PriceCalculatorTest {
private final PriceCalculator priceCalculator = new PriceCalculator();

    @Test
    void testGetPrice_SameTime_ShouldReturnZero() {
        LocalDateTime now = LocalDateTime.now();
        Double rate = 100.0;

        Double price = priceCalculator.getPrice(now, now, rate);

        assertThat(price).isEqualTo(0.0);
    }

    @Test
    void testGetPrice_ExactlyOneDay() {
        LocalDateTime from = LocalDateTime.of(2025, 5, 17, 10, 0);
        LocalDateTime to = from.plusDays(1); // exactly 24 hours later
        Double rate = 100.0;

        Double price = priceCalculator.getPrice(from, to, rate);

        assertThat(price).isEqualTo(100.0);
    }

    @Test
    void testGetPrice_LessThanOneDay_ShouldChargeOneDay() {
        LocalDateTime from = LocalDateTime.of(2025, 5, 17, 10, 0);
        LocalDateTime to = from.plusHours(3).plusMinutes(20);
        Double rate = 80.0;

        Double price = priceCalculator.getPrice(from, to, rate);

        assertThat(price).isEqualTo(80.0);
    }

    @Test
    void testGetPrice_TwoDaysWithExtraMinutes_ShouldChargeThreeDays() {
        LocalDateTime from = LocalDateTime.of(2025, 5, 17, 10, 0);
        LocalDateTime to = from.plusDays(2).plusHours(2); // 2 full days + extra
        Double rate = 75.0;

        Double price = priceCalculator.getPrice(from, to, rate);

        assertThat(price).isEqualTo(225.0); // 3 * 75
    }

    @Test
    void testGetPrice_OnlyMinutes_ShouldChargeOneDay() {
        LocalDateTime from = LocalDateTime.of(2025, 5, 17, 10, 0);
        LocalDateTime to = from.plusMinutes(1);
        Double rate = 50.0;

        Double price = priceCalculator.getPrice(from, to, rate);

        assertThat(price).isEqualTo(50.0);
    }

}
