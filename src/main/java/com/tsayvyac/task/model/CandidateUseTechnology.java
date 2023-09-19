package com.tsayvyac.task.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateUseTechnology {

    @EmbeddedId
    private CandidateTechnologyKey id;

    @ManyToOne
    @MapsId("technologyId")
    @JoinColumn(name = "technology_id")
    private Technology technology;

    @ManyToOne
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private Integer level;

    private String note;
}
