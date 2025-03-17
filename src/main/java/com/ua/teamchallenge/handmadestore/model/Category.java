package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    @Column(nullable = false)
    private String categoryName;
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(
//            name = "category_subcategories",
//            joinColumns = @JoinColumn(name = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "subcategory_id")
//    )
//    @JsonManagedReference
//    private List<Subcategory> subcategories = new ArrayList<>();

//  change @ManyToMany on @OneToMany in subcategory
    @ManyToOne
    @JoinColumn(name="subcategory_id")
    @JsonBackReference // Эта сторона не будет сериализована
    private Subcategory subcategory;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference // Эта сторона будет сериализована
    private List<Item> items;
}
