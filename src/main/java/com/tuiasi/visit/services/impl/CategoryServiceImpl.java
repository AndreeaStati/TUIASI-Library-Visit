package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.CategoryEntity;
import com.tuiasi.visit.repositories.CategoryRepository;
import com.tuiasi.visit.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoriesRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return StreamSupport.stream(categoryRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryEntity> findOne(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity partialUpdateCategory(Integer id, CategoryEntity categoryEntity) {
        categoryEntity.setId(id);

        return categoryRepository.findById(id).map(existingCategory -> {
            Optional.ofNullable(categoryEntity.getCategoryName()).ifPresent(existingCategory::setCategoryName);
            Optional.ofNullable(categoryEntity.getPricePerPerson()).ifPresent(existingCategory::setPricePerPerson);
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Category does not exist"));
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

}
