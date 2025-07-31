package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.AdminEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AdminService  {
    AdminEntity createAdmin(AdminEntity adminEntity);

    List<AdminEntity> findAll();

    Optional<AdminEntity> findById(Integer id);
    
    Optional<AdminEntity> findByUsername(String username);
}
