package com.food.delivery.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long id) {
        this(String.format("No Kitchen with Id %d was found.", id));
    }
}
