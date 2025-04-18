package com.uutrunk.hospitalhealthdocument.convertor;

import com.uutrunk.hospitalhealthdocument.dto.DiagnosisPlanDTO;
import com.uutrunk.hospitalhealthdocument.pojo.DiagnosisPlan;
import org.mapstruct.Mapper;

@Mapper
public interface DiagnosisPlanConvertor {
    DiagnosisPlanConvertor INSTANCE = org.mapstruct.factory.Mappers.getMapper(DiagnosisPlanConvertor.class);
    DiagnosisPlanDTO toDTO(DiagnosisPlan entity);
    DiagnosisPlan toEntity(DiagnosisPlanDTO dto);
}
