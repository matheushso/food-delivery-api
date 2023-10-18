package com.food.delivery.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	private List<Restaurant> findAll() {
		return restaurantService.findAll();
	}

	@GetMapping("/{id}")
	private ResponseEntity<Restaurant> findById(@PathVariable Long id) {
		try {
			Restaurant restaurant = restaurantService.findById(id);
			return ResponseEntity.ok(restaurant);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	private ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.save(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	private ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		try {
			Restaurant restaurantUpdate = restaurantService.findById(id);

			BeanUtils.copyProperties(restaurant, restaurantUpdate, "id", "paymentMethods", "address", "creationDate");

			restaurantUpdate = restaurantService.save(restaurantUpdate);
			return ResponseEntity.ok(restaurantUpdate);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Restaurant restaurantActual = restaurantService.findById(id);

		if (restaurantActual == null) {
			return ResponseEntity.notFound().build();
		}

		merge(fields, restaurantActual);

		return update(id, restaurantActual);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			restaurantService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	private void merge(Map<String, Object> sourceFields, Restaurant destinationRestaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant restaurantOrigin = objectMapper.convertValue(sourceFields, Restaurant.class);

		sourceFields.forEach((name, value) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, name);
			field.setAccessible(true);

			Object newValue = ReflectionUtils.getField(field, restaurantOrigin);

			System.out.println(name + " = " + value + " = " + newValue);

			ReflectionUtils.setField(field, destinationRestaurant, newValue);
		});
	}
}
