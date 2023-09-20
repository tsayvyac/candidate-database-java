package com.tsayvyac.task.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private Integer age;

    @OneToMany(mappedBy = "candidate")
    @JsonManagedReference
    private Set<CandidateUseTechnology> useTechnologies;

    public void addToSet(CandidateUseTechnology cut) {
        if (useTechnologies == null) useTechnologies = new HashSet<>();
        useTechnologies.add(cut);
    }
}
