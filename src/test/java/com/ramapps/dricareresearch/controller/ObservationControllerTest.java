package com.ramapps.dricareresearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramapps.dricareresearch.dto.ObservationDto;
import com.ramapps.dricareresearch.dto.ObservationResponse;
import com.ramapps.dricareresearch.service.ObservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ObservationController.class)
class ObservationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ObservationService observationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAllObservations() throws Exception {
        List<ObservationResponse> observationResponses = new ArrayList<>();
        ObservationResponse observationResponse = new ObservationResponse();
        observationResponse.setPatient(2L);
        observationResponses.add(observationResponse);
        observationResponses.add(new ObservationResponse());
        when(observationService.fetchAllObservations(any(Integer.class), any(Integer.class)))
                .thenReturn(observationResponses);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/observations")
                .param("page", "1")
                .param("length", "10")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.body").isArray())
                .andExpect(jsonPath("$.body[0].patient").value(observationResponses.get(0).getPatient()));
    }

    @Test
    public void testCreateObservation() throws Exception {
        ObservationDto observationDto = new ObservationDto();
        doNothing().when(observationService).createObservation(observationDto);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/observations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(observationDto)));
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201));
    }

    @Test
    public void testGetObservationById() throws Exception {
        long observationId = 1L;
        ObservationResponse observationResponse = new ObservationResponse();
        observationResponse.setId(observationId);
        observationResponse.setPatient(2L);
        when(observationService.getObservation(observationId)).thenReturn(observationResponse);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/observations/{id}", observationId)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.body.id").value(observationResponse.getId()));
    }

    @Test
    public void testUpdateObservation() throws Exception {
        ObservationDto observationDto = new ObservationDto();
        doNothing().when(observationService).updateObservation(observationDto);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/observations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(observationDto)));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void testDeleteObservation() throws Exception {
        long observationId = 1L;
        doNothing().when(observationService).deleteObservation(observationId);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/observations/{id}", observationId)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }
}