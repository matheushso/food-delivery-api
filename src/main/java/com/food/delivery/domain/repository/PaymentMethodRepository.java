package com.food.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.delivery.domain.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}
