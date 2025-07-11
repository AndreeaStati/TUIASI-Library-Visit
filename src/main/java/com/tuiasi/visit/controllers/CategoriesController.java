package com.tuiasi.visit.controllers;

import com.tuiasi.visit.domain.dto.BlockedSlotsDto;
import com.tuiasi.visit.domain.dto.CategoriesDto;
import com.tuiasi.visit.domain.entities.BlockedSlotsEntity;
import com.tuiasi.visit.domain.entities.CategoriesEntity;
import com.tuiasi.visit.mappers.Mapper;
import com.tuiasi.visit.services.BlockedSlotsService;
import com.tuiasi.visit.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CategoriesController {

    private final CategoriesService categoriesService;
    private final Mapper<CategoriesEntity, CategoriesDto> categoriesMapper;

    @Autowired
    public CategoriesController(CategoriesService categoriesService, Mapper<CategoriesEntity, CategoriesDto> categoriesMapper) {
        this.categoriesService = categoriesService;
        this.categoriesMapper = categoriesMapper;
    }

    @GetMapping(path = "/categories")
    public List<CategoriesDto> listCategories() {
        List<CategoriesEntity> categories = categoriesService.findAll();
        return categories.stream()
                .map(categoriesMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/categories/{id}")
    public ResponseEntity<CategoriesDto> getCategory(@PathVariable("id") Integer id) {
        Optional<CategoriesEntity> foundCategory = categoriesService.findOne(id);
        return foundCategory.map(categoriesEntity -> {
            CategoriesDto categoriesDto = categoriesMapper.mapTo(categoriesEntity);
            return new ResponseEntity<>(categoriesDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/categories")
    public ResponseEntity<CategoriesDto> createCategory(@RequestBody CategoriesDto category) {
        CategoriesEntity categoriesEntity = categoriesMapper.mapFrom(category);
        CategoriesEntity savedCategory = categoriesService.createCategory(categoriesEntity);
        CategoriesDto response = categoriesMapper.mapTo(savedCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/categories/{id}")
    public ResponseEntity<CategoriesDto> fullUpdateCategory(
            @PathVariable("id") Integer id,
            @RequestBody CategoriesDto categoriesDto) {

        if(!categoriesService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        categoriesDto.setId(id);
        CategoriesEntity categoryEntity = categoriesMapper.mapFrom(categoriesDto);
        CategoriesEntity savedCategory = categoriesService.save(categoryEntity);
        return new ResponseEntity<>(
                categoriesMapper.mapTo(savedCategory),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/categories/{id}")
    public ResponseEntity<CategoriesDto> partialUpdateCategory(
            @PathVariable("id") Integer id,
            @RequestBody CategoriesDto categoriesDto
    ) {
        if (!categoriesService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CategoriesEntity categoryEntity = categoriesMapper.mapFrom(categoriesDto);
        CategoriesEntity updatedCategory = categoriesService.partialUpdateCategory(id, categoryEntity);
        return new ResponseEntity<>(
                categoriesMapper.mapTo(updatedCategory),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/categories/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Integer id) {
        categoriesService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
