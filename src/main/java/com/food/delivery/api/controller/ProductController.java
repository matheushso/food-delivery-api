package com.food.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.delivery.domain.model.Product;
import com.food.delivery.domain.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    private List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    private Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    private Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    private Product update(@PathVariable Long id, @RequestBody Product product) {
        Product productUpdate = productService.findById(id);

        BeanUtils.copyProperties(product, productUpdate, "id");

        return productService.save(productUpdate);
    }

    @PatchMapping("/{id}")
    public Product partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Product productActual = productService.findById(id);

        merge(fields, productActual);

        return update(id, productActual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    private void merge(Map<String, Object> sourceFields, Product destinationProduct) {
        ObjectMapper objectMapper = new ObjectMapper();
        Product productOrigin = objectMapper.convertValue(sourceFields, Product.class);

        sourceFields.forEach((name, value) -> {
            Field field = ReflectionUtils.findField(Product.class, name);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, productOrigin);

            System.out.println(name + " = " + value + " = " + newValue);

            ReflectionUtils.setField(field, destinationProduct, newValue);
        });
    }
}
