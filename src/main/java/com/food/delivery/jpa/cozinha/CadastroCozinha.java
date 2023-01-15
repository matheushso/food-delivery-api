package com.food.delivery.jpa.cozinha;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> listarCozinha() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

}
