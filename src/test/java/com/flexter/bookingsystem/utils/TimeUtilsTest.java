package com.flexter.bookingsystem.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.flexter.bookingsystem.exception.TimeRangeValidationException;

public class TimeUtilsTest {

    private final TimeUtils timeUtils = new TimeUtils();

    @Test
    void testGetDateTime_ValidFormat() {
        String input = "17-05-2025 05:30:00";
        LocalDateTime expected = LocalDateTime.of(2025, 5, 17, 5, 30, 0);
        LocalDateTime actual = timeUtils.getDateTime(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGetDateTime_InvalidFormat_ShouldThrowException() {
        String input = "2025-05-17 05:30:00"; // wrong format
        assertThatThrownBy(() -> timeUtils.getDateTime(input))
                .isInstanceOf(java.time.format.DateTimeParseException.class);
    }

    @Test
    void testValidate_ValidRange_ShouldReturnFalse() {
        LocalDateTime start = LocalDateTime.of(2025, 5, 17, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 17, 12, 0);

        boolean result = timeUtils.validate(start, end);

        assertThat(result).isFalse();
    }

    @Test
    void testValidate_StartAfterEnd_ShouldThrowException() {
        LocalDateTime start = LocalDateTime.of(2025, 5, 17, 13, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 17, 12, 0);

        assertThatThrownBy(() -> timeUtils.validate(start, end))
                .isInstanceOf(TimeRangeValidationException.class);
    }

    @Test
    void testValidate_NullStart_ShouldThrowException() {
        LocalDateTime end = LocalDateTime.of(2025, 5, 17, 12, 0);

        assertThatThrownBy(() -> timeUtils.validate(null, end))
                .isInstanceOf(TimeRangeValidationException.class);
    }

    @Test
    void testValidate_NullEnd_ShouldThrowException() {
        LocalDateTime start = LocalDateTime.of(2025, 5, 17, 12, 0);

        assertThatThrownBy(() -> timeUtils.validate(start, null))
                .isInstanceOf(TimeRangeValidationException.class);
    }
}
