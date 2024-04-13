package com.example.synthesisserver.service;

import com.example.synthesisserver.base.Filter;
import com.example.synthesisserver.client.impl.AuthenticationServer;
import com.example.synthesisserver.dto.SynthesisDTO;
import com.example.synthesisserver.dto.SynthesisLineDTO;
import com.example.synthesisserver.dto.UserDTO;
import com.example.synthesisserver.entity.SynthesisEntity;
import com.example.synthesisserver.entity.SynthesisLineEntity;
import com.example.synthesisserver.mapper.MapperHelper;
import com.example.synthesisserver.repo.SynthesisLineRepository;
import com.example.synthesisserver.repo.SynthesisRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
public class SynthesisServiceImp implements SynthesisService {
    private final SynthesisRepository synthesisRepository;
    private final AuthenticationServer authenticationServer;
    private final MapperHelper mapperHelper;
    private final EntityManager entityManager;
    private final SynthesisLineRepository synthesisLineRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SynthesisLineEntity> getSynthesisLines(Long synthesisId) {
        return Filter.builder(SynthesisLineEntity.class, entityManager)
                .filter()
                .orderBy("createdAt", "DESC")
                .isEqual("synthesisId", synthesisId)
                .getPage(PageRequest.of(0, 10000))
                .getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SynthesisDTO> getSynthesis() {
        Map<Long, UserDTO> userDTOMap = authenticationServer.getUserMap();

        String jpql = "SELECT syn " +
                "FROM SynthesisEntity syn " +
                "JOIN SynthesisLineEntity line ON syn.id = line.synthesisId " +
                "GROUP BY syn.id " +
                "ORDER BY SUM(CASE WHEN line.isWin = true THEN 1 ELSE 0 END) DESC";

        TypedQuery<SynthesisEntity> query = entityManager.createQuery(jpql, SynthesisEntity.class);
        List<SynthesisEntity> synthesisEntities = query.getResultList();

        Map<Long, List<SynthesisLineEntity>> synthesisLineMap = synthesisLineRepository.findAllBySynthesisIdIn(
                synthesisEntities.stream().map(SynthesisEntity::getId).collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(SynthesisLineEntity::getSynthesisId));

        return synthesisEntities.stream().map(synthesisEntity -> {
            SynthesisDTO synthesisDTO = mapperHelper.getOutputFrom(synthesisEntity);
            if (userDTOMap.containsKey(synthesisEntity.getUserId())) {
                synthesisDTO.setUserName(userDTOMap.get(synthesisEntity.getUserId()).getUsername());
            }
            if (synthesisLineMap.containsKey(synthesisDTO.getId())) {
                List<SynthesisLineEntity> synthesisLineEntities = synthesisLineMap.get(synthesisDTO.getId());
                int winCount = 0;
                int lostCount = 0;
                for (SynthesisLineEntity synthesisLineEntity : synthesisLineEntities) {
                    if (Boolean.TRUE.equals(synthesisLineEntity.getIsWin())) {
                        winCount++;
                    } else {
                        lostCount++;
                    }
                }
                synthesisDTO.setWinCount(winCount);
                synthesisDTO.setLostCount(lostCount);
            }
            return synthesisDTO;
        }).collect(Collectors.toList());
    }

//    @Override
//    @Transactional
//    public void updateLostAndWinBy(Long userId, Boolean isWin) {
//        SynthesisEntity synthesisEntity = synthesisRepository.findById(userId).get();
//        if (Boolean.TRUE.equals(isWin)){
//            synthesisEntity.setWinCount(synthesisEntity.getWinCount() + 1);
//        }
//        else {
//            synthesisEntity.setLostCount(synthesisEntity.getLostCount() + 1);
//        }
//    }

//    @Override
//    @Transactional
//    public void createSynthesis(Long userId) {
//        synthesisRepository.save(
//                SynthesisEntity.builder()
//                        .userId(userId)
//                        .lostCount(0)
//                        .winCount(0)
//                        .build()
//        );
//    }
}
