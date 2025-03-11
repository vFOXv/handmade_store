package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name="id",insertable = false, updatable = false)
    @JsonBackReference // Эта сторона не будет сериализована
    private Category category;
    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    @JsonBackReference // Эта сторона не будет сериализована
    private Material material;
    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    @JsonBackReference // Эта сторона не будет сериализована
    private Style style;
    private Double price;
    private int discount;
//    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonBackReference // Эта сторона не будет сериализована
//    private List<ItemColor> itemColors = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "items_colors",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    //@JsonBackReference // Эта сторона не будет сериализована
    @JsonManagedReference // Эта сторона будет сериализована
    private List<Color> colors = new ArrayList<>();

    //Discount can't be <0% and >100%
    public void setDiscount(int discount) {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        }else{
            System.out.println("Invalid discount!!!");
        }
    }
}
