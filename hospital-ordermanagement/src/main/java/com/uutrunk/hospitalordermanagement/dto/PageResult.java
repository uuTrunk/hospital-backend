package com.uutrunk.hospitalordermanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T>{
    private Long total;
    private List<T> list;
}
