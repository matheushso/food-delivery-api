package com.food.delivery.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.repository.RestaurantRepository;

@Component
public class RestauranteRepositoryImpl implements RestaurantRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurant> listar() {
		return manager.createQuery("from Restaurante", Restaurant.class).getResultList();
	}

	@Override
	public Restaurant buscar(Long id) {
		return manager.find(Restaurant.class, id);
	}

	@Transactional
	@Override
	public Restaurant salvar(Restaurant restaurante) {
		return manager.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(Restaurant restaurante) {
		restaurante = buscar(restaurante.getId());
		manager.remove(restaurante);
	}
}
