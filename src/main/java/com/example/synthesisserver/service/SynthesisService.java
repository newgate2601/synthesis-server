package com.example.synthesisserver.service;

import com.example.synthesisserver.dto.SynthesisDTO;
import com.example.synthesisserver.entity.SynthesisEntity;
import com.example.synthesisserver.entity.SynthesisLineEntity;

import java.util.List;

public interface SynthesisService {
    List<SynthesisLineEntity> getSynthesisLines(Long synthesisId);
    List<SynthesisDTO> getSynthesis();
//    void updateLostAndWinBy(Long userId, Boolean isWin);
//    void createSynthesis(Long userId);
}
