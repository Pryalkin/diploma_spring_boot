package com.bsuir.diploma.repository;

import com.bsuir.diploma.model.Viewing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewingRepository extends JpaRepository<Viewing, Long> {
}
