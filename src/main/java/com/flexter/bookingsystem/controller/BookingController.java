package com.flexter.bookingsystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexter.bookingsystem.utils.Constants;
import com.flexter.bookingsystem.dto.BookingDto;
import com.flexter.bookingsystem.model.BookingCreationObject;
import com.flexter.bookingsystem.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents an Booking REST Controller.
 * <p>
 * This class contains details about booking CRUD endpoints.
 * </p>
 * 
 * @author Cyril Nleng
 * @version 1.0
 * @since 2025-05-19
 */
@Tag(name = "Booking API", description = "Booking Managment API")
@CrossOrigin(origins = "*")
@RequestMapping(value = Constants.API_V1_VERSION + "/bookings")
@RestController
@Slf4j
@AllArgsConstructor
public class BookingController {

    private static final String BOOKING_CREATED = "Booking created successfully";
    private static final String BOOKING_RETRIEVED = "Booking retrieved successfully";

    private final BookingService bookingService;
    private final ModelMapper mapper;

    @PostMapping
    @Operation(summary = "Create a booking", description = "Create a new booking for a vehicle")
    public ResponseEntity<ApiResponse<BookingDto>> createBooking(@Valid @RequestBody CreateBookingRequest request) throws Exception {
        
        log.info("Creating booking with vehicle Id={}, pickupDateTime={}, returnDateTime={}",
                request.getVehicleId(),
                request.getPickupDateTime(),
                request.getReturnDateTime());

        BookingDto createdBooking = bookingService.createBooking(mapper.map(request, BookingCreationObject.class));
        ApiResponse<BookingDto> response = ApiResponse.<BookingDto>builder()
                .message(BOOKING_CREATED)
                .data(createdBooking)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Retrieve booking", description = "Retrieve an existing booking using its id")
    public ResponseEntity<ApiResponse<BookingDto>> getBooking(@PathVariable String id) throws Exception {
        log.info("Getting booking with Id = {}", id);
        BookingDto dto = bookingService.getBookingById(id);
        ApiResponse<BookingDto> response = ApiResponse.<BookingDto>builder()
                .message(BOOKING_RETRIEVED)
                .data(dto)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}