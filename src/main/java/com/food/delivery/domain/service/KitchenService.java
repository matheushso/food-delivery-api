package com.food.delivery.domain.service;

import com.food.delivery.domain.exception.EntityInUseException;
import com.food.delivery.domain.exception.KitchenNotFoundException;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {

    private static final String MSG_KITCHEN_IN_USE = "Kitchen of id %d has restaurants. It can not be removed.";

    @Autowired
    private KitchenRepository kitchenRepository;

    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));
    }

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void delete(Long id) {
        try {
            Kitchen kitchen = findById(id);
            kitchenRepository.delete(kitchen);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_KITCHEN_IN_USE, id));
        }
    }
}
