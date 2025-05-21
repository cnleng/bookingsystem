package com.flexter.bookingsystem.service;
import java.util.List;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.flexter.bookingsystem.repository.VehicleRepository;
import com.flexter.bookingsystem.utils.PriceCalculator;
import com.flexter.bookingsystem.dto.VehicleDto;
import com.flexter.bookingsystem.dto.VehicleEstimate;
import com.flexter.bookingsystem.model.Vehicle;
import com.flexter.bookingsystem.exception.VehicleException;

@Service("vehicleService")
@Slf4j
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final PriceCalculator priceCalculator;
    private final ModelMapper mapper;

    @Cacheable(value = "vehicle-city-cache", key = "#city + '-' + #pickupDateTime.toString()+ '-' + #returnDateTime.toString()")
    public List<VehicleEstimate> getVehicles(String city, LocalDateTime pickupDateTime, LocalDateTime returnDateTime) {
        try {
            
            List<Vehicle> vehicles = vehicleRepository.getVehicleByCityPickupReturnTime(
                city, 
                pickupDateTime.getDayOfWeek(),
                returnDateTime.getDayOfWeek(),
                pickupDateTime.toLocalTime(),
                returnDateTime.toLocalTime()
            );

            if (vehicles!=null && !vehicles.isEmpty()) {
                return vehicles.stream().map( 
                    v -> VehicleEstimate.builder().price(priceCalculator.getPrice(pickupDateTime, returnDateTime, v.getRate())).vehicleDto(mapper.map(v, VehicleDto.class)).build())
                    .toList();
            }
            return List.of();

        } catch(Exception e) {
            throw new VehicleException(e.getMessage());
        }
    }

}
