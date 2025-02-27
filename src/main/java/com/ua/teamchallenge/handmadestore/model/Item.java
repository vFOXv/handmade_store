package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Column(name = "name", unique = true, nullable = false)
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
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemColor> itemColors = new ArrayList<>();

    //Discount can't be <0% and >100%
    public void setDiscount(int discount) {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        }else{
            System.out.println("Invalid discount!!!");
        }
    }
}
