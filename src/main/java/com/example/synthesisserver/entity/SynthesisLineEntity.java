package com.example.synthesisserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tbl_synthesis_line")
public class SynthesisLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long synthesisId;
    private Boolean isWin;
    private Integer yourNumberOfMoves;
    private Integer botNumberMoves;
    private Integer level;
    private Boolean youFirst;
    private OffsetDateTime createdAt;
}
