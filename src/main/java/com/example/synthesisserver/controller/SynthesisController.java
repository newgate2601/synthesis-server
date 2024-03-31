package com.example.synthesisserver.controller;

import com.example.synthesisserver.dto.SynthesisDTO;
import com.example.synthesisserver.dto.SynthesisDTOList;
import com.example.synthesisserver.service.SynthesisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/synthesis")
@AllArgsConstructor
public class SynthesisController {
    private final SynthesisService synthesisService;

    @GetMapping("/list")
    public SynthesisDTOList getSynthesis() {
        return new SynthesisDTOList(synthesisService.getSynthesis());
    }

    @PutMapping("/update")
    public void updateLostAndWinBy(Long userId, Boolean isWin) {
        synthesisService.updateLostAndWinBy(userId, isWin);
    }

    @PostMapping("/create")
    public void createSynthesis(Long userId) {
        synthesisService.createSynthesis(userId);
    }
}
