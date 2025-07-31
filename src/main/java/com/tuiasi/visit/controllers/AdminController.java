package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.AdminDto;
import com.tuiasi.visit.domain.entities.AdminEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.tuiasi.visit.services.AdminService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AdminController {

    private final AdminService adminService;
    private final Mapper<AdminEntity, AdminDto> adminMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public AdminController(AdminService adminService, Mapper<AdminEntity, AdminDto> adminMapper, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(path = "/admins")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto admin) {
        AdminEntity adminEntity = adminMapper.mapFrom(admin);
        AdminEntity savedAdminEntity = adminService.createAdmin(adminEntity);
        AdminDto response = adminMapper.mapTo(savedAdminEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/admins")
    public List<AdminDto> findAll() {
        List<AdminEntity> adminEntities = adminService.findAll();
        return adminEntities.stream()
                .map(adminMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/admins/{id}")
    public ResponseEntity<AdminDto> findById(@PathVariable Integer id) {
        Optional<AdminEntity> foundAdmin = adminService.findById(id);
        return foundAdmin.map(adminEntity -> {
            AdminDto response = adminMapper.mapTo(adminEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminDto loginRequest) {
        AdminEntity requestAdmin = adminMapper.mapFrom(loginRequest);
        Optional<AdminEntity> admin = adminService.findByUsername(requestAdmin.getUsername());

        if (admin.isPresent() && admin.get().getPasswordHash().equals(loginRequest.getPasswordHash())) {
            String token = jwtUtil.generateToken(admin.get().getUsername());
            return ResponseEntity.ok().body(Map.of("token", token));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

}
