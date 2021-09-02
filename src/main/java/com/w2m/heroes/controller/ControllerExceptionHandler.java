package com.w2m.heroes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.w2m.heroes.exception.HeroNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

   @ResponseStatus(HttpStatus.NOT_FOUND) // 404
   @ExceptionHandler(HeroNotFoundException.class)
   public void handleNotFound(HeroNotFoundException ex) {
      log.error("Requested account not found");
   }
}
