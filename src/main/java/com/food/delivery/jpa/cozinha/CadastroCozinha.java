package com.food.delivery.jpa.cozinha;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Kitchen;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager manager;

	public List<Kitchen> listarCozinha() {
		return manager.createQuery("from Cozinha", Kitchen.class).getResultList();
	}

}
