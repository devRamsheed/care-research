package com.ramapps.dricareresearch.dto;

import com.ramapps.dricareresearch.constants.ObservationTypes;
import com.ramapps.dricareresearch.constants.Units;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ObservationResponse {
    private Long id;
    private String type;
    private Timestamp date;
    private Double value;
    private Long patient;
    private String unit;
}
