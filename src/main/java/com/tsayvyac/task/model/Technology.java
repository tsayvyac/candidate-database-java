package com.tsayvyac.task.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "technology")
    private Set<CandidateUseTechnology> useTechnologies;
}
