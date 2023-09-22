package com.tsayvyac.task.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a technology and its corresponding skill level of candidate. This object used in {@link CandidateResponse}.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTechnologyLevel {
    private String name;
    private Integer level;
}
