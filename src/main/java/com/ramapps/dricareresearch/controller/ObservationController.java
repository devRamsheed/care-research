package com.ramapps.dricareresearch.controller;

import com.ramapps.dricareresearch.dto.GenericResponse;
import com.ramapps.dricareresearch.dto.ObservationDto;
import com.ramapps.dricareresearch.dto.ObservationResponse;
import com.ramapps.dricareresearch.service.ObservationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/observations")
@RequiredArgsConstructor
@Api(value = "Observations", tags = "Observation APIs")
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

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ObservationResponse>> getObservationById(@PathVariable Long id) {
        return new ResponseEntity<>(GenericResponse.<ObservationResponse>builder()
                .body(observationService.getObservation(id))
                .status(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GenericResponse<Void>> updateObservation(@RequestBody ObservationDto observation) {
        observationService.updateObservation(observation);
        return new ResponseEntity<>(GenericResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteObservation(@PathVariable Long id) {
        observationService.deleteObservation(id);
        return new ResponseEntity<>(GenericResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

}
