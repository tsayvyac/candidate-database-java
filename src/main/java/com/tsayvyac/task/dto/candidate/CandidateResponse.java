package com.tsayvyac.task.dto.candidate;

import java.util.Set;

public record CandidateResponse(
        Long id,
        String firstName,
        String lastName,
        Set<CandidateTechnologyLevel> useTechnologies
) {
}
