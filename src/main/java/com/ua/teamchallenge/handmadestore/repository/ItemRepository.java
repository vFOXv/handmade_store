package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.colors")
    List<Item> findAllItems();
}
