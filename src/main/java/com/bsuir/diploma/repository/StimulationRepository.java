package com.bsuir.diploma.repository;

import com.bsuir.diploma.model.Stimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StimulationRepository extends JpaRepository<Stimulation, Long> {
}
