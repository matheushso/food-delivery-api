package com.food.delivery.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.food.delivery.domain.exception.EntityInUseException;
import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.State;
import com.food.delivery.domain.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public List<State> findAll() {
		return stateRepository.findAll();
	}

	public State findById(Long id) {
		State state = stateRepository.findById(id).orElse(null);

		if (state == null) {
			throw new EntityNotFoundException(String.format("No State with Id %d was found.", id));
		}

		return state;
	}

	public State save(State state) {
		return stateRepository.save(state);
	}

	public void delete(Long id) {
		try {
			stateRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("No State with Id %d was found.", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("State of id %d has cities. It can not be removed.", id));
		}

	}
}
