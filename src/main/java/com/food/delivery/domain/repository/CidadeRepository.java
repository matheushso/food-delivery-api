package com.food.delivery.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Cidade;

@Component
public interface CidadeRepository {

	List<Cidade> listar();

	Cidade buscar(Long id);

	Cidade salvar(Cidade cidade);

	void remover(Cidade cidade);
}
