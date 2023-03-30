package com.food.delivery.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.repository.KitchenRepository;
import com.food.delivery.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenRepository kitchenRepository;

	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	public Restaurant findById(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

		if (restaurant == null) {
			throw new EntityNotFoundException(String.format("No Restaurant with Id %d was found.", id));

		}

		return restaurant;
	}

	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId).orElse(null);
		restaurant.setKitchen(kitchen);

		if (kitchen == null) {
			throw new EntityNotFoundException(String.format("No Kitchen with Id %d was found.", kitchenId));

		}

		return restaurantRepository.save(restaurant);
	}

	public void delete(Long id) {
		Restaurant restaurant = findById(id);

		if (restaurant == null) {
			throw new EntityNotFoundException(String.format("No Restaurant with Id %d was found.", id));
		}

		restaurantRepository.delete(restaurant);
	}
}
