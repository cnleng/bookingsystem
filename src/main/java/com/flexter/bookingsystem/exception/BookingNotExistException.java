package com.flexter.bookingsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingNotExistException extends RuntimeException {

    private final String  entityId;

}
