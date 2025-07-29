package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.BookingDto;
import com.tuiasi.visit.domain.entities.BookingEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class BookingController {

    private final BookingService bookingService;
    private final Mapper<BookingEntity, BookingDto> bookingMapper;

    @Autowired
    public BookingController(BookingService bookingService, Mapper<BookingEntity, BookingDto> bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @PostMapping(path = "/bookings")
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        BookingEntity bookingEntity = bookingMapper.mapFrom(bookingDto);
        BookingEntity savedBookingEntity = bookingService.createBooking(bookingEntity);
        return new ResponseEntity<>(bookingMapper.mapTo(savedBookingEntity),HttpStatus.CREATED);
    }

    @GetMapping(path = "/bookings")
    public List<BookingDto> findAllBookings() {
        List<BookingEntity> bookings = bookingService.findAllBookings();
        return bookings.stream()
                .map(bookingMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/bookings/{id}")
    public ResponseEntity<BookingDto> findBookingById(@PathVariable Integer id) {
        Optional<BookingEntity> bookingEntity = bookingService.findBookingById(id);
        return bookingEntity.map(bookingEntity1 -> {
            BookingDto bookingDto = bookingMapper.mapTo(bookingEntity1);
            return new ResponseEntity<>(bookingMapper.mapTo(bookingEntity1), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/bookings/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Integer id, @RequestBody BookingDto bookingDto) {
        if(!bookingService.existsById(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookingEntity bookingEntity = bookingMapper.mapFrom(bookingDto);
        BookingEntity savedBookingEntity = bookingService.updateBooking(id, bookingEntity);
        return new ResponseEntity<>(bookingMapper.mapTo(savedBookingEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/bookings/{id}")
    public ResponseEntity deleteBooking(@PathVariable Integer id) {
        bookingService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
