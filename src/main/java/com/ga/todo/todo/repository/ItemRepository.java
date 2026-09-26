package com.ga.todo.todo.repository;

import com.ga.todo.todo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
    List<Item> findByNameLike(String name);
    List<Item> findByCategoryId(Long categoryId);

}
