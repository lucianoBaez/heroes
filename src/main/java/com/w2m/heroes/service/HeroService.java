package com.w2m.heroes.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.w2m.heroes.entity.Hero;
import com.w2m.heroes.exception.HeroNotFoundException;
import com.w2m.heroes.exception.HeroPreConditionException;
import com.w2m.heroes.repository.HeroRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@CacheConfig(cacheNames = "heroCache")
public class HeroService {

   private final HeroRepository heroRepository;

   public HeroService(HeroRepository heroRepository) {
      this.heroRepository = heroRepository;
   }

   public Hero create(Hero hero) {
      return heroRepository.save(hero);
   }

   public Hero delete(long l) {
      log.info("trying to delete hero {}", l);
      Hero hero = heroRepository.findById(l).orElseThrow(() -> new HeroNotFoundException(l));
      heroRepository.delete(hero);
      return hero;
   }

   @Cacheable
   public List<Hero> findAll() {
      log.info("finding all users");
      return heroRepository.findAll();
   }

   public Hero findById(Long id) {
      log.info("finding user with id: {}", id);
      return heroRepository.findById(id).orElseThrow(() -> new HeroNotFoundException(id));
   }

   public List<Hero> findByName(String name) {
      log.info("finding user with name: {}", name);
      if(name.isBlank()) {
         throw new HeroPreConditionException();
      }
      return heroRepository.findByNameContains(name);
   }

   public Hero update(Hero hero) {
      heroRepository.findById(hero.getId()).orElseThrow(() -> new HeroNotFoundException(hero.getId()));
      heroRepository.save(hero);
      return hero;
   }
}
