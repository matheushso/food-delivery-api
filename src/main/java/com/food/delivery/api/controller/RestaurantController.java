package com.food.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    private List<Restaurant> findAll() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    private Restaurant findById(@PathVariable Long id) {
        return restaurantService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Restaurant save(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

    @PutMapping("/{id}")
    private Restaurant update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant restaurantUpdate = restaurantService.findById(id);

        BeanUtils.copyProperties(restaurant, restaurantUpdate, "id", "paymentMethods", "address", "creationDate");

        return restaurantService.save(restaurantUpdate);
    }

    @PatchMapping("/{id}")
    public Restaurant partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Restaurant restaurantActual = restaurantService.findById(id);

        merge(fields, restaurantActual);

        return update(id, restaurantActual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    private void merge(Map<String, Object> sourceFields, Restaurant destinationRestaurant) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantOrigin = objectMapper.convertValue(sourceFields, Restaurant.class);

        sourceFields.forEach((name, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, name);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restaurantOrigin);

            System.out.println(name + " = " + value + " = " + newValue);

            ReflectionUtils.setField(field, destinationRestaurant, newValue);
        });
    }
}
