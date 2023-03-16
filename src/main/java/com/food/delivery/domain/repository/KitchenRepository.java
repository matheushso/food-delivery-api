package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.delivery.domain.model.Kitchen;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

}
