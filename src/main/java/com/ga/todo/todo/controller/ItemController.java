package com.ga.todo.todo.controller;

import com.ga.todo.todo.model.Item;
import com.ga.todo.todo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    //Create
    @PostMapping("/categories/{categoryId}/items")
    public Item createItem(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Item itemObject) {
        System.out.println("Calling createItem ==>");
        return itemService.createItem(categoryId, itemObject);
    }


    //READ ALL
    @GetMapping("/categories/{categoryId}/items")
    public List<Item> getItems(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("Calling getItems ==>");
        return itemService.getCategoryItems(categoryId);
    }

    //READ ONE ITEM
    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "itemId") Long itemId) {
        System.out.println("Calling getItem ==>");
        return itemService.getCategoryItems(categoryId, itemId);
    }

    //UPDATE
    @PutMapping("categories/{categoryId}/items/{itemId}")
    public Item updateItem(@PathVariable(value = "categoryId") Long categoryId,
                           @PathVariable(value = "itemId") Long itemId,
                           @RequestBody Item itemObject) {
        System.out.println("Calling updateItem ==>");
        return itemService.updateCategoryItem(categoryId, itemId, itemObject);
    }

    //DELETE
    @DeleteMapping("categories/{categoryId}/items/{itemId}")
    public ResponseEntity<HashMap<String, String>> deleteItem
    (@PathVariable(value = "categoryId") Long categoryId,
     @PathVariable(value = "itemId") Long itemId) {
        System.out.println("Calling deleteItem ==>");
        itemService.deleteCategoryitem(categoryId, itemId);
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "Item  with id: " + itemId + " was successfully deleted.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
