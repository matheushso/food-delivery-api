package com.food.delivery.domain.service;

import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.*;
import com.food.delivery.domain.model.Product;
import com.food.delivery.domain.repository.ProductRepository;
import com.food.delivery.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	private static final String MSG_PRODUCT_NOT_FOUND = "No Product with Id %d was found.";

	private static final String MSG_RESTAURANT_IN_USE = "No Restaurant with Id %d was found.";

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_PRODUCT_NOT_FOUND, id)));
	}

	public Product save(Product product) {
		Long restaurantId = product.getRestaurant().getId();
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new EntityNotFoundException(String.format(MSG_RESTAURANT_IN_USE, restaurantId)));

		product.setRestaurant(restaurant);
		return productRepository.save(product);
	}

	public void delete(Long id) {
		Product product = findById(id);

		productRepository.delete(product);
	}
}
