package com.w2m.heroes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.heroes.entity.Hero;

@RestController
@RequestMapping("/api")
public class HeroController {

   @GetMapping("/hero")
   List<Hero> all() {
      return null;
   }

   @PostMapping("/hero")
   ResponseEntity<Hero> create(@RequestBody Hero hero) {
      return ResponseEntity.status(HttpStatus.CREATED).body(null);
   }
}
