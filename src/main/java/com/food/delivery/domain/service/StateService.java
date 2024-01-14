package com.food.delivery.domain.service;

import com.food.delivery.domain.exception.EntityInUseException;
import com.food.delivery.domain.exception.StateNotFoundException;
import com.food.delivery.domain.model.State;
import com.food.delivery.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    private static final String MSG_STATE_IN_USE = "State of id %d has cities. It can not be removed.";

    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State findById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void delete(Long id) {
        try {
            State state = findById(id);
            stateRepository.delete(state);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_STATE_IN_USE, id));
        }
    }
}
