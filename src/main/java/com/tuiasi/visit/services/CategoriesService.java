package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.domain.entities.CategoriesEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {

    CategoriesEntity createCategory(CategoriesEntity categoryEntity);

    public List<CategoriesEntity> findAll();

    Optional<CategoriesEntity> findOne(Integer id);

    boolean isExists(Integer id);

    CategoriesEntity save(CategoriesEntity categoryEntity);

    CategoriesEntity partialUpdateCategory(Integer id, CategoriesEntity categoryEntity);

    void delete(Integer id);
}
