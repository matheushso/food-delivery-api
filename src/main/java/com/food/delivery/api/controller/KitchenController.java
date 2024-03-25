package com.food.delivery.api.controller;

import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private Kitchen save(@RequestBody @Valid Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{id}")
    private Kitchen update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        Kitchen kitchenUpdate = kitchenService.findById(id);

        BeanUtils.copyProperties(kitchen, kitchenUpdate, "id");

        return kitchenService.save(kitchenUpdate);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }
}
