package com.food.delivery.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime dateTime = LocalDateTime.now();
    private final String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
