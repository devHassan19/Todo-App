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

    // D - Delete
}