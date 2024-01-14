package com.food.delivery.domain.service;

import com.food.delivery.domain.exception.RestaurantNotFoundException;
import com.food.delivery.domain.model.Kitchen;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenService kitchenService;

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenService.findById(kitchenId);
        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }

    public void delete(Long id) {
        Restaurant restaurant = findById(id);
        restaurantRepository.delete(restaurant);
    }
}
