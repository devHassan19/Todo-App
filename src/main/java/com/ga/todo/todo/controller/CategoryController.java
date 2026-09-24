package com.ga.todo.todo.controller;

import com.ga.todo.todo.model.Category;
import com.ga.todo.todo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class CategoryController {

    private CategoryRepository categoryRepository;

    // CRUD
    // C - Create - HTTP POST - To create a record (category)

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("Calling createCategory ==> ");
        return categoryRepository.save(categoryObject);
    }

    // R - Read - HTTP GET - To read all records
    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("Calling getCategories() ==> ");
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        System.out.println("Calling getCategory() ==> ");
        return categoryRepository.findById(categoryId).orElse(null);
    }
    // U - Update
    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        System.out.println("Calling updateCategory() ==> ");
        Category updateCategory = categoryRepository.findById(categoryId).orElse(null);

        if (updateCategory == null) {
            return null;
        }

        updateCategory.setName(category.getName());
        updateCategory.setDescription(category.getDescription());

        return categoryRepository.save(updateCategory);
    }

    // D - Delete
    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        System.out.println("Calling deleteCategory() ==> ");
        categoryRepository.deleteById(categoryId);
    }
}