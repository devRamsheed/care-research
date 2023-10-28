package com.ramapps.dricareresearch.controller;

import com.ramapps.dricareresearch.dto.GenericResponse;
import com.ramapps.dricareresearch.dto.ObservationDto;
import com.ramapps.dricareresearch.dto.ObservationResponse;
import com.ramapps.dricareresearch.service.ObservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/observations")
@RequiredArgsConstructor
public class ObservationController {
    private final ObservationService observationService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<ObservationResponse>>> fetchAllObservations(@RequestParam(value = "page", required = false) Integer page,
                                                                                           @RequestParam(value = "length", required = false) Integer length) {
        return ResponseEntity.ok(GenericResponse.<List<ObservationResponse>>builder()
                .body(observationService.fetchAllObservations(page, length))
                .status(HttpStatus.OK.value())
                .build());
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Void>> createObservation(@RequestBody ObservationDto observation) {
        observationService.createObservation(observation);
        return new ResponseEntity<>(GenericResponse.<Void>builder()
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }

}
