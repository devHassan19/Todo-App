package com.ga.todo.todo.service;

import com.ga.todo.todo.exception.InformationNotFoundException;
import com.ga.todo.todo.model.Category;
import com.ga.todo.todo.model.Item;
import com.ga.todo.todo.repository.CategoryRepository;
import com.ga.todo.todo.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    public Item createItem(Long categoryId, Item item) {
        System.out.println("Service Calling createRecipe ==>");
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new InformationNotFoundException(
                        "Category with id " + categoryId + " not found"
                ));
        item.setCategory(category);
        return itemRepository.save(item);
    }


    public List<Item> getCategoryItems(Long categoryId) {
        System.out.println("Service Calling getCategoryItems ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category.get().getItemList();
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    //READ ONE of Item
    public Item getCategoryItems(Long categortId, Long itemId) {
        System.out.println("Service Calling getCategoryItems =>");
        Optional<Category> category = categoryRepository.findById(categortId);

        if (category.isPresent()) {
            Optional<Item> item = itemRepository.findById(itemId);
            if (item.isPresent()) {
                return item.get();
            }
            if (item.isEmpty()) {
                throw new InformationNotFoundException("Item with id " + itemId + " not found");
            }
        }
        return itemRepository.findById(itemId).orElseThrow(() -> new InformationNotFoundException("Recipe with id " + itemId + " not found"));
    }

    //UPDATE
    public Item updateCategoryItem(Long categoryId, Long itemId, Item itemObject) {
        System.out.println("service calling updateCategoryItem ==>");
        try {
            Item item = (itemRepository.findByCategoryId(categoryId).stream().filter(i -> i.getId().equals(itemId)).findFirst()).get();
            item.setName(itemObject.getName());
            item.setDueDate(itemObject.getDueDate());
            return itemRepository.save(item);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("item or category not found");
        }
    }

    //    DELETE
    public void deleteCategoryitem(Long categoryId, Long itemId) {
        try {
            Item item = (itemRepository.findByCategoryId(categoryId).stream().filter(i -> i.getId().equals(itemId)).findFirst()).get();
            itemRepository.deleteById(item.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("item or category not found");
        }
    }

}
