package com.food.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.delivery.domain.exception.EntityNotFoundException;
import com.food.delivery.domain.model.Product;
import com.food.delivery.domain.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ResponseEntity<Product> findById(@PathVariable Long id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<?> save(@RequestBody Product product) {
        try {
            product = productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product productUpdate = productService.findById(id);

            BeanUtils.copyProperties(product, productUpdate, "id");

            productUpdate = productService.save(productUpdate);
            return ResponseEntity.ok(productUpdate);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Product productActual = productService.findById(id);

        if (productActual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(fields, productActual);

        return update(id, productActual);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            productService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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
