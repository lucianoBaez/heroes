package com.w2m.heroes.exception;

public class HeroNotFoundException extends RuntimeException {

   HeroNotFoundException(Long id) {
      super("Could not find hero " + id);
   }
}
