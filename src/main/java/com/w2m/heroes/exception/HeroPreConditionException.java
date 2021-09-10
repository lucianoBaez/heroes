package com.w2m.heroes.exception;

public class HeroPreConditionException extends RuntimeException {

   public HeroPreConditionException() {
      super("Could not find hero with blank name");
   }
}
