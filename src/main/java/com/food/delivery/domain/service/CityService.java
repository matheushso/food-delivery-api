package com.food.delivery.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.City;
import com.food.delivery.domain.model.State;
import com.food.delivery.domain.repository.CityRepository;
import com.food.delivery.domain.repository.StateRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	public List<City> findAll() {
		return cityRepository.findAll();
	}

	public City findById(Long id) {
		return cityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("No City with Id %d was found.", id)));
	}

	public City save(City city) {
		Long stateId = city.getState().getId();
		State state = stateRepository.buscar(stateId);

		if (state == null) {
			throw new EntityNotFoundException(String.format("No State with Id %d was found.", stateId));

		}

		city.setState(state);
		return cityRepository.save(city);
	}

	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("No City with Id %d was found.", id));
		}
	}
}
