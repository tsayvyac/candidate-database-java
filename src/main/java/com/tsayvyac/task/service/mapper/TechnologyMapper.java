package com.tsayvyac.task.service.mapper;

import com.tsayvyac.task.dto.technology.CandidateInfo;
import com.tsayvyac.task.dto.technology.TechnologyDetailsResponse;
import com.tsayvyac.task.dto.technology.TechnologyResponse;
import com.tsayvyac.task.exception.CandidateNotFound;
import com.tsayvyac.task.model.CandidateUseTechnology;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.tsayvyac.task.util.Constant.*;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TechnologyMapper {
    private final CandidateRepository candidateRepository;

    public TechnologyDetailsResponse mapToDetailsResponse(Technology technology) {
        return TechnologyDetailsResponse.builder()
                .id(technology.getId())
                .name(technology.getName())
                .usesThisTechnology(mapToCandidateInfo(technology.getUseTechnologies()))
                .build();
    }

    public TechnologyResponse mapToResponse(Technology technology) {
        return TechnologyResponse.builder()
                .id(technology.getId())
                .name(technology.getName())
                .build();
    }

    private Set<CandidateInfo> mapToCandidateInfo(Set<CandidateUseTechnology> candidateUseTechnology) {
        return candidateUseTechnology.stream().map(cut ->
                        candidateRepository.findById(cut.getCandidate().getId()).map(candidate ->
                                        CandidateInfo.builder()
                                                .id(candidate.getId())
                                                .firstName(candidate.getFirstName())
                                                .lastName(candidate.getLastName())
                                                .level(cut.getLevel())
                                                .note(cut.getNote())
                                                .build()
                                )
                                .orElseThrow(() ->
                                        new CandidateNotFound(C_WITH_ID + cut.getCandidate().getId() + NOT_FOUND)))
                .collect(Collectors.toSet());
    }
}
