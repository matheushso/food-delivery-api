package com.food.delivery.api.controller;

import com.food.delivery.domain.model.State;
import com.food.delivery.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    private List<State> findAll() {
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    private State findById(@PathVariable Long id) {
        return stateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private State save(@RequestBody State state) {
        return stateService.save(state);
    }

    @PutMapping("/{id}")
    private State update(@PathVariable Long id, @RequestBody State state) {
        State stateUpdate = stateService.findById(id);

        BeanUtils.copyProperties(state, stateUpdate, "id");

        return stateService.save(stateUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        stateService.delete(id);
    }
}
