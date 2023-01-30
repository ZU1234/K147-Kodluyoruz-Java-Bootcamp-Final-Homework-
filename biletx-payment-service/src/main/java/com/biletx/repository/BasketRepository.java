package com.biletx.repository;

import com.biletx.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    @Query(value = "Select * from baskets where user_id=?", nativeQuery = true)
    List<Basket> findAllByUserId(Integer userId);
    @Query(value = "SELECT COUNT(*) from baskets where user_id=?", nativeQuery = true)
    Integer countFindAllByUserId(Integer userId);

    @Query(value = "DELETE FROM baskets WHERE user_id=?",nativeQuery = true)
    void deleteAllByUserId(Integer userId);

}

