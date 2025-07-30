package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.domain.entities.BookingDetailsId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailsRepository extends CrudRepository<BookingDetailsEntity, BookingDetailsId> {
    List<BookingDetailsEntity> findByIdBookingId(Integer bookingId);

    List<BookingDetailsEntity> findByBookingId(Integer bookingId);
}
