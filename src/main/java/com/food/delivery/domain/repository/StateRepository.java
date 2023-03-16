package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.delivery.domain.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
