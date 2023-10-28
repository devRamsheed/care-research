package com.ramapps.dricareresearch.service;

import com.ramapps.dricareresearch.constants.ObservationTypes;
import com.ramapps.dricareresearch.constants.Units;
import com.ramapps.dricareresearch.dto.ObservationDto;
import com.ramapps.dricareresearch.dto.ObservationResponse;
import com.ramapps.dricareresearch.model.Observations;
import com.ramapps.dricareresearch.repository.ObservationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObservationServiceImpl implements ObservationService {
    private final ObservationRepo observationRepo;

    @Override
    public List<ObservationResponse> fetchAllObservations(Integer page, Integer length) {
        Pageable pageable = Pageable.unpaged();
        if (page != null && length != null) {
            pageable = PageRequest.of(page, length);
        }
        return observationRepo.findAll(pageable).stream()
                .map(mapToObservationDto).collect(Collectors.toList());
    }

    @Override
    public void createObservation(ObservationDto observationDto) {
        observationRepo.save(mapToObservations.apply(observationDto));
    }

    private final Function<Observations, ObservationResponse> mapToObservationDto = observations -> {
        ObservationResponse observation = new ObservationResponse();
        observation.setId(observations.getId());
        observation.setType(ObservationTypes.valueOf(observations.getType()).toString());
        observation.setValue(observations.getValue());
        observation.setUnit(Units.valueOf(observations.getUnit()).toString());
        observation.setPatient(observations.getPatient());
        observation.setDate(observations.getCreateTime());
        return observation;
    };

    private final Function<ObservationDto, Observations> mapToObservations = observations -> {
        Observations observation = new Observations();
        observation.setType(observations.getType().name());
        observation.setValue(observations.getValue());
        observation.setUnit(observations.getUnit().name());
        observation.setPatient(observations.getPatient());
        return observation;
    };
}
