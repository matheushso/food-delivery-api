package com.food.delivery.api.controller;

import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    private List<Kitchen> findAll() {
        return kitchenService.findAll();
    }

    @GetMapping("/{id}")
    private Kitchen findById(@PathVariable Long id) {
        return kitchenService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Kitchen save(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        try {
            Kitchen kitchenUpdate = kitchenService.findById(id);

            BeanUtils.copyProperties(kitchen, kitchenUpdate, "id");

            kitchenUpdate = kitchenService.save(kitchenUpdate);
            return ResponseEntity.ok(kitchenUpdate);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }
}
