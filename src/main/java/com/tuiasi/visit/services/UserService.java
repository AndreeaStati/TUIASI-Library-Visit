package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(UserEntity userEntity);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Integer id);

    boolean existsById(Integer id);

    UserEntity updateUser(Integer id,UserEntity userEntity);

    void deleteById(Integer id);
}
