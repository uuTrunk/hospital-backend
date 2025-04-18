package com.julien.hospitaldischargeservice.common;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> records;
    private long total;
    private int size;
    private int current;
    private int pages;

    public static <T> PageResult<T> from(Page<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setRecords(page.getContent());
        result.setTotal(page.getTotalElements());
        result.setSize(page.getSize());
        result.setCurrent(page.getNumber() + 1);
        result.setPages(page.getTotalPages());
        return result;
    }
}