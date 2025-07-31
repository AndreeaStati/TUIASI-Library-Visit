package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.domain.entities.BookingDetailsId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailsRepository extends CrudRepository<BookingDetailsEntity, BookingDetailsId> {
    List<BookingDetailsEntity> findByIdBookingId(Integer bookingId);

    List<BookingDetailsEntity> findByBookingId(Integer bookingId);

    @Query(value =
            "SELECT " +

                    "b.booking_date as bookingDate, " +
                    "b.start_time as startTime, " +
                    "b.end_time as endTime, " +
                    "SUM(bd.number_of_users) as totalUsers " +
            "FROM bookings b " +
            "JOIN booking_details bd ON b.booking_id = bd.booking_id " +
            "WHERE b.booking_date = :date " +
            "GROUP BY  b.booking_date, b.start_time, b.end_time",
            nativeQuery = true)
    List<Object[]> findBookingSummaryByDate(@Param("date") java.sql.Date date);

    @Query(value =
            "SELECT " +
                    "b.booking_id, " +
                    "b.booking_date as bookingDate, " +
                    "b.start_time as startTime, " +
                    "b.end_time as endTime, " +
                    "SUM(bd.number_of_users) as totalUsers " +
            "FROM bookings b " +
            "JOIN booking_details bd ON b.booking_id = bd.booking_id " +
            "GROUP BY b.booking_id, b.booking_date, b.start_time, b.end_time",
            nativeQuery = true)
    List<Object[]> findBookingSummaryAll();


}
