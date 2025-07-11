package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.BookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {
}
