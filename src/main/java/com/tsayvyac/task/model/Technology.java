package com.tsayvyac.task.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "technology")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "technology")
    @JsonManagedReference
    private Set<CandidateUseTechnology> useTechnologies;

    public void addToSet(CandidateUseTechnology cut) {
        if (useTechnologies == null) useTechnologies = new HashSet<>();
        useTechnologies.add(cut);
    }
}
