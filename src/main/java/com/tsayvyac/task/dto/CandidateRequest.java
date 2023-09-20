package com.tsayvyac.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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
