package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.model.ItemColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemColorRepository extends JpaRepository<ItemColor, Integer> {
    Optional<ItemColor> findById(Long id);
}
