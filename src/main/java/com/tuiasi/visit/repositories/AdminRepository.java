package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
}
