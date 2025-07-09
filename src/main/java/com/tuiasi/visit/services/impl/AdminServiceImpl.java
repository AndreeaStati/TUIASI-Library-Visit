package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.AdminEntity;
import com.tuiasi.visit.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tuiasi.visit.services.AdminService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public AdminEntity createAdmin(AdminEntity adminEntity) {
        return adminRepository.save(adminEntity);
    }

    @Override
    public List<AdminEntity> findAll() {
        return StreamSupport.stream(adminRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }


}
