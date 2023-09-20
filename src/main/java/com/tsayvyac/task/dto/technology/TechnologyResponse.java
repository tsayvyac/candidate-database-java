package com.tsayvyac.task.dto.technology;

import com.tsayvyac.task.model.CandidateUseTechnology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyResponse {
    private Long id;
    private String name;
    private Set<CandidateUseTechnology> usesThisTechnologies;
}
