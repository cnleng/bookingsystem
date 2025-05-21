package com.flexter.bookingsystem.service;

import com.flexter.bookingsystem.model.BookingCreationObject;
import com.flexter.bookingsystem.dto.BookingDto;

public interface BookingService {

    BookingDto createBooking(BookingCreationObject booking);

    BookingDto getBookingById(String id);

}
