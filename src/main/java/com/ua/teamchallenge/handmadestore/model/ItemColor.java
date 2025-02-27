package com.ua.teamchallenge.handmadestore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "items_colors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @JsonManagedReference // Эта сторона будет сериализована
    private Item item;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    private int quantity;
}
