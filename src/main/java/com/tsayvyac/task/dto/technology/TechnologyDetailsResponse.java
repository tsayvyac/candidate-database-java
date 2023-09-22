package com.tsayvyac.task.dto.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Represents a response object containing detailed information about a technology.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDetailsResponse {
    private Long id;
    private String name;
    private Set<CandidateInfo> usesThisTechnology;
}
