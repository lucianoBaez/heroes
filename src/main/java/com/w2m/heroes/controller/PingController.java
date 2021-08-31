package com.w2m.heroes.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.heroes.annotations.Loggable;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ping")
@Slf4j
public class PingController {

   @GetMapping()
   @Loggable
   public void ping() {
      log.info("Ping service");
   }

}
