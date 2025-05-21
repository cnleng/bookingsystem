package com.flexter.bookingsystem.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimeRangeValidationException extends RuntimeException {

    private final LocalDateTime start;
    private final LocalDateTime end;

}
