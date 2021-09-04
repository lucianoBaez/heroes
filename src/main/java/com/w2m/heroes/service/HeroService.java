package com.w2m.heroes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.w2m.heroes.entity.Hero;

@Service
public class HeroService {

   public Hero create(Hero hero) {
      return hero;
   }

   public Hero delete(long l) {
      return null;
   }

   public List<Hero> findAll() {
      return null;
   }

   public Hero findById(Long id) {
      return null;
   }

   public Hero findByName(String name) {
      return null;
   }
}
