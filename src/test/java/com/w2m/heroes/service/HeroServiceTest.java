package com.w2m.heroes.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.w2m.heroes.entity.Hero;
import com.w2m.heroes.exception.HeroNotFoundException;
import com.w2m.heroes.repository.HeroRepository;

public class HeroServiceTest {

   private HeroService service;

   private HeroRepository repository;

   @BeforeEach
   public void initEach() {
      repository = Mockito.mock(HeroRepository.class);
      service = new HeroService(repository);
   }

   @Test
   public void test_findAll() {
      List<Hero> list = Stream
            .of(Hero.builder().id(1L).name("myHero").build(), Hero.builder().id(2L).name("myHero").build())
            .collect(Collectors.toList());

      when(repository.findAll()).thenReturn(list);

      List<Hero> all = service.findAll();
      assertThat(all).isNotNull();
      assertThat(all.size()).isEqualTo(2);
   }

   @Test
   public void test_add_hero() {

      when(repository.save(any())).thenReturn(Hero.builder().id(1L).name("hero").build());
      Hero hero = service.create(Hero.builder().name("hero").build());

      assertThat(hero).isNotNull();
      assertThat(hero.getId()).isEqualTo(1L);
   }

   @Test
   public void test_update_existe_hero() {

      Hero hero1 = Hero.builder().id(1L).name("hero").build();
      when(repository.findById(1L)).thenReturn(Optional.of(hero1));

      when(repository.save(any())).thenReturn(Hero.builder().id(1L).name("hero").build());
      Hero hero = service.update(Hero.builder().id(1L).name("hero").build());

      assertThat(hero).isNotNull();
      assertThat(hero.getId()).isEqualTo(1L);
   }

   @Test
   public void test_update_not_existe_hero() {

      Hero hero1 = Hero.builder().id(1L).name("hero").build();
      when(repository.findById(1L)).thenReturn(Optional.empty());

      assertThatThrownBy(() -> service.update(hero1))
            .isExactlyInstanceOf(HeroNotFoundException.class);
   }

   @Test
   public void test_delete_hero_exist() {

      Hero hero1 = Hero.builder().id(1L).name("hero").build();
      when(repository.findById(1L)).thenReturn(Optional.of(hero1));

      Hero heroDeleted = service.delete(1L);

      assertThat(heroDeleted).isNotNull();
      assertThat(heroDeleted.getId()).isEqualTo(hero1.getId());
   }

   @Test
   public void test_delete_hero_dont_exist() {
      when(repository.findById(1L)).thenReturn(Optional.empty());
      assertThatThrownBy(() -> service.delete(1L))
            .isExactlyInstanceOf(HeroNotFoundException.class);
   }

   @Test
   public void test_find_hero_byId_exist() {

      Hero hero1 = Hero.builder().id(1L).name("hero").build();
      when(repository.findById(1L)).thenReturn(Optional.of(hero1));

      Hero heroDeleted = service.findById(1L);

      assertThat(heroDeleted).isNotNull();
      assertThat(heroDeleted.getId()).isEqualTo(hero1.getId());
   }

   @Test
   public void test_find_hero_byId_dont_exist() {
      when(repository.findById(1L)).thenReturn(Optional.empty());
      assertThatThrownBy(() -> service.findById(1L))
            .isExactlyInstanceOf(HeroNotFoundException.class);
   }

   @Test
   public void test_find_hero_byName_exist() {

      List<Hero> list = Stream
            .of(Hero.builder().id(1L).name("myHero").build(), Hero.builder().id(2L).name("myHero").build())
            .collect(Collectors.toList());

      when(repository.findByNameContains("hero")).thenReturn(list);

      List<Hero> man = service.findByName("hero");
      assertThat(man).isNotNull();
      assertThat(man.size()).isEqualTo(2);
   }

   @Test
   public void test_find_hero_ByName_dont_exist() {

      when(repository.findByNameContains("man")).thenReturn(Collections.emptyList());

      List<Hero> man = service.findByName("man");
      assertThat(man).isNotNull();
      assertThat(man.size()).isEqualTo(0);
   }

}
