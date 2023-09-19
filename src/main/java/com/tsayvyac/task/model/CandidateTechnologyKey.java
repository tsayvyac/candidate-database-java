package com.tsayvyac.task.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTechnologyKey implements Serializable {

    @Column(name = "technology_id")
    private Long technologyId;

    @Column(name = "candidate_id")
    private Long candidateId;
}
