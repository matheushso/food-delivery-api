package com.food.delivery.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.food.delivery.domain.model.PaymentMethod;

@Component
public interface PaymentMethodRepository {

	List<PaymentMethod> listar();

	PaymentMethod buscar(Long id);

	PaymentMethod salvar(PaymentMethod formaPagamento);

	void remover(PaymentMethod formaPagamento);
}
