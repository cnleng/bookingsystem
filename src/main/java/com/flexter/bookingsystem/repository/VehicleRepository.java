package com.flexter.bookingsystem.repository;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flexter.bookingsystem.model.Vehicle;

@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Query("""
        SELECT DISTINCT v FROM Vehicle v 
        JOIN FETCH v.bookings b 
        WHERE LOWER(v.city) = LOWER(:city) 
        AND b.pickupDateTime >= :pickupDateTime AND b.returnDateTime <= :pickupDateTime 
        AND b.pickupDateTime >= :returnDateTime AND b.returnDateTime <= :returnDateTime 
        """
    )
    List<Vehicle> getVehicleByCityPickupReturnTime(
        @Param("city") String city,
        @Param("pickupDateTime") LocalDateTime pickupDateTime,
        @Param("returnDateTime") LocalDateTime returnDateTime
    );

}