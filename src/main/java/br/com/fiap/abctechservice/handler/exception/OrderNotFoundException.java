package br.com.fiap.abctechservice.handler.exception;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {
    private final String description;

    public OrderNotFoundException(String message, String description){
        super(message);
        this.description = description;
    }
}