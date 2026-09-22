package com.ga.todo.todo.repository;

import com.ga.todo.todo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
    Category findByNameAndDescription(String name, String desc);
}
