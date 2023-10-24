package com.tsayvyac.task.dto.technology;

public record CandidateInfo(
        Long id,
        String firstName,
        String lastName,
        Integer level,
        String note
) {
}
