package com.bsuir.diploma.repository;

import com.bsuir.diploma.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
