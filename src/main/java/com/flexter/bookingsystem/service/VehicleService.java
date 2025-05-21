package com.flexter.bookingsystem.service;

import java.util.List;
import java.time.LocalDateTime;

import com.flexter.bookingsystem.dto.VehicleDto;

public interface VehicleService {

    List<VehicleDto> getVehicles(String city, LocalDateTime pickupDateTime, LocalDateTime returnDateTime);

}
