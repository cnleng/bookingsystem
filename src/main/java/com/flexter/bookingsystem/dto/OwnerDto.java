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
public class OwnerDto implements Serializable {
    private String id;
    private String name;
    private String email;
    // private List<VehicleDto> vehicles;
}
