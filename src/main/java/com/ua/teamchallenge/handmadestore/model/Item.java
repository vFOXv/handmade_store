package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonBackReference("item-category") // Эта сторона не будет сериализована
    private Category category;
    @ManyToOne
    @JoinColumn(name="material_id")
    @JsonBackReference("item-material") // Эта сторона не будет сериализована
    private Material material;
    private BigDecimal price;
    private int quantity;
    private int discount;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "items_colors",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    //@JsonBackReference // Эта сторона не будет сериализована
    //@JsonManagedReference("item-colors") // Эта сторона будет сериализована
    private List<Color> colors = new ArrayList<>();
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="superitem_id")
    @JsonBackReference // Эта сторона не будет сериализована
    private SuperItem superItem;


    //Discount can't be <0% and >100%
    public void setDiscount(int discount) {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        }else{
            System.out.println("Invalid discount!!!");
        }
    }

    //при добавлении item или изменении price, меняеться min price in superItem.
    @PrePersist
    @PreUpdate
    public void syncSuperItemMinPrice() {
        if (superItem != null) {
            superItem.updateIdItemMinPrice();
        }
    }
}

