package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.CategoriesEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CategoriesEntityRepositoryIntegrationTests {

    private CategoriesRepository categoriesRepository;

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository){
        this.categoriesRepository = categoriesRepository;
    }

    @Test
    public void testCategoryCanBeCreatedAndRecalled(){
        CategoriesEntity categoryEntity = TestDataUtil.createCategoryA();
        categoriesRepository.save(categoryEntity);

        Optional<CategoriesEntity> result = categoriesRepository.findById(categoryEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(categoryEntity);
    }

    @Test
    public void testMultipleCategoriesCanBeCreatedAndRecalled(){
        CategoriesEntity categoryEntityA = TestDataUtil.createCategoryA();
        CategoriesEntity categoryEntityB = TestDataUtil.createCategoryB();
        CategoriesEntity categoryEntityC = TestDataUtil.createCategoryC();

        categoriesRepository.save(categoryEntityA);
        categoriesRepository.save(categoryEntityB);
        categoriesRepository.save(categoryEntityC);

        Iterable<CategoriesEntity> result = categoriesRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .contains(categoryEntityA, categoryEntityB, categoryEntityC);
    }

    @Test
    public void testCategoryCanBeUpdatedAndRecalled(){
        CategoriesEntity categoryEntity = TestDataUtil.createCategoryA();
        categoriesRepository.save(categoryEntity);

        categoryEntity.setPricePerPerson(3.0);
        categoriesRepository.save(categoryEntity);

        Optional<CategoriesEntity> result = categoriesRepository.findById(categoryEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(categoryEntity);
    }

    @Test
    public void testCategoryCanBeDeleted(){
        CategoriesEntity categoryEntity = TestDataUtil.createCategoryA();
        categoriesRepository.save(categoryEntity);
        categoriesRepository.delete(categoryEntity);
        Optional<CategoriesEntity> result = categoriesRepository.findById(categoryEntity.getId());
        assertThat(result).isEmpty();
    }
}
