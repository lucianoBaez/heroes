package com.w2m.heroes.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.mapstruct.factory.Mappers;
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

import com.w2m.heroes.annotations.Loggable;
import com.w2m.heroes.dto.HeroDto;
import com.w2m.heroes.dto.mapper.HeroMapper;
import com.w2m.heroes.entity.Hero;
import com.w2m.heroes.service.HeroService;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

   private final HeroService heroService;

   private final HeroMapper mapper = Mappers.getMapper(HeroMapper.class);

   public HeroController(HeroService heroService) {
      this.heroService = heroService;
   }

   @PostMapping
   @Loggable
   ResponseEntity<HeroDto> create(@RequestBody HeroDto hero) {
      Hero created = heroService.create(mapper.fromDto(hero));
      return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
   }

   @PutMapping()
   ResponseEntity<HeroDto> update(@RequestBody Hero hero) {
      Hero created = heroService.update(hero);
      return ResponseEntity.status(HttpStatus.OK).body(mapper.toDto(created));
   }

   @GetMapping()
   @Loggable
   List<HeroDto> all() {
      List<Hero> heroes = heroService.findAll();
      return heroes.stream().map(mapper::toDto).collect(toList());
   }

   @GetMapping(value = "/id/{id}")
   HeroDto findById(@PathVariable(value = "id") Long id) {
      return mapper.toDto(heroService.findById(id));
   }

   @GetMapping(value = "/name/{name}")
   List<HeroDto> findById(@PathVariable(value = "name") String name) {
      List<Hero> heroes = heroService.findByName(name);
      return heroes.stream().map(mapper::toDto).collect(toList());
   }

   @DeleteMapping("/id/{id}")
   HeroDto delete(@PathVariable(value = "id") Long id) {
      return mapper.toDto(heroService.delete(id));
   }
}
