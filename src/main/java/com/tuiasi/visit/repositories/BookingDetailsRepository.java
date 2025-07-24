package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.BookingDetailsEntity;
import com.tuiasi.visit.domain.entities.BookingDetailsId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailsRepository extends CrudRepository<BookingDetailsEntity, BookingDetailsId> {
}
