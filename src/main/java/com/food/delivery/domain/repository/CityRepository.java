package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.City;

@Component
public interface CityRepository extends JpaRepository<City, Long> {

}
