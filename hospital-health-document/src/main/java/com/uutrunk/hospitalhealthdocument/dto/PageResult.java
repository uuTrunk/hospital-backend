package com.uutrunk.hospitalhealthdocument.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T>{
    private Long total;
    private List<T> list;
}
