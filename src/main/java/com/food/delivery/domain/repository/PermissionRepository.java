package com.food.delivery.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.Permission;

@Component
public interface PermissionRepository {

	List<Permission> listar();

	Permission buscar(Long id);

	Permission salvar(Permission permissao);

	void remover(Permission permissao);
}
