package com.example.synthesisserver.mapper;

import com.example.synthesisserver.dto.SynthesisDTO;
import com.example.synthesisserver.entity.SynthesisEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperHelper {
    public SynthesisDTO getOutputFrom(SynthesisEntity synthesisEntity){
        return SynthesisDTO.builder()
                .id(synthesisEntity.getId())
//                .winCount(synthesisEntity.getWinCount())
//                .lostCount(synthesisEntity.getLostCount())
                .build();
    }
}
