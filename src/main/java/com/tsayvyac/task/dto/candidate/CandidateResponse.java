package com.tsayvyac.task.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Represents a response object used for retrieving candidate information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<CandidateTechnologyLevel> useTechnologies;
}
