package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.domain.entities.BookingDetailsId;
import com.tuiasi.visit.repositories.BookingDetailsRepository;
import com.tuiasi.visit.services.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public List<BookingDetailsEntity> findByBookingId(Integer bookingId) {
        return bookingDetailsRepository.findByIdBookingId(bookingId);
    }



    @Override
    public List<BookingDetailsEntity> updateAllByBookingId(Integer bookingId, List<BookingDetailsEntity> newDetails) {
        List<BookingDetailsEntity> existing = bookingDetailsRepository.findByBookingId(bookingId);
        bookingDetailsRepository.deleteAll(existing);

        Iterable<BookingDetailsEntity> savedIterable = bookingDetailsRepository.saveAll(newDetails);
        // convertește Iterable în List
        List<BookingDetailsEntity> savedList = StreamSupport.stream(savedIterable.spliterator(), false)
                .collect(Collectors.toList());
        return savedList;
    }

    @Override
    public List<Map<String, Object>> getBookingDetailsSummaryByDate(LocalDate date) {
        List<Object[]> results = bookingDetailsRepository.findBookingSummaryByDate(java.sql.Date.valueOf(date));

        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("bookingDate", record[0]);
            map.put("startTime", record[1]);
            map.put("endTime", record[2]);
            map.put("totalUsers", record[3]);
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getBookingDetailsSummaryAll() {
        List<Object[]> results = bookingDetailsRepository.findBookingSummaryAll();

        return results.stream().map(record -> {
            Map<String, Object> map = new HashMap<>();
            map.put("bookingId", record[0]);
            map.put("bookingDate", record[1]);
            map.put("startTime", record[2]);
            map.put("endTime", record[3]);
            map.put("totalUsers", record[4]);
            return map;
        }).collect(Collectors.toList());
    }


}
