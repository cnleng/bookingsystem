package com.flexter.bookingsystem.model;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCreationObject {
    private String vehicleId;
    private LocalDateTime pickupDateTime;
    private LocalDateTime returnDateTime;
}
