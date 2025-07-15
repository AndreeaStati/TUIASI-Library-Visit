package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.domain.entities.BookingDetailsId;

import java.util.List;
import java.util.Optional;

public interface BookingDetailsService {
    BookingDetailsEntity createUpdateBookingDetails(BookingDetailsId id, BookingDetailsEntity bookingDetails);

    List<BookingDetailsEntity> findAll();

    boolean isExists(BookingDetailsId id);

    Optional<BookingDetailsEntity> findOne(BookingDetailsId id);

    BookingDetailsEntity partialUpdate(BookingDetailsId id, BookingDetailsEntity bookingDetailsEntity);

    void delete(BookingDetailsId id);
}
