package com.flexter.bookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {
    private String id;
    private String make;
    private String year;
    private String model;
    private Double rate;
    private String city;
    // private List<AvailabilityDto> availabilities;
    // private List<BookingDto> bookings;
    private OwnerDto owner;
}
