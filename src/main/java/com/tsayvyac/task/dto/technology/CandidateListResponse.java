package com.tsayvyac.task.dto.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateListResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer level;
    private String note;
}
