package com.ua.teamchallenge.handmadestore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name= "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Item> items;
}
