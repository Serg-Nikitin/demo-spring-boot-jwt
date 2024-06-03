package ru.nikitin.jwt.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    @ExceptionHandler(RuntimeException.class)
    public Exception handleException(RuntimeException e) {
        return new Exception(e.getClass().getSimpleName(), e);
    }
}