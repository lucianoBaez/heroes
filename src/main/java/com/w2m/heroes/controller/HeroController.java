package com.w2m.heroes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.heroes.entity.Hero;
import com.w2m.heroes.service.HeroService;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

   private final HeroService heroService;

   public HeroController(HeroService heroService) {
      this.heroService = heroService;
   }

   @PostMapping
   ResponseEntity<Hero> create(@RequestBody Hero hero) {
      Hero created = heroService.create(Hero.builder().name(hero.getName()).build());
      return ResponseEntity.status(HttpStatus.CREATED).body(created);
   }

   @PutMapping()
   ResponseEntity<Hero> update(@RequestBody Hero hero) {
      Hero created = heroService.update(hero);
      return ResponseEntity.status(HttpStatus.OK).body(created);
   }

   @GetMapping()
   List<Hero> all() {
      return heroService.findAll();
   }

   @GetMapping(value = "/id/{id}")
   Hero findById(@PathVariable(value = "id") Long id) {
      return heroService.findById(id);
   }

   @GetMapping(value = "/name/{name}")
   List<Hero> findById(@PathVariable(value = "name") String name) {
      return heroService.findByName(name);
   }

   @DeleteMapping("/id/{id}")
   Hero delete(@PathVariable(value = "id") Long id) {
      return heroService.delete(id);
   }
}
