package com.food.delivery.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.repository.KitchenRepository;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Kitchen> listar() {
		return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
	}

	@Override
	public Kitchen buscar(Long id) {
		return manager.find(Kitchen.class, id);
	}

	@Transactional
	@Override
	public Kitchen salvar(Kitchen cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Kitchen kitchen = buscar(id);

		if (kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(kitchen);
	}
}
