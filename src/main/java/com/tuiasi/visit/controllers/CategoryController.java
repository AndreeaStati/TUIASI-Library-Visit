package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.CategoryDto;
import com.tuiasi.visit.domain.entities.CategoryEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final Mapper<CategoryEntity, CategoryDto> categoriesMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoriesMapper) {
        this.categoryService = categoryService;
        this.categoriesMapper = categoriesMapper;
    }

    @GetMapping(path = "/categories")
    public List<CategoryDto> listCategories() {
        List<CategoryEntity> categories = categoryService.findAll();
        return categories.stream()
                .map(categoriesMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Integer id) {
        Optional<CategoryEntity> foundCategory = categoryService.findOne(id);
        return foundCategory.map(categoriesEntity -> {
            CategoryDto categoryDto = categoriesMapper.mapTo(categoriesEntity);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
        CategoryEntity categoryEntity = categoriesMapper.mapFrom(category);
        CategoryEntity savedCategory = categoryService.createCategory(categoryEntity);
        CategoryDto response = categoriesMapper.mapTo(savedCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryDto> fullUpdateCategory(
            @PathVariable("id") Integer id,
            @RequestBody CategoryDto categoryDto) {

        if(!categoryService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        categoryDto.setId(id);
        CategoryEntity categoryEntity = categoriesMapper.mapFrom(categoryDto);
        CategoryEntity savedCategory = categoryService.save(categoryEntity);
        return new ResponseEntity<>(
                categoriesMapper.mapTo(savedCategory),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryDto> partialUpdateCategory(
            @PathVariable("id") Integer id,
            @RequestBody CategoryDto categoryDto
    ) {
        if (!categoryService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CategoryEntity categoryEntity = categoriesMapper.mapFrom(categoryDto);
        CategoryEntity updatedCategory = categoryService.partialUpdateCategory(id, categoryEntity);
        return new ResponseEntity<>(
                categoriesMapper.mapTo(updatedCategory),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/categories/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
