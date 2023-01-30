package com.food.delivery.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.State;

@Component
public interface StateRepository {

	List<State> listar();

	State buscar(Long id);

	State salvar(State estado);

	void remover(State estado);
}
