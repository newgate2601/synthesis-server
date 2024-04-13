package com.example.synthesisserver.dto;

import com.example.synthesisserver.entity.SynthesisLineEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SynthesisLineDTOList {
    private List<SynthesisLineEntity> synthesisLineDTOs;
}
