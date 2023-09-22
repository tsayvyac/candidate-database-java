package com.tsayvyac.task.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Represents a response object containing detailed information about a candidate.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDetailsResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Set<TechnologyInfo> useTechnologies;
}
