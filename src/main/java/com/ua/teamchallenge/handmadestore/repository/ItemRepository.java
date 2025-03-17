package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findDistinctItems(Pageable pageable);

    Page<Item> findDistinctByCategoryId(Long categoryId, Pageable pageable);

    Page<Item> findDistinctByCategorySubcategoryId(Long categorySubcategoryId, Pageable pageable);

    Page<Item> findDistinctByCategoryIdAndCategorySubcategoryId(Long categoryId, Long categorySubcategoryId, Pageable pageable);

    Page<Item> findDistinctByMaterialId(Long materialId, Pageable pageable);

    Page<Item> findDistinctByMaterialIdAndCategoryId(Long materialId, Long categoryId, Pageable pageable);

    Page<Item> findDistinctByMaterialIdAndCategoryIdAndCategorySubcategoryId(Long materialId, Long categoryId, Long categorySubcategoryId, Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.createdAt = (SELECT MAX(i2.createdAt) FROM Item i2)")
    Optional<Item> findItemWithLatestCreatedAt();
}
