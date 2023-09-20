package com.tsayvyac.task.dto.candidate;

import com.tsayvyac.task.model.CandidateUseTechnology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Set<CandidateUseTechnology> useTechnologies;
}
