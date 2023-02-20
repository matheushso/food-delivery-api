package com.food.delivery.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.food.delivery.domain.exception.EntityInUseException;
import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.service.KitchenService;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;

	@GetMapping
	private List<Kitchen> findAll() {
		return kitchenService.findAll();
	}

	@GetMapping("/{id}")
	private ResponseEntity<Kitchen> findById(@PathVariable Long id) {
		try {
			Kitchen kitchen = kitchenService.findById(id);
			return ResponseEntity.ok(kitchen);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Kitchen save(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}

	@PutMapping("/{id}")
	private ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
		try {
			Kitchen kitchenUpdate = kitchenService.findById(id);

			BeanUtils.copyProperties(kitchen, kitchenUpdate, "id");

			kitchenUpdate = kitchenService.save(kitchenUpdate);
			return ResponseEntity.ok(kitchenUpdate);

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	private ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			kitchenService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
