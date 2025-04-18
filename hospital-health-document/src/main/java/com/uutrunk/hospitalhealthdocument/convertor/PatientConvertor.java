package com.uutrunk.hospitalhealthdocument.convertor;

import com.uutrunk.hospitalhealthdocument.dto.PatientDTO;
import com.uutrunk.hospitalhealthdocument.dto.PatientDetailDTO;
import com.uutrunk.hospitalhealthdocument.pojo.PatientInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientConvertor {
    PatientConvertor INSTANCE = Mappers.getMapper(PatientConvertor.class);
    PatientDetailDTO toDetailDTO(PatientInfo entity);
    PatientDTO toDTO(PatientInfo entity);
}
