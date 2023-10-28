package com.ramapps.dricareresearch;

import com.ramapps.dricareresearch.constants.ObservationTypes;
import com.ramapps.dricareresearch.constants.Units;
import com.ramapps.dricareresearch.model.Observations;
import com.ramapps.dricareresearch.repository.ObservationRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class DriCareresearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriCareresearchApplication.class, args);
    }
}
