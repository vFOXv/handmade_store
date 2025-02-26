package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "styles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "style_name", unique = true, nullable = false)
    private String styleName;

    @OneToMany(mappedBy = "style", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference // Эта сторона будет сериализована
    private List<Item> items;
}
