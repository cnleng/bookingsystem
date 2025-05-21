package com.flexter.bookingsystem.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flexter.bookingsystem.model.Booking;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, String> {
    
    @Query("SELECT b FROM Booking b " +
        "JOIN b.vehicle v " +
        "WHERE v.id = :vehicleId " +
        "AND b.pickupDateTime >= :pickupDateTime AND b.returnDateTime <= :returnDateTime"
    )
    List<Booking> getBookingByVehiclePickupReturnTime(
        @Param("vehicleId") String vehicleId,
        @Param("pickupDateTime") LocalDateTime pickupDateTime,
        @Param("returnDateTime") LocalDateTime returnDateTime
    );

}
