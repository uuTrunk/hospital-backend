package com.uutrunk.hospitalestimate.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author uutrunk
 */
@Data
public class DietRestriction {
    private int id;
    private int assessmentId;
    private List<String> restrictionType;
}
