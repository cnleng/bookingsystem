package com.flexter.bookingsystem.repository;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flexter.bookingsystem.model.Vehicle;

@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Query("""
        SELECT v FROM Vehicle v 
        JOIN v.availabilities a 
        WHERE LOWER(v.city) = LOWER(:city) 
        AND (a.dayOfWeek = :pickupDay OR a.dayOfWeek = :returnDay)
        AND (a.fromTime <= :pickupTime AND a.toTime >= :pickupTime) 
        AND (a.fromTime <= :returnTime AND a.toTime >= :returnTime) 
        GROUP BY v
        HAVING COUNT(DISTINCT a.dayOfWeek) = 2
        """
    )
    List<Vehicle> getVehicleByCityPickupReturnTime(
        @Param("city") String city,
        @Param("pickupDay") DayOfWeek pickupDay,
        @Param("returnDay") DayOfWeek returnDay,
        @Param("pickupTime") LocalTime pickupTime,
        @Param("returnTime") LocalTime returnTime
    );

    @Query("""
        SELECT v
        FROM Vehicle v
        JOIN v.availabilities a
        WHERE v.id = :vehicleId
        AND (a.dayOfWeek = :pickupDay OR a.dayOfWeek = :returnDay)
        AND (a.fromTime <= :pickupTime AND a.toTime >= :pickupTime) 
        AND (a.fromTime <= :returnTime AND a.toTime >= :returnTime)  
        GROUP BY v
        HAVING COUNT(DISTINCT a.dayOfWeek) = 2
        """
    )
    List<Vehicle> getVehicleByIdPickupReturnTime(
        @Param("vehicleId") String vehicleId,
        @Param("pickupDay") DayOfWeek pickupDay,
        @Param("returnDay") DayOfWeek returnDay,
        @Param("pickupTime") LocalTime pickupTime,
        @Param("returnTime") LocalTime returnTime
    );

    // @Query("""
    //     SELECT v
    //     FROM Vehicle v
    //     JOIN v.availabilities a
    //     WHERE v.id = :vehicleId
    //     AND (a.dayOfWeek = :pickupDay OR a.dayOfWeek = :returnDay) 
    //     GROUP BY v
    //     HAVING COUNT(DISTINCT a.dayOfWeek) = 2
    //     """
    // )
    // List<Vehicle> getVehicleByIdPickupReturnTime(
    //     @Param("vehicleId") String vehicleId,
    //     @Param("pickupDay") DayOfWeek pickupDay,
    //     @Param("returnDay") DayOfWeek returnDay
    // );

}