package com.flexter.bookingsystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flexter.bookingsystem.dto.VehicleEstimate;
import com.flexter.bookingsystem.service.VehicleService;
import com.flexter.bookingsystem.utils.Constants;
import com.flexter.bookingsystem.utils.TimeUtils;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents an Vehicle REST Controller.
 * <p>
 * This class contains details about vehicle CRUD endpoints.
 * </p>
 * 
 * @author Cyril Nleng
 * @version 1.0
 * @since 2025-05-19
 */
@Tag(name = "Vehicle API", description = "Vehicle Management API (CRUD)")
@CrossOrigin(origins = "*")
@RequestMapping(value = Constants.API_V1_VERSION + "/vehicles")
@RestController
@Slf4j
@AllArgsConstructor
public class VehicleController {

    private static final String VEHICLES_RETRIEVED = "Vehicles retrieved successfully";

    private final VehicleService vehicleService;
    private final TimeUtils timeUtils;

    @GetMapping
    public ResponseEntity<ApiListResponse<VehicleEstimate>> getVehicles(
            @RequestParam(required = true) String city,
            @RequestParam(required = true, name = "pickupDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime pickupDateTime,
            @RequestParam(required = true, name = "returnDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime
            ) throws Exception {

        log.info("Getting vehicles for city={}, pickupDateTime={}, returnDateTime={}", city, pickupDateTime, returnDateTime);
        List<VehicleEstimate> vehicles = vehicleService.getVehicles(city, pickupDateTime, returnDateTime);

        ApiListResponse<VehicleEstimate> response = ApiListResponse.<VehicleEstimate>builder().data(vehicles).total(vehicles!= null ? vehicles.size() : 0).message(VEHICLES_RETRIEVED).build();
        return ResponseEntity.ok(response);
    }
}
