package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
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
    @JoinColumn(name = "category_id")
    @JsonBackReference // Эта сторона не будет сериализована
    private Category category;
    @OneToMany(mappedBy = "superItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
    @Column(name="id_item_min_price")
    private Long idItemMinPrice;

    @PrePersist
    @PreUpdate
    //@PrePersist и @PreUpdate вызывают метод syncItemsCategory перед сохранением или обновлением SuperItem в базе данных.
    //устанавливает category in item from superitem
    public void syncItemsCategory() {
        if (items != null && category != null) {
            for (Item item : items) {
                item.setCategory(category);
            }
        }
        updateIdItemMinPrice();
    }

    public void updateIdItemMinPrice() {
        if (items != null && !items.isEmpty()) {
            Item minPriceItem = items.stream()
                    .filter(item -> item.getPrice() != null)
                    .min(Comparator.comparing(Item::getPrice))
                    .orElse(null);
            this.idItemMinPrice = (minPriceItem != null) ? minPriceItem.getId() : null;
        } else {
            this.idItemMinPrice = null;
        }
    }
}




