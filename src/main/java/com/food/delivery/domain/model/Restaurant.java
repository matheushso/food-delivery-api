package com.food.delivery.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

	@JsonIgnore
	@Embedded
	private Address address;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime creationDate;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime updateDate;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_payment_method", inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentMethods;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products;
}
