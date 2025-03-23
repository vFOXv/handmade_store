package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "colors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String colorName;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "item_colors",
            joinColumns = @JoinColumn(name = "color_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    //@JsonManagedReference // Эта сторона будет сериализована
    //@JsonBackReference("item-colors") // Эта сторона не будет сериализована
    @JsonIgnore // Исключаем поле из сериализации/десериализации
    private List<Item> items = new ArrayList<>();
}
