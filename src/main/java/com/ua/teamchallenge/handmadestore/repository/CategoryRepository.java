package com.ua.teamchallenge.handmadestore.repository;


import com.ua.teamchallenge.handmadestore.model.Category;
import com.ua.teamchallenge.handmadestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
}
