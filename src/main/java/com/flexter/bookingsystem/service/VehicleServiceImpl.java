package com.flexter.bookingsystem.service;
import java.util.List;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.flexter.bookingsystem.repository.VehicleRepository;
import com.flexter.bookingsystem.dto.VehicleDto;
import com.flexter.bookingsystem.model.Vehicle;
import com.flexter.bookingsystem.exception.VehicleException;

@Service("vehicleService")
@Slf4j
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper mapper;

    public List<VehicleDto> getVehicles(String city, LocalDateTime pickupDateTime, LocalDateTime returnDateTime) {
        try {
            List<Vehicle> vehicles = this.vehicleRepository.getVehicleByCityPickupReturnTime(city, pickupDateTime, returnDateTime);
            return vehicles.stream().map( v -> mapper.map(v, VehicleDto.class)).toList();
        } catch(Exception e) {
            throw new VehicleException(e.getMessage());
        }
    }

}
