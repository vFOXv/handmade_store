package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query("SELECT s FROM Subcategory s JOIN s.categories c WHERE c.id = :categoryId")
    List<Subcategory> findAllByCategoryId(@Param("categoryId") Long categoryId);
}
