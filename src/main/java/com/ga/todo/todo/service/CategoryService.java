package com.ga.todo.todo.service;

import com.ga.todo.todo.exception.InformationExistException;
import com.ga.todo.todo.exception.InformationNotFoundException;
import com.ga.todo.todo.model.Category;
import com.ga.todo.todo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    // CREATE
    public Category createCategory(Category categoryObject) {

        System.out.println("Service Calling createCategory ==> ");

        Category category =
                categoryRepository.findByName(categoryObject.getName());

        if (category != null) {
            throw new InformationExistException(
                    "category with name "
                            + categoryObject.getName()
                            + " already exists"
            );
        }

        return categoryRepository.save(categoryObject);
    }

    // READ ALL
    public List<Category> getCategories() {

        System.out.println("Service Calling getCategories() ==> ");

        return categoryRepository.findAll();
    }

    // READ ONE
    public Category getCategory(Long categoryId) {

        System.out.println("Service Calling getCategory() ==> ");

        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "category with id "
                                        + categoryId
                                        + " not found"
                        )
                );
    }

    // UPDATE
    public Category updateCategory(
            Long categoryId,
            Category category
    ) {

        System.out.println("Service Calling updateCategory() ==> ");

        Category updateCategory =
                categoryRepository.findById(categoryId)
                        .orElseThrow(() ->
                                new InformationNotFoundException(
                                        "category with id "
                                                + categoryId
                                                + " not found"
                                )
                        );

        // Check if another category has the same name
        Category existingCategory =
                categoryRepository.findByName(category.getName());

        if (existingCategory != null &&
                !existingCategory.getId().equals(categoryId)) {

            throw new InformationExistException(
                    "category with name "
                            + category.getName()
                            + " already exists"
            );
        }

        updateCategory.setName(category.getName());
        updateCategory.setDescription(category.getDescription());

        return categoryRepository.save(updateCategory);
    }

    // DELETE
    public void deleteCategory(Long categoryId) {

        System.out.println("Service Calling deleteCategory() ==> ");

        Category category =
                categoryRepository.findById(categoryId)
                        .orElseThrow(() ->
                                new InformationNotFoundException(
                                        "category with id "
                                                + categoryId
                                                + " not found"
                                )
                        );

        categoryRepository.delete(category);
    }
}

