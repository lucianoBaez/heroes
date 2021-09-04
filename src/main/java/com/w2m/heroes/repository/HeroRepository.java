package com.w2m.heroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.w2m.heroes.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
   List<Hero> findByNameContains(String title);
}
