package com.food.delivery.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private BigDecimal freightRate;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Kitchen kitchen;
	
	@ManyToMany
	@JoinTable(name = "restaurant_payment_method", inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentMethods;
}
