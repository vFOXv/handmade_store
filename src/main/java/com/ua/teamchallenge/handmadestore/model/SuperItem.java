package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "superitems")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuperItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "superitem_name")
    private String name;
    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonBackReference // Эта сторона не будет сериализована
    private Category category;
    @OneToMany(mappedBy = "superItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @PrePersist
    @PreUpdate
    //@PrePersist и @PreUpdate вызывают метод syncItemsCategory перед сохранением или обновлением SuperItem в базе данных.
    public void syncItemsCategory() {
        if (items != null && category != null) {
            for (Item item : items) {
                item.setCategory(category);
            }
        }
    }
}
