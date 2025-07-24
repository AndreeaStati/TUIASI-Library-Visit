package com.tuiasi.visit.repositories;

import com.tuiasi.visit.TestDataUtil;
import com.tuiasi.visit.domain.entities.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
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
public class CategoryEntityRepositoryIntegrationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    public void setCategoriesRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @BeforeEach
    void clearBlockedSlots() {
        bookingDetailsRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void testCategoryCanBeCreatedAndRecalled(){
        CategoryEntity categoryEntity = TestDataUtil.createCategoryA();
        categoryRepository.save(categoryEntity);

        Optional<CategoryEntity> result = categoryRepository.findById(categoryEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(categoryEntity);
    }

    @Test
    public void testMultipleCategoriesCanBeCreatedAndRecalled(){
        CategoryEntity categoryEntityA = TestDataUtil.createCategoryA();
        CategoryEntity categoryEntityB = TestDataUtil.createCategoryB();
        CategoryEntity categoryEntityC = TestDataUtil.createCategoryC();

        categoryRepository.save(categoryEntityA);
        categoryRepository.save(categoryEntityB);
        categoryRepository.save(categoryEntityC);

        Iterable<CategoryEntity> result = categoryRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .contains(categoryEntityA, categoryEntityB, categoryEntityC);
    }

    @Test
    public void testCategoryCanBeUpdatedAndRecalled(){
        CategoryEntity categoryEntity = TestDataUtil.createCategoryA();
        categoryRepository.save(categoryEntity);

        categoryEntity.setPricePerPerson(3.0);
        categoryRepository.save(categoryEntity);

        Optional<CategoryEntity> result = categoryRepository.findById(categoryEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(categoryEntity);
    }

    @Test
    public void testCategoryCanBeDeleted(){
        CategoryEntity categoryEntity = TestDataUtil.createCategoryA();
        categoryRepository.save(categoryEntity);
        categoryRepository.delete(categoryEntity);
        Optional<CategoryEntity> result = categoryRepository.findById(categoryEntity.getId());
        assertThat(result).isEmpty();
    }
}
