package com.flexter.bookingsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.flexter.bookingsystem.dto.VehicleEstimate;

public interface VehicleService {

    List<VehicleEstimate> getVehicles(String city, LocalDateTime pickupDateTime, LocalDateTime returnDateTime);

}
