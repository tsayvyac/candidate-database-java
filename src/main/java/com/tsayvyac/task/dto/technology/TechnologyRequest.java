package com.tsayvyac.task.dto.technology;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request object used for creating technology information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyRequest {
    private String name;
}
