package com.food.delivery.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Restaurant;

@Component
public interface RestaurantRepository {

	List<Restaurant> listar();

	Restaurant buscar(Long id);

	Restaurant salvar(Restaurant cozinha);

	void remover(Restaurant cozinha);
}
