package com.ua.teamchallenge.handmadestore.repository;

import com.ua.teamchallenge.handmadestore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
//    @Query("select i.id, i.name, i.description, i.category, i.material, i.style, i.price, i.discount, c.colorName, string_agg(c.colorName, ', ') AS colors \n" +
//            "from Item as i left join items_colors as ic on i.id=ic.item_id left join Color as c on ic.color_id=c.id \n" +
//            "GROUP BY  i.id, i.name, i.description, i.price, i.discount")
    @Query("SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.colors")
    List<Item> findAllItems();

    Optional<Item> findById(Long id);

}
