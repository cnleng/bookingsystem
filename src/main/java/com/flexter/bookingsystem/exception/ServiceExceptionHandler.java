package com.flexter.bookingsystem.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import com.flexter.bookingsystem.controller.ApiResponse;

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        exception.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        log.error("An error occurred while processing request {} ", ex);
        log.error("An error with request {} ", request);
        ApiResponse<?> response = ApiResponse.builder()
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BookingNotExistException.class)
    public ResponseEntity<ApiResponse<String>> handleBookingNotExistException(BookingNotExistException e) {
        String message = String.format("An error occurred while getting Booking with id [%s] ", e.getEntityId());
        log.error(message);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(VehicleNotExistException.class)
    public ResponseEntity<ApiResponse<String>> handleVehicleNotExistException(VehicleNotExistException e) {
        String message = String.format("An error occurred while getting Vehicle with id [%s] ", e.getEntityId());
        log.error(message);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(VehicleNotAvailableException.class)
    public ResponseEntity<ApiResponse<String>> handleVehicleNotAvailableException(VehicleNotAvailableException e) {
        String message = String.format("Vehicle is not available at pickup day/time [%s] and return day/time [%s]", e.getPickupDateTime(), e.getReturnDateTime());
        log.error(message);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(TimeRangeValidationException.class)
    public ResponseEntity<?> handleTimeRangeValidationException(TimeRangeValidationException e, HttpServletRequest request) {
        String message = String.format("Time range is incorrect: pickup day/time [%s] is after return day/time [%s]", e.getStart(), e.getEnd());
        log.error(message);
        Map<String, String> result = new HashMap<>();
        result.put("message", message);
        result.put("pickupDateTime", e.getStart().toString());
        result.put("returnDateTime", e.getEnd().toString());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

}
