package com.tuiasi.visit.services;

import com.tuiasi.visit.domain.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryEntity createCategory(CategoryEntity categoryEntity);

    public List<CategoryEntity> findAll();

    Optional<CategoryEntity> findOne(Integer id);

    boolean isExists(Integer id);

    CategoryEntity save(CategoryEntity categoryEntity);

    CategoryEntity partialUpdateCategory(Integer id, CategoryEntity categoryEntity);

    void delete(Integer id);
}
