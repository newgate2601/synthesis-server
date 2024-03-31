package com.example.synthesisserver.service;

import com.example.synthesisserver.dto.SynthesisDTO;
import com.example.synthesisserver.entity.SynthesisEntity;

import java.util.List;

public interface SynthesisService {
    List<SynthesisDTO> getSynthesis();
    void updateLostAndWinBy(Long userId, Boolean isWin);
    void createSynthesis(Long userId);
}
