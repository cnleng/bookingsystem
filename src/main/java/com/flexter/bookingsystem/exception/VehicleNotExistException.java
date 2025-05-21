package com.flexter.bookingsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VehicleNotExistException extends RuntimeException {

    private final String  entityId;

}
