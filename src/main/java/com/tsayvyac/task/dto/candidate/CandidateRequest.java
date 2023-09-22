package com.tsayvyac.task.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a request object used for creating candidate information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<CandidateTechnologyRequest> technologies;
}
