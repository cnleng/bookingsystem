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
public class VehicleDto implements Serializable {
    private String id;
    private String make;
    private String year;
    private String model;
    private Double rate;
    private String city;
    private OwnerDto owner;
}
