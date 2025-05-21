package com.flexter.bookingsystem.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleEstimate implements Serializable {

    private Double price;
    private VehicleDto vehicleDto;

}
