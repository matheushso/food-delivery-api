package com.food.delivery.jpa.restaurante;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.food.delivery.domain.model.Restaurante;

public class CadastroRestaurante {

	@PersistenceContext
	private EntityManager manager;

	public List<Restaurante> listarCozinha() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

}
