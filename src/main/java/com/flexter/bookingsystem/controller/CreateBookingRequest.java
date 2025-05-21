package com.flexter.bookingsystem.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CreateBookingRequest {

    @JsonProperty("vehicleId")
    @NotNull(message = "The vehicle identifier is required.")
    private String vehicleId;

    @JsonProperty("pickupDateTime")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @NotNull(message = "The pickup Date and Time are required.")
    private LocalDateTime pickupDateTime;

    @JsonProperty("returnDateTime")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @NotNull(message = "The return Date and Time are required.")
    private LocalDateTime returnDateTime;
}
