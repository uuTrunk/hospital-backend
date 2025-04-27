package com.uutrunk.hospitalmedicine.convertor;

import com.uutrunk.hospitalmedicine.dto.DrugDTO;
import com.uutrunk.hospitalmedicine.pojo.DrugInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DrugConvertor {
    DrugConvertor INSTANCE = Mappers.getMapper(DrugConvertor.class);
    DrugDTO toDTO(DrugInfo entity);
    DrugInfo toEntity(DrugDTO dto);
}
