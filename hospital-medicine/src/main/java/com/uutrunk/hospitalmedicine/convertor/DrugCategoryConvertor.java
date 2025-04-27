package com.uutrunk.hospitalmedicine.convertor;

import com.uutrunk.hospitalmedicine.dto.DrugCategoryDTO;
import com.uutrunk.hospitalmedicine.pojo.DrugCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DrugCategoryConvertor {
    DrugCategoryConvertor INSTANCE = Mappers.getMapper(DrugCategoryConvertor.class);

    DrugCategoryDTO toDTO(DrugCategory entity);
    DrugCategory toEntity(DrugCategoryDTO dto);
}
