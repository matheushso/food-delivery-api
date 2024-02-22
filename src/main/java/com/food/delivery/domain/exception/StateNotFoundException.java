package com.food.delivery.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long id) {
        this(String.format("No State with Id %d was found.", id));
    }
}
