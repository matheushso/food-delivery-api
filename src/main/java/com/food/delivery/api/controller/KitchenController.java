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
import com.food.delivery.domain.repository.KitchenRepository;
import com.food.delivery.domain.service.KitchenService;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepository;

	@Autowired
	private KitchenService kitchenService;

	@GetMapping
	private List<Kitchen> listar() {
		return kitchenRepository.listar();
	}

	@GetMapping("/{id}")
	private ResponseEntity<Kitchen> buscar(@PathVariable Long id) {
		Kitchen kitchen = kitchenRepository.buscar(id);

		if (kitchen != null) {
			return ResponseEntity.ok(kitchen);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Kitchen adicionar(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}

	@PutMapping("/{id}")
	private ResponseEntity<Kitchen> atualizar(@PathVariable Long id, @RequestBody Kitchen kitchen) {
		Kitchen kitchenAtualizar = kitchenRepository.buscar(id);

		if (kitchenAtualizar != null) {
			BeanUtils.copyProperties(kitchen, kitchenAtualizar, "id");

			kitchenAtualizar = kitchenRepository.salvar(kitchenAtualizar);
			return ResponseEntity.ok(kitchenAtualizar);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	private ResponseEntity<Kitchen> remover(@PathVariable Long id) {
		try {
			kitchenService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}
}
