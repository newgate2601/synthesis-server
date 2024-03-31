package com.example.synthesisserver.service;

import com.example.synthesisserver.base.Filter;
import com.example.synthesisserver.client.impl.AuthenticationServer;
import com.example.synthesisserver.dto.SynthesisDTO;
import com.example.synthesisserver.dto.UserDTO;
import com.example.synthesisserver.entity.SynthesisEntity;
import com.example.synthesisserver.mapper.MapperHelper;
import com.example.synthesisserver.repo.SynthesisRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SynthesisServiceImp implements SynthesisService{
    private final SynthesisRepository synthesisRepository;
    private final AuthenticationServer authenticationServer;
    private final MapperHelper mapperHelper;
    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<SynthesisDTO> getSynthesis() {
        Map<Long, UserDTO> userDTOMap = authenticationServer.getUserMap();
        List<SynthesisEntity> synthesisEntities = Filter.builder(SynthesisEntity.class, entityManager)
                .orderBy("winCount", "DESC")
                .getPage(PageRequest.of(0, 1000))
                .getContent();
        return synthesisEntities.stream().map(synthesisEntity -> {
            SynthesisDTO synthesisDTO = mapperHelper.getOutputFrom(synthesisEntity);
            if (userDTOMap.containsKey(synthesisEntity.getUserId())){
                synthesisDTO.setUserName(userDTOMap.get(synthesisEntity.getUserId()).getUsername());
            }
            return synthesisDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateLostAndWinBy(Long userId, Boolean isWin) {
        SynthesisEntity synthesisEntity = synthesisRepository.findById(userId).get();
        if (Boolean.TRUE.equals(isWin)){
            synthesisEntity.setWinCount(synthesisEntity.getWinCount() + 1);
        }
        else {
            synthesisEntity.setLostCount(synthesisEntity.getLostCount() + 1);
        }
    }

    @Override
    @Transactional
    public void createSynthesis(Long userId) {
        synthesisRepository.save(
                SynthesisEntity.builder()
                        .userId(userId)
                        .lostCount(0)
                        .winCount(0)
                        .build()
        );
    }
}
