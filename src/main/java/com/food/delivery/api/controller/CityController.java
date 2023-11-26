package com.food.delivery.api.controller;

import com.food.delivery.domain.model.City;
import com.food.delivery.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    private List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    private City findById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private City save(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/{id}")
    private City update(@PathVariable Long id, @RequestBody City city) {
        City cityUpdate = cityService.findById(id);

        BeanUtils.copyProperties(city, cityUpdate, "id");

        return cityService.save(cityUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
