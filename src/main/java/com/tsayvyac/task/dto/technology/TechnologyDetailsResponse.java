package com.tsayvyac.task.dto.technology;

import java.util.Set;

public record TechnologyDetailsResponse(
        Long id,
        String name,
        Set<CandidateInfo> usesThisTechnology
) {
}
