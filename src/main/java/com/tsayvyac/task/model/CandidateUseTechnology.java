package com.tsayvyac.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CandidateUseTechnology {

    @EmbeddedId
    private CandidateTechnologyKey id;

    @ManyToOne
    @MapsId("technologyId")
    @JoinColumn(name = "technology_id")
    @JsonBackReference
    private Technology technology;

    @ManyToOne
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    private Candidate candidate;

    private Integer level;

    private String note;

    public CandidateUseTechnology(Technology technology, Candidate candidate, Integer level, String note) {
        this.id = new CandidateTechnologyKey();
        this.technology = technology;
        this.candidate = candidate;
        this.level = level;
        this.note = note;
    }

    public static CandidateUseTechnologyBuilder builder() {
        return new CandidateUseTechnologyBuilder();
    }

    public static class CandidateUseTechnologyBuilder {
        private Technology technology;
        private Candidate candidate;
        private Integer level;
        private String note;

        CandidateUseTechnologyBuilder() {
        }

        public CandidateUseTechnologyBuilder technology(Technology technology) {
            this.technology = technology;
            return this;
        }

        public CandidateUseTechnologyBuilder candidate(Candidate candidate) {
            this.candidate = candidate;
            return this;
        }

        public CandidateUseTechnologyBuilder level(Integer level) {
            this.level = level;
            return this;
        }

        public CandidateUseTechnologyBuilder note(String note) {
            this.note = note;
            return this;
        }

        public CandidateUseTechnology build() {
            return new CandidateUseTechnology(this.technology, this.candidate, this.level, this.note);
        }
    }
}
