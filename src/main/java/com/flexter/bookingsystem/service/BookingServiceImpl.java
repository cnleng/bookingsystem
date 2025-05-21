package com.flexter.bookingsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexter.bookingsystem.dto.BookingDto;
import com.flexter.bookingsystem.exception.BookingNotExistException;
import com.flexter.bookingsystem.exception.VehicleNotAvailableException;
import com.flexter.bookingsystem.exception.VehicleNotExistException;
import com.flexter.bookingsystem.model.Booking;
import com.flexter.bookingsystem.model.BookingCreationObject;
import com.flexter.bookingsystem.model.Vehicle;
import com.flexter.bookingsystem.repository.BookingRepository;
import com.flexter.bookingsystem.repository.VehicleRepository;
import com.flexter.bookingsystem.utils.PriceCalculator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("bookingService")
@Slf4j
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final PriceCalculator priceCalculator;
    private final ModelMapper mapper;

    public BookingDto getBookingById(String id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotExistException(id));
        BookingDto dto = mapper.map(booking, BookingDto.class);
        return dto;
    }

    @Transactional
    public BookingDto createBooking(BookingCreationObject bo) {

        LocalDateTime pickupTime = bo.getPickupDateTime();
        LocalDateTime returnTime = bo.getReturnDateTime();
        String vehicleId = bo.getVehicleId();

        List<Vehicle> vehicles = vehicleRepository.getVehicleByIdPickupReturnTime(
            vehicleId, 
            pickupTime.getDayOfWeek(),
            returnTime.getDayOfWeek(),
            pickupTime.toLocalTime(),
            returnTime.toLocalTime()
            );
        
        if (vehicles==null || vehicles.isEmpty() ) {
            throw new VehicleNotAvailableException(pickupTime.toString(), returnTime.toString());
        }
        Vehicle vehicle = vehicles.stream().findFirst().get();

        Double price = priceCalculator.getPrice(pickupTime, returnTime, vehicle.getRate());
        Booking booking = Booking.builder()
                .pickupDateTime(pickupTime)
                .returnDateTime(returnTime)
                .price(price)
                .bookedOnDateTime(LocalDateTime.now())
                .vehicle(vehicle)
                .build();

        Booking savedBooking = bookingRepository.save(booking);
        return mapper.map(savedBooking, BookingDto.class);
    }

}
