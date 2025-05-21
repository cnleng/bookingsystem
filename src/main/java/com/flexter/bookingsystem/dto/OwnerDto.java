package com.flexter.bookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerDto {
    private String id;
    private String name;
    private String email;
    // private List<VehicleDto> vehicles;
}
