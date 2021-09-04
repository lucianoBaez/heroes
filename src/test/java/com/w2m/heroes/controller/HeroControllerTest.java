package com.w2m.heroes.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.w2m.heroes.entity.Hero;
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
      final ResultActions resultActions = mockMvc.perform(get("/api/hero"));
      resultActions.andExpect(status().isOk());
   }

   @Test
   public void should_CreateHero_When_ValidRequest() throws Exception {
      mockMvc
            .perform(post("/api/hero").contentType(MediaType.APPLICATION_JSON).content("{ \"name\": \"hero\"}").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
   }

   @Test
   public void should_GetHero_When_ValidRequest() throws Exception {
      mockMvc
            .perform(get("/api/hero/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("myHero"));
   }

   @Test
   public void should_GetHeroByName_When_ValidRequest() throws Exception {
      LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
      requestParams.add("name", "john");

      Hero hero = Hero.builder().id(1L).name("myHero").build();
      when(heroService.findHero(1L)).thenReturn(hero);
      mockMvc
            .perform(get("/api/hero/query").accept(MediaType.APPLICATION_JSON).params(requestParams))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
   }

   @Test
   public void should_deleteHero_When_ValidRequest() throws Exception {

      Hero hero = Hero.builder().id(1L).name("myHero").build();
      when(heroService.delete(1L)).thenReturn(hero);
      mockMvc.perform(delete("/api/hero/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
   }

   @Test
   public void should_Return404_When_HeroNotFound() throws Exception {
      when(heroService.findHero(1L)).thenReturn(null);
      mockMvc.perform(get("/api/hero/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
   }

}


