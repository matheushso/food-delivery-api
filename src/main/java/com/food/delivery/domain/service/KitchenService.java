package com.food.delivery.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.food.delivery.domain.exception.EntityInUseException;
import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.repository.KitchenRepository;

@Service
public class KitchenService {

	@Autowired
	private KitchenRepository kitchenRepository;

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.salvar(kitchen);
	}

	public void delete(Long id) {
		try {
			kitchenRepository.delete(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("No Kitchen with Id %d was found", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Kitchen of id %d has restaurants. It can not be removed.", id));
		}
	}

}
