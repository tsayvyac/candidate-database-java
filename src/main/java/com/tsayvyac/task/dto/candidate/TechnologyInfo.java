package com.tsayvyac.task.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents information about technology. Used with {@link CandidateResponse}.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyInfo {
    private Long id;
    private String name;
    private Integer level;
    private String note;
}
