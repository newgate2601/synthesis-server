package com.example.synthesisserver.repo;

import com.example.synthesisserver.entity.SynthesisLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SynthesisLineRepository extends JpaRepository<SynthesisLineEntity, Long> {
    List<SynthesisLineEntity> findAllBySynthesisIdIn(Collection<Long> ids);
}
