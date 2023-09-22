package com.tsayvyac.task.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request object used for updating candidate.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTechnologyRequest {
    private String name;
    private Integer level;
    private String note;
}
