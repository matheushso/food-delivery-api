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

import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.City;
import com.food.delivery.domain.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping
	private List<City> findAll() {
		return cityService.findAll();
	}

	@GetMapping("/{id}")
	private ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			City city = cityService.findById(id);
			return ResponseEntity.ok(city);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private ResponseEntity<?> save(@RequestBody City city) {
		try {
			city = cityService.save(city);
			return ResponseEntity.status(HttpStatus.CREATED).body(city);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	private ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city) {
		try {
			City cityUpdate = cityService.findById(id);

			BeanUtils.copyProperties(city, cityUpdate, "id");

			cityUpdate = cityService.save(cityUpdate);
			return ResponseEntity.ok(cityUpdate);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			cityService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
