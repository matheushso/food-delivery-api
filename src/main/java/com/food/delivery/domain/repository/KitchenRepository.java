package com.food.delivery.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Kitchen;

@Component
public interface KitchenRepository {

	List<Kitchen> listar();

	Kitchen buscar(Long id);

	Kitchen salvar(Kitchen cozinha);

	void delete(Long id);
}
