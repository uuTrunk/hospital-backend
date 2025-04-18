package com.uutrunk.hospitalhealthdocument.convertor;

import com.uutrunk.hospitalhealthdocument.dto.AdmissionHistoryDTO;
import com.uutrunk.hospitalhealthdocument.pojo.AdmissionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdmissionHistoryConvertor {
    AdmissionHistoryConvertor INSTANCE = Mappers.getMapper(AdmissionHistoryConvertor.class);
    AdmissionHistoryDTO toDTO(AdmissionHistory entity);
    AdmissionHistory toEntity(AdmissionHistoryDTO dto);

}
