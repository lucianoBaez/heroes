package com.w2m.heroes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import com.w2m.heroes.entity.Hero;
import com.w2m.heroes.exception.HeroNotFoundException;
import com.w2m.heroes.service.HeroService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class HeroControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private WebApplicationContext webApplicationContext;

   @MockBean
   private HeroService heroService;

   @Before
   public void setUp() {
      this.mockMvc = webAppContextSetup(webApplicationContext).build();
   }

   @Test
   public void should_GetHeroes_valid_request() throws Exception {
      List<Hero> list = Stream
            .of(Hero.builder().id(1L).name("myHero").build(), Hero.builder().id(2L).name("myHero").build())
            .collect(Collectors.toList());

      when(heroService.findAll()).thenReturn(list);

      final ResultActions resultActions = mockMvc.perform(get("/api/hero"));
      resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[1].id").value(2));
   }

   @Test
   public void should_GetHeroes_valid_request_emtpy() throws Exception {
      List<Hero> list = new ArrayList<>();

      when(heroService.findAll()).thenReturn(list);

      final ResultActions resultActions = mockMvc.perform(get("/api/hero"));
      resultActions.andExpect(status().isOk()).andExpect(content().string("[]"));
   }

   @Test
   public void should_CreateHero_When_ValidRequest() throws Exception {

      when(heroService.create(any())).thenReturn(Hero.builder().id(33L).name("hero").build());
      mockMvc
            .perform(post("/api/hero").contentType(MediaType.APPLICATION_JSON).content("{ \"name\": \"hero\"}").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(33))
            .andExpect(jsonPath("$.name").value("hero"));
   }

   @Test
   public void should_UpdateHero_When_ValidRequest() throws Exception {

      when(heroService.update(any())).thenReturn(Hero.builder().id(33L).name("hero").build());
      mockMvc
            .perform(put("/api/hero").contentType(MediaType.APPLICATION_JSON).content("{ \"name\": \"hero\"}").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(33))
            .andExpect(jsonPath("$.name").value("hero"));
   }

   @Test
   public void should_GetHero_When_ValidRequest() throws Exception {

      Hero hero = Hero.builder().id(1L).name("myHero").build();
      when(heroService.findById(1L)).thenReturn(hero);

      ResultActions resultActions = mockMvc
            .perform(get("/api/hero/id/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("myHero"));
   }

   @Test
   public void should_GetHeroByName_When_ValidRequest() throws Exception {

      List<Hero> list = Stream
            .of(Hero.builder().id(1L).name("myHero").build(), Hero.builder().id(2L).name("myHero").build())
            .collect(Collectors.toList());

      when(heroService.findByName("hero")).thenReturn(list);

      ResultActions resultActions = mockMvc
            .perform(get("/api/hero/name/hero"))
            .andExpect(status().isOk())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[1].id").value(2));
   }

   @Test
   public void should_deleteHero_When_ValidRequest() throws Exception {

      Hero hero = Hero.builder().id(1L).name("myHero").build();
      when(heroService.delete(1L)).thenReturn(hero);

      ResultActions resultActions = mockMvc
            .perform(delete("/api/hero/id/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("myHero"));
   }

   @Test
   public void should_Return404_When_HeroNotFound() throws Exception {
      when(heroService.findById(1L)).thenThrow(HeroNotFoundException.class);
      mockMvc.perform(get("/api/hero/id/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
   }

}


