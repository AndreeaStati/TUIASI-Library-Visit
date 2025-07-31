package com.tuiasi.visit.repositories;

import com.tuiasi.visit.domain.entities.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByUsername(String username);
}
