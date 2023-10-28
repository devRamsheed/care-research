package com.ramapps.dricareresearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
    private T body;
    private String message;
    private int status;
}
