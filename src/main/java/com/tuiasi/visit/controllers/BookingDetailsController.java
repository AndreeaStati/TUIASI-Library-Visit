package com.tuiasi.visit.controllers;


import com.tuiasi.visit.domain.dto.BookingDetailsDto;
import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.domain.entities.BookingDetailsId;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.services.BookingDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class BookingDetailsController {

    private BookingDetailsService bookingDetailsService;
    private Mapper<BookingDetailsEntity, BookingDetailsDto> bookingDetailsMapper;

    public BookingDetailsController(Mapper<BookingDetailsEntity, BookingDetailsDto> bookingDetailsMapper, BookingDetailsService bookingDetailsService){
        this.bookingDetailsMapper = bookingDetailsMapper;
        this.bookingDetailsService = bookingDetailsService;
    }

    @PutMapping(path = "/booking-details/{bookingId}/{categoryId}")
    public ResponseEntity<BookingDetailsDto> createUpdateBookingDetails(
            @PathVariable Integer bookingId,
            @PathVariable Integer categoryId,
            @RequestBody BookingDetailsDto bookingDetailsDto)
    {
        BookingDetailsEntity bookingDetailsEntity = bookingDetailsMapper.mapFrom(bookingDetailsDto);
        BookingDetailsId id = new BookingDetailsId(bookingId, categoryId);
        bookingDetailsEntity.setId(id);

        boolean bookingDetailsExists = bookingDetailsService.isExists(id);
        BookingDetailsEntity savedBookingDetailsEntity = bookingDetailsService.createUpdateBookingDetails(id, bookingDetailsEntity);
        BookingDetailsDto savedUpdatedBookingDetailsDto = bookingDetailsMapper.mapTo(savedBookingDetailsEntity);

        if (bookingDetailsExists){
            return new ResponseEntity<>(savedUpdatedBookingDetailsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedUpdatedBookingDetailsDto, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/booking-details")
    public List<BookingDetailsDto> listBooks() {
        List<BookingDetailsEntity> bookingDetailsEntities = bookingDetailsService.findAll();
        return bookingDetailsEntities.stream()
                .map(bookingDetailsMapper::mapTo)
                .collect(Collectors.toList());
    }


    @GetMapping("/booking-details/{bookingId}/{categoryId}")
    public ResponseEntity<BookingDetailsDto> getBookingDetails(@PathVariable Integer bookingId,
                                                               @PathVariable Integer categoryId) {

        BookingDetailsId id = new BookingDetailsId(bookingId, categoryId);
        Optional<BookingDetailsEntity> found = bookingDetailsService.findOne(id);

        return found
                .map(entity -> new ResponseEntity<>(bookingDetailsMapper.mapTo(entity), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/booking-details/{bookingId}/{categoryId}")
    public ResponseEntity<BookingDetailsDto> partialUpdateBookingDetails(
            @PathVariable Integer bookingId,
            @PathVariable Integer categoryId,
            @RequestBody BookingDetailsDto bookingDetailsDto
    ) {
        BookingDetailsId id = new BookingDetailsId(bookingId, categoryId);

        if (!bookingDetailsService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookingDetailsEntity bookingDetailsEntity = bookingDetailsMapper.mapFrom(bookingDetailsDto);
        bookingDetailsEntity.setId(id);

        BookingDetailsEntity updatedBookingDetailsEntity = bookingDetailsService.partialUpdate(id, bookingDetailsEntity);

        return new ResponseEntity<>(
                bookingDetailsMapper.mapTo(updatedBookingDetailsEntity),
                HttpStatus.OK
        );
    }


    @DeleteMapping(path = "/booking-details/{bookingId}/{categoryId}")
    public ResponseEntity deleteBookingDetails(
            @PathVariable Integer bookingId,
            @PathVariable Integer categoryId
    ) {
        BookingDetailsId id = new BookingDetailsId(bookingId, categoryId);

        if (!bookingDetailsService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookingDetailsService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/booking-details/{bookingId}")
    public ResponseEntity<List<BookingDetailsDto>> getBookingDetailsByBookingId(@PathVariable Integer bookingId) {
        List<BookingDetailsEntity> bookingDetailsEntities = bookingDetailsService.findByBookingId(bookingId);
        List<BookingDetailsDto> dtos = bookingDetailsEntities.stream()
                .map(bookingDetailsMapper::mapTo)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/booking-details/{bookingId}")
    public ResponseEntity<List<BookingDetailsDto>> updateBookingDetailsByBookingId(
            @PathVariable Integer bookingId,
            @RequestBody List<BookingDetailsDto> updatedDetails) {

        List<BookingDetailsEntity> updatedEntities = updatedDetails.stream()
                .map(bookingDetailsMapper::mapFrom)
                .peek(e -> e.setId(new BookingDetailsId(bookingId, e.getId().getCategoryId())))
                .collect(Collectors.toList());

        List<BookingDetailsEntity> savedEntities = bookingDetailsService.updateAllByBookingId(bookingId, updatedEntities);

        List<BookingDetailsDto> savedDtos = savedEntities.stream()
                .map(bookingDetailsMapper::mapTo)
                .collect(Collectors.toList());

        return new ResponseEntity<>(savedDtos, HttpStatus.OK);
    }

}
