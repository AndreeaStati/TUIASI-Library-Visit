package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedSlotsRepository extends CrudRepository<BlockedSlotsEntity, Integer> {
}
