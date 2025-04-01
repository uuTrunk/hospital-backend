package com.uutrunk.hospitalestimate.po;

import lombok.Data;

/**
 * @author uutrunk
 */
@Data
public class DietRestriction {
    private int id;
    private int assessmentId;
    private String restrictionType;
}
