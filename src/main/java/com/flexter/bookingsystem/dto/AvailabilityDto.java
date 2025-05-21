package com.flexter.bookingsystem.dto;
import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilityDto {
    private String id;
    private DayOfWeek dayOfWeek;
    private LocalTime fromTime;
    private LocalTime toTime;
    private VehicleDto vehicle;
}
