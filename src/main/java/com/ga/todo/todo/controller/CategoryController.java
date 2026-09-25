package com.ga.todo.todo.controller;

import com.ga.todo.todo.model.Category;
import com.ga.todo.todo.repository.CategoryRepository;
import com.ga.todo.todo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    // CRUD
    // C - Create - HTTP POST - To create a record (category)

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("Calling createCategory ==> ");
        return categoryService.createCategory(categoryObject);
    }


    // R - Read - HTTP GET - To read all records
    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("Calling getCategories() ==> ");
        return categoryService.getCategories();
    }

    // R - Read - HTTP GET - To read One records
    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        System.out.println("Calling getCategory() ==> ");
        return categoryService.getCategory(categoryId);
    }

    // U - Update
    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        System.out.println("Calling updateCategory() ==> ");
        return categoryService.updateCategory(categoryId, category);
    }

    // D - Delete
    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        System.out.println("Calling deleteCategory() ==> ");
        categoryService.deleteCategory(categoryId);
    }
}