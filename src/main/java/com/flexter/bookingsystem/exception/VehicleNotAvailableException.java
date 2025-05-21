package com.flexter.bookingsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VehicleNotAvailableException extends RuntimeException {

    private final String pickupDateTime;
    private final String returnDateTime;

}
