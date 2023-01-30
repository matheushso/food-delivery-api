package com.food.delivery.jpa.restaurante;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.food.delivery.domain.model.Restaurant;

public class CadastroRestaurante {

	@PersistenceContext
	private EntityManager manager;

	public List<Restaurant> listarCozinha() {
		return manager.createQuery("from Restaurante", Restaurant.class).getResultList();
	}

}
