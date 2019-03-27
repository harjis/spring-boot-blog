package com.example.springbootblog.exceptions;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(){}

    public EntityNotFound(Long id) {
        super("Could not find entity " + id);
    }
}
