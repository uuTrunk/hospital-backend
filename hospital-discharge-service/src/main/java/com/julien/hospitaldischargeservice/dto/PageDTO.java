package com.julien.hospitaldischargeservice.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int number;
    private int size;
    private boolean first;
    private boolean last;

    public static <T> PageDTO<T> from(Page<T> page) {
        PageDTO<T> dto = new PageDTO<>();
        dto.setContent(page.getContent());
        dto.setTotalElements(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setNumber(page.getNumber());
        dto.setSize(page.getSize());
        dto.setFirst(page.isFirst());
        dto.setLast(page.isLast());
        return dto;
    }
}