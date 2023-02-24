package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.delivery.domain.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
