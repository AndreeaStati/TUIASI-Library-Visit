package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.UserDto;
import com.tuiasi.visit.domain.entities.UserEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;

    @Autowired
    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity savedUserEntity = userService.createUser(userEntity);
        UserDto response = userMapper.mapTo(savedUserEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userService.findAll();
        return userEntities.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        if(!userService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity savedUserEntity = userService.updateUser(id, userEntity);
        UserDto response = userMapper.mapTo(savedUserEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id) {
        if(!userService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
