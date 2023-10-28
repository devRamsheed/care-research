package com.ramapps.dricareresearch.repository;

import com.ramapps.dricareresearch.model.Observations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepo extends JpaRepository<Observations, Long> {
}
