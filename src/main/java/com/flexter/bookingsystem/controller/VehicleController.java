package com.flexter.bookingsystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flexter.bookingsystem.dto.VehicleEstimate;
import com.flexter.bookingsystem.service.VehicleService;
import com.flexter.bookingsystem.utils.Constants;
import com.flexter.bookingsystem.utils.TimeUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping(value = Constants.API_V1_VERSION + "/vehicles")
@RestController
@Slf4j
@AllArgsConstructor
public class VehicleController {

    private static final String VEHICLES_RETRIEVED = "Vehicles retrieved successfully";

    private final VehicleService vehicleService;
    private final TimeUtils timeUtils;

    @GetMapping
    @Operation(summary = "Get vehicles", description = "Create all available vehicles by city adn during time range")
    public ResponseEntity<ApiListResponse<VehicleEstimate>> getVehicles(
            @Parameter(description = "city be fetched")
            @RequestParam(required = true) String city,
            @Parameter(description = "Pickup datetime, in format dd-MM-yyyy hh:mm:ss")
            @RequestParam(required = true, name = "pickupDateTime") String pickupDateTime,
            @Parameter(description = "Return datetime, in format dd-MM-yyyy hh:mm:ss")
            @RequestParam(required = true, name = "returnDateTime") String returnDateTime
            ) throws Exception {

        log.info("Getting vehicles for city={}, pickupDateTime={}, returnDateTime={}", city, pickupDateTime, returnDateTime);

        LocalDateTime startTime = timeUtils.getDateTime(pickupDateTime);
        LocalDateTime endTime = timeUtils.getDateTime(returnDateTime);
        timeUtils.validate(startTime, endTime);
        List<VehicleEstimate> vehicles = vehicleService.getVehicles(
            city, 
            startTime, 
            endTime);

        ApiListResponse<VehicleEstimate> response = ApiListResponse.<VehicleEstimate>builder().data(vehicles).total(vehicles!= null ? vehicles.size() : 0).message(VEHICLES_RETRIEVED).build();
        return ResponseEntity.ok(response);
    }
}
