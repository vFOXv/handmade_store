package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
//    @Query("SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.colors")
    @Query("SELECT DISTINCT i FROM Item i")
    List<Item> findAllItems();

//    @Query("SELECT i FROM Item i WHERE i.category.id = :categoryId")
//    List<Item> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT i FROM Item i WHERE i.createdAt = (SELECT MAX(i2.createdAt) FROM Item i2)")
    Item findItemWithLatestCreatedAt();
}
