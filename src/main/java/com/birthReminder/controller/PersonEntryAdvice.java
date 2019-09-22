package com.birthReminder.controller;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonEntryAdvice {
    @ExceptionHandler({IllegalArgumentException.class, ConversionFailedException.class})
    public void handleConversionFailedException(Exception ex) {
        System.out.println("error here!");
    }
}
