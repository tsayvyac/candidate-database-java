package com.tsayvyac.task.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyListResponse {
    private Long id;
    private String name;
    private Integer level;
    private String note;
}