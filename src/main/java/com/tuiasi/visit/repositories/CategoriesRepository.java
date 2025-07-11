package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.domain.entities.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Integer> {
}
