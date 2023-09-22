package com.tsayvyac.task.dto.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents information about candidate. Used with {@link TechnologyResponse}.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer level;
    private String note;
}
