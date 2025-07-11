package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.BookingEntity;
import com.tuiasi.visit.repositories.BookingRepository;
import com.tuiasi.visit.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookingServicelmpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingServicelmpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public BookingEntity createBooking(BookingEntity bookingEntity) {
        return bookingRepository.save(bookingEntity);
    }

    @Override
    public boolean existsById(Integer id) {
        return bookingRepository.existsById(id);
    }

    @Override
    public BookingEntity updateBooking(Integer id, BookingEntity bookingEntity) {
        bookingEntity.setId(id);
        return bookingRepository.findById(id).map(existingBoking ->{
            Optional.ofNullable(bookingEntity.getDate()).ifPresent(existingBoking::setDate);
            Optional.ofNullable(bookingEntity.getUser()).ifPresent(existingBoking::setUser);
            Optional.ofNullable(bookingEntity.getStatus()).ifPresent(existingBoking::setStatus);
            Optional.ofNullable(bookingEntity.getStartTime()).ifPresent(existingBoking::setStartTime);
            Optional.ofNullable(bookingEntity.getEndTime()).ifPresent(existingBoking::setEndTime);
            Optional.ofNullable(bookingEntity.getTotalPrice()).ifPresent(existingBoking::setTotalPrice);
            Optional.ofNullable(bookingEntity.getDetails()).ifPresent(existingBoking::setDetails);
            return bookingRepository.save(existingBoking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<BookingEntity> findAllBookings() {
        return StreamSupport.stream(bookingRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<BookingEntity> findBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        bookingRepository.deleteById(id);
    }


}
