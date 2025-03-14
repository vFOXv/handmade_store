package com.ua.teamchallenge.handmadestore.repository;


import com.ua.teamchallenge.handmadestore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
