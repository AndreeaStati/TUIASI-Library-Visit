package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.domain.entities.BookingDetailsId;
import com.tuiasi.visit.repositories.BookingDetailsRepository;
import com.tuiasi.visit.services.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

    private BookingDetailsRepository bookingDetailsRepository;

    public BookingDetailsServiceImpl(BookingDetailsRepository bookingDetailsRepository){
        this.bookingDetailsRepository = bookingDetailsRepository;
    }

    @Override
    public BookingDetailsEntity createUpdateBookingDetails(BookingDetailsId id, BookingDetailsEntity bookingDetails) {
        bookingDetails.setId(id);
        return bookingDetailsRepository.save(bookingDetails);
    }

    @Override
    public List<BookingDetailsEntity> findAll() {
        return StreamSupport
                .stream(
                        bookingDetailsRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExists(BookingDetailsId id) {
        return bookingDetailsRepository.existsById(id);
    }

    @Override
    public Optional<BookingDetailsEntity> findOne(BookingDetailsId id) {
        return bookingDetailsRepository.findById(id);
    }

    @Override
    public BookingDetailsEntity partialUpdate(BookingDetailsId id, BookingDetailsEntity newData) {
        BookingDetailsEntity existing = bookingDetailsRepository.findById(id).orElseThrow();

        if (newData.getNumberOfUsers() != null) {
            existing.setNumberOfUsers(newData.getNumberOfUsers());
        }

        return bookingDetailsRepository.save(existing);
    }

    @Override
    public void delete(BookingDetailsId id) {
        bookingDetailsRepository.deleteById(id);
    }

}
