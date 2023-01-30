package com.food.delivery.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.State;
import com.food.delivery.domain.repository.StateRepository;

@Component
public class EstadoRepositoryImpl implements StateRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<State> listar() {
		return manager.createQuery("from State", State.class).getResultList();
	}

	@Override
	public State buscar(Long id) {
		return manager.find(State.class, id);
	}

	@Transactional
	@Override
	public State salvar(State estado) {
		return manager.merge(estado);
	}

	@Transactional
	@Override
	public void remover(State estado) {
		estado = buscar(estado.getId());
		manager.remove(estado);
	}

}
