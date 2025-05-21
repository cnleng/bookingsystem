package com.flexter.bookingsystem.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto implements Serializable{
    private String id;
    private LocalDateTime bookedOnDateTime;
    private LocalDateTime pickupDateTime;
    private LocalDateTime returnDateTime;
    private Double price;
    private VehicleDto vehicle;
}
