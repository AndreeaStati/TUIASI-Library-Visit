package com.tuiasi.visit.services.impl;

import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.domain.entities.CategoriesEntity;
import com.tuiasi.visit.repositories.BlockedSlotsRepository;
import com.tuiasi.visit.repositories.CategoriesRepository;
import com.tuiasi.visit.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesRepository categoriesRepository;

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }
    @Override
    public CategoriesEntity createCategory(CategoriesEntity categoryEntity) {
        return categoriesRepository.save(categoryEntity);
    }

    @Override
    public List<CategoriesEntity> findAll() {
        return StreamSupport.stream(categoriesRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriesEntity> findOne(Integer id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return categoriesRepository.existsById(id);
    }

    @Override
    public CategoriesEntity save(CategoriesEntity categoryEntity) {
        return categoriesRepository.save(categoryEntity);
    }

    @Override
    public CategoriesEntity partialUpdateCategory(Integer id, CategoriesEntity categoryEntity) {
        categoryEntity.setId(id);

        return categoriesRepository.findById(id).map(existingCategory -> {
            Optional.ofNullable(categoryEntity.getCategoryName()).ifPresent(existingCategory::setCategoryName);
            Optional.ofNullable(categoryEntity.getPricePerPerson()).ifPresent(existingCategory::setPricePerPerson);
            return categoriesRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Category does not exist"));
    }

    @Override
    public void delete(Integer id) {
        categoriesRepository.deleteById(id);
    }

}
