package com.food.delivery.domain.service;

import com.food.delivery.domain.exception.CityNotFoundException;
import com.food.delivery.domain.exception.EntityInUseException;
import com.food.delivery.domain.model.City;
import com.food.delivery.domain.model.State;
import com.food.delivery.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private static final String MSG_CITY_IN_USE = "City id %d is used and cannot be removed.";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateService stateService;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateService.findById(stateId);
        city.setState(state);

        return cityRepository.save(city);
    }

    public void delete(Long id) {
        try {
            City city = findById(id);
            cityRepository.delete(city);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_CITY_IN_USE, id));
        }
    }
}
