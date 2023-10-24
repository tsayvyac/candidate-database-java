package com.tsayvyac.task.dto.candidate;

import java.util.List;

public record CandidateRequest(
        String firstName,
        String lastName,
        Integer age,
        List<CandidateTechnologyRequest> technologies
) {
}
