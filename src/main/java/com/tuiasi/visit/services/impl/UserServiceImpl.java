package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.UserEntity;
import com.tuiasi.visit.repositories.UserRepository;
import com.tuiasi.visit.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity updateUser(Integer id, UserEntity userEntity) {
        userEntity.setId(id);
        return userRepository.findById(id).map(existingUser ->{
            Optional.ofNullable(userEntity.getFirstName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(userEntity.getLastName()).ifPresent(existingUser::setLastName);
            Optional.ofNullable(userEntity.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(userEntity.getPhoneNumber()).ifPresent(existingUser::setPhoneNumber);
            return userRepository.save(existingUser);
        }).orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findOne(Integer id) {
        return userRepository.findById(id);
    }
}
