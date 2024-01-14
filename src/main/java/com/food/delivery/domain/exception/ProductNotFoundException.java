package com.food.delivery.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long id) {
        this(String.format("No Product with Id %d was found.", id));
    }
}
