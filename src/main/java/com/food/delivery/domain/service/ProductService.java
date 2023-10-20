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

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		Product product = productRepository.findById(id).orElse(null);

		if (product == null) {
			throw new EntityNotFoundException(String.format("No Product with Id %d was found.", id));

		}

		return product;
	}

	public Product save(Product product) {
		Long restaurantId = product.getRestaurant().getId();
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

		if (restaurant == null) {
			throw new EntityNotFoundException(String.format("No Restaurant with Id %d was found.", restaurantId));
		}

		product.setRestaurant(restaurant);
		return productRepository.save(product);
	}

	public void delete(Long id) {
		Product product = findById(id);

		if (product == null) {
			throw new EntityNotFoundException(String.format("No Product with Id %d was found.", id));
		}

		productRepository.delete(product);
	}
}
