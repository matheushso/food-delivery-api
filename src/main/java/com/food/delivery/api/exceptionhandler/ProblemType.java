package com.food.delivery.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    BUSINESS_ERROR("/business-error", "Business rule violation");

    private final String title;
    private final String uri;
    private static final String URL = "https://fooddelivery.com.br";

    ProblemType(String path, String title) {
        this.uri = URL + path;
        this.title = title;
    }
}
