package com.flexter.bookingsystem.service;

import java.time.LocalDateTime;

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

        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotExistException(vehicleId));
        boolean isTargetPickupDay = vehicle.getAvailabilities().stream()
            .filter(a -> a.getFromTime().isBefore(pickupTime.toLocalTime())
            && a.getToTime().isAfter(pickupTime.toLocalTime()))
            .anyMatch(a -> a.getDayOfWeek().equals(pickupTime.getDayOfWeek()) );
        boolean isTargetReturnDay = vehicle.getAvailabilities().stream()
            .filter(a -> a.getFromTime().isBefore(pickupTime.toLocalTime())
            && a.getToTime().isAfter(pickupTime.toLocalTime()))
            .anyMatch(a -> a.getDayOfWeek().equals(returnTime.getDayOfWeek()) );
        if (!isTargetPickupDay || !isTargetReturnDay) {
            throw new VehicleNotAvailableException(pickupTime.toString(), returnTime.toString());
        }

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
