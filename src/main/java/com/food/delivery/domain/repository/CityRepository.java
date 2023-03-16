package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.delivery.domain.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
