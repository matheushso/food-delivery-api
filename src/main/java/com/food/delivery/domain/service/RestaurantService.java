package com.food.delivery.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	private static final String MSG_RESTAURANT_NOT_FOUND = "No Restaurant with Id %d was found.";

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenService kitchenService;

	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	public Restaurant findById(Long id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_RESTAURANT_NOT_FOUND, id)));
	}

	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenService.findById(kitchenId);
		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	public void delete(Long id) {
		Restaurant restaurant = findById(id);

		restaurantRepository.delete(restaurant);
	}
}
