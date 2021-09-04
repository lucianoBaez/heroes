package com.w2m.heroes.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.w2m.heroes.dto.HeroDto;
import com.w2m.heroes.entity.Hero;

@Mapper
public interface HeroMapper {

   HeroDto toDto(Hero entity);

   Hero fromDto(HeroDto dto);

   Hero mergeEntity(HeroDto dto, @MappingTarget Hero entity);
}
