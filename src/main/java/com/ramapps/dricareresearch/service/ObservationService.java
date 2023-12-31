package com.ramapps.dricareresearch.service;

import com.ramapps.dricareresearch.dto.ObservationDto;
import com.ramapps.dricareresearch.dto.ObservationResponse;

import java.util.List;

public interface ObservationService {

    /**
     * Function to fetch all observations
     *
     * @param page   page number
     * @param length page length
     * @return list of observations
     */
    List<ObservationResponse> fetchAllObservations(Integer page, Integer length);

    /**
     * Function to create new Observation
     *
     * @param observationDto observation dto
     */
    void createObservation(ObservationDto observationDto);

    /**
     * get an observation data by id
     *
     * @param id observation unique id
     * @return observation response
     */
    ObservationResponse getObservation(Long id);

    /**
     * update an existing observation
     *
     * @param observationDto observation updated record
     */
    void updateObservation(ObservationDto observationDto);

    /**
     * Delete an observation
     *
     * @param id observation identifier
     */
    void deleteObservation(Long id);
}
