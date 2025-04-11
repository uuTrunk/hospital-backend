package com.uutrunk.hospitalhealthdocument.convertor;

import com.uutrunk.hospitalhealthdocument.dto.HealthRecordDTO;
import com.uutrunk.hospitalhealthdocument.pojo.HealthRecordMain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HealthRecordConvertor {
    HealthRecordConvertor INSTANCE = Mappers.getMapper(HealthRecordConvertor.class);
    HealthRecordDTO toDTO(HealthRecordMain entity);
}
