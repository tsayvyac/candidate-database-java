package com.tsayvyac.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateTechnologyRequest {
    private String name;
    private Integer level;
    private String note;
}
