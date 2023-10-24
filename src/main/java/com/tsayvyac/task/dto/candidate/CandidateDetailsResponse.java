package com.tsayvyac.task.dto.candidate;

import java.util.Set;

public record CandidateDetailsResponse(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        Set<TechnologyInfo> useTechnologies
) {
}
