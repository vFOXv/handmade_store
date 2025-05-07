package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAll(Pageable pageable);

    Page<Item> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT i FROM Item i JOIN i.category c JOIN c.subcategory s WHERE s.id = :subcategoryId ")
    Page<Item> findByCategorySubcategoryId(@Param("subcategoryId") Long subcategoryId, Pageable pageable);

    Page<Item> findByCategoryIdAndCategorySubcategoryId(Long categoryId, Long subcategoryId, Pageable pageable);

    Page<Item> findByMaterialId(Long materialId, Pageable pageable);

    Page<Item> findByMaterialIdAndCategoryId(Long materialId, Long categoryId, Pageable pageable);

    Page<Item> findByMaterialIdAndCategoryIdAndCategorySubcategoryId(Long materialId, Long categoryId, Long subcategoryId, Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.createdAt = (SELECT MAX(i2.createdAt) FROM Item i2)")
    Optional<Item> findItemWithLatestCreatedAt();

    @Query("SELECT MIN(price) FROM Item")
    BigDecimal findMinPrice();

    @Query("SELECT MAX(price) FROM Item")
    BigDecimal findMaxPrice();
}
