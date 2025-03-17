package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    //    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(
//            name = "category_subcategories",
//            joinColumns = @JoinColumn(name = "subcategory_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    @JsonBackReference
//    private List<Category> categories = new ArrayList<>();
//    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
//    @JsonManagedReference // Эта сторона будет сериализована
//    private List<Item> items;
    @OneToMany(mappedBy = "subcategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference// Эта сторона будет сериализована
    private List<Category> categories;
}
