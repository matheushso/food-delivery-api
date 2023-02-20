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
import com.food.delivery.domain.model.State;
import com.food.delivery.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping
	private List<State> findAll() {
		return stateService.findAll();
	}

	@GetMapping("/{id}")
	private ResponseEntity<State> findById(@PathVariable Long id) {
		try {
			State state = stateService.findById(id);
			return ResponseEntity.ok(state);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private State save(@RequestBody State state) {
		return stateService.save(state);
	}

	@PutMapping("/{id}")
	private ResponseEntity<State> update(@PathVariable Long id, @RequestBody State state) {
		try {
			State stateUpdate = stateService.findById(id);

			BeanUtils.copyProperties(state, stateUpdate, "id");

			stateUpdate = stateService.save(stateUpdate);

			return ResponseEntity.ok(stateUpdate);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			stateService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
