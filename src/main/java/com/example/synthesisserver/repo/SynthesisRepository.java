package com.example.synthesisserver.repo;

import com.example.synthesisserver.entity.SynthesisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SynthesisRepository extends JpaRepository<SynthesisEntity, Long> {
}
