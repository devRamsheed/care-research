package com.ramapps.dricareresearch.service;

import com.ramapps.dricareresearch.constants.ObservationTypes;
import com.ramapps.dricareresearch.constants.Units;
import com.ramapps.dricareresearch.dto.ObservationDto;
import com.ramapps.dricareresearch.dto.ObservationResponse;
import com.ramapps.dricareresearch.model.Observations;
import com.ramapps.dricareresearch.repository.ObservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ObservationServiceImplTest {

    private ObservationServiceImpl observationService;

    @Mock
    private ObservationRepo observationRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        observationService = new ObservationServiceImpl(observationRepo);
    }

    @Test
    public void testFetchAllObservations() {
        Page<Observations> mockPage = mock(Page.class);
        List<ObservationResponse> observationResponses = new ArrayList<>();
        observationResponses.add(new ObservationResponse());
        observationResponses.add(new ObservationResponse());
        List<Observations> observationsList = new ArrayList<>();
        Observations mockObservation = new Observations();
        mockObservation.setUnit("BEATS_MINUTE");
        mockObservation.setValue(10.01);
        mockObservation.setType("HEART_RATE");
        Observations mockObservation1 = new Observations();
        mockObservation1.setUnit("BEATS_MINUTE");
        mockObservation1.setValue(10.01);
        mockObservation1.setType("HEART_RATE");
        observationsList.add(mockObservation);
        observationsList.add(mockObservation1);
        when(mockPage.stream()).thenReturn(observationsList.stream());
        when(observationRepo.findAll(any(Pageable.class))).thenReturn(mockPage);
        List<ObservationResponse> result = observationService.fetchAllObservations(0, 10);
        assertEquals(observationResponses.size(), result.size());
    }

    @Test
    public void testCreateObservation() {
        ObservationDto observationDto = new ObservationDto();
        observationDto.setType(ObservationTypes.HEART_RATE);
        observationDto.setUnit(Units.DEGREE);
        Observations mockObservation = new Observations();
        when(observationRepo.save(any(Observations.class))).thenReturn(mockObservation);
        observationService.createObservation(observationDto);
        verify(observationRepo, times(1)).save(any(Observations.class));
    }

    @Test
    public void testGetObservation() {
        long observationId = 1L;
        Observations mockObservation = new Observations();
        mockObservation.setPatient(1L);
        mockObservation.setUnit("BEATS_MINUTE");
        mockObservation.setValue(10.01);
        mockObservation.setType("HEART_RATE");
        when(observationRepo.findById(observationId)).thenReturn(Optional.of(mockObservation));
        ObservationResponse result = observationService.getObservation(observationId);
        assertNotNull(result);
    }

    @Test
    public void testUpdateObservation() {
        long observationId = 1L;
        ObservationDto observationDto = new ObservationDto();
        observationDto.setId(1L);
        observationDto.setType(ObservationTypes.HEART_RATE);
        observationDto.setUnit(Units.DEGREE);
        observationDto.setValue(10.01);
        Observations mockObservation = new Observations();
        mockObservation.setId(1L);
        mockObservation.setType("HEART_RATE");
        mockObservation.setUnit("DEGREE");
        mockObservation.setValue(10.01);
        when(observationRepo.findById(anyLong())).thenReturn(Optional.of(mockObservation));
        observationService.updateObservation(observationDto);
        verify(observationRepo, times(1)).findById(observationId);
        assertEquals(observationDto.getType().name(), mockObservation.getType());
        assertEquals(observationDto.getValue(), mockObservation.getValue());
        assertEquals(observationDto.getUnit().name(), mockObservation.getUnit());
        assertEquals(observationDto.getPatient(), mockObservation.getPatient());
    }

    @Test
    public void testDeleteObservation() {
        long observationId = 1L;
        observationService.deleteObservation(observationId);
        verify(observationRepo, times(1)).deleteById(observationId);
    }
}