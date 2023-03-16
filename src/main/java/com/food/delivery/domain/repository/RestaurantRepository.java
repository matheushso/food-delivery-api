package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.delivery.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
