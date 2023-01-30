package com.food.delivery.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.City;

@Component
public interface CityRepository {

	List<City> listar();

	City buscar(Long id);

	City salvar(City cidade);

	void remover(City cidade);
}
