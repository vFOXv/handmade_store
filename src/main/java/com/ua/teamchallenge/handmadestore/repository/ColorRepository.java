package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
