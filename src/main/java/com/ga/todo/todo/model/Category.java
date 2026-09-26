package com.ga.todo.todo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // one category can contain more than one items
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
    private List<Item> itemList;

    public void setUpdateAt(LocalDateTime now) {
    }
}
//GET	/api/categories/{categoryId}/items
//POST	/api/categories/{categoryId}/items
//GET	/api/categories/{categoryId}/items/{itemId}
//PUT	/api/categories/{categoryId}/items/{itemId}
//DELETE	/api/categories/{categoryId}/items/{itemId}