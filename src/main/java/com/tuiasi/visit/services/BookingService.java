package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    BookingEntity createBooking(BookingEntity bookingEntity);

    boolean existsById(Integer id);

    BookingEntity updateBooking(Integer id,BookingEntity bookingEntity);

    List<BookingEntity> findAllBookings();

    Optional<BookingEntity> findBookingById(Integer id);

    void deleteById(Integer id);

}
