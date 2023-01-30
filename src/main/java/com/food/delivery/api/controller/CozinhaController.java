package com.food.delivery.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private KitchenRepository cozinhaRepository;

	@GetMapping
	private List<Kitchen> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping("/{id}")
	private ResponseEntity<Kitchen> buscar(@PathVariable Long id) {
		Kitchen cozinha = cozinhaRepository.buscar(id);

		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Kitchen adicionar(@RequestBody Kitchen cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}

	@PutMapping("/{id}")
	private ResponseEntity<Kitchen> atualizar(@PathVariable Long id, @RequestBody Kitchen cozinha) {
		Kitchen cozinhaAtualizar = cozinhaRepository.buscar(id);

		if (cozinhaAtualizar != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtualizar, "id");

			cozinhaAtualizar = cozinhaRepository.salvar(cozinhaAtualizar);
			return ResponseEntity.ok(cozinhaAtualizar);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	private ResponseEntity<Kitchen> remover(@PathVariable Long id) {
		try {
			Kitchen cozinha = cozinhaRepository.buscar(id);

			if (cozinha != null) {
				cozinhaRepository.remover(cozinha);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}
}
