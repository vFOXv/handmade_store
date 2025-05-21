package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Item;
import com.ua.teamchallenge.handmadestore.model.SuperItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperItemRepository extends JpaRepository<SuperItem, Long> {
    Page<SuperItem> findAll(Pageable pageable);
    //@Query("SELECT i FROM Item i JOIN i.category c JOIN c.subcategory s WHERE s.id = :subcategoryId ")


}
