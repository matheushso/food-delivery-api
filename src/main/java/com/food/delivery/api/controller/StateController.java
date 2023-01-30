package com.food.delivery.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.delivery.domain.model.State;
import com.food.delivery.domain.repository.StateRepository;

@RestController
@RequestMapping("/estados")
public class StateController {

	@Autowired
	private StateRepository estadoRepository;

	@GetMapping
	private List<State> listar() {
		return estadoRepository.listar();
	}
}
