package com.ua.teamchallenge.handmadestore.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    @JoinColumn(name="id")
    private Category category;
    @ManyToOne
    @JoinColumn(name="id")
    private Material material;
    @ManyToOne
    @JoinColumn(name="id")
    private Style style;
    private Double price;
    private int discount;

    //Discount can't be <0% and >100%
    public void setDiscount(int discount) {
        if (discount >= 0 && discount <= 100) {
            this.discount = discount;
        }else{
            System.out.println("Invalid discount!!!");
        }
    }
}
