package com.food.delivery.domain.service;

import com.food.delivery.domain.exception.ProductNotFoundException;
import com.food.delivery.domain.model.Product;
import com.food.delivery.domain.model.Restaurant;
import com.food.delivery.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product save(Product product) {
        Long restaurantId = product.getRestaurant().getId();
        Restaurant restaurant = restaurantService.findById(restaurantId);

        product.setRestaurant(restaurant);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
