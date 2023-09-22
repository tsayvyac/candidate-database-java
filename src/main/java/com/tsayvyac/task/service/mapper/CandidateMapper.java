package com.tsayvyac.task.service.mapper;

import com.tsayvyac.task.dto.candidate.CandidateDetailsResponse;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyLevel;
import com.tsayvyac.task.dto.candidate.TechnologyInfo;
import com.tsayvyac.task.exception.TechnologyNotFound;
import com.tsayvyac.task.model.Candidate;
import com.tsayvyac.task.model.CandidateUseTechnology;
import com.tsayvyac.task.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.tsayvyac.task.util.Constant.*;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CandidateMapper {
    private final TechnologyRepository technologyRepository;

    public CandidateResponse mapToCandidateResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .id(candidate.getId())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .useTechnologies(mapToCandidateTechnologyLevel(candidate.getUseTechnologies()))
                .build();
    }

    public CandidateDetailsResponse mapToDetailsResponse(Candidate candidate) {
        return CandidateDetailsResponse.builder()
                .id(candidate.getId())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .age(candidate.getAge())
                .useTechnologies(mapToTechnologyInfo(candidate.getUseTechnologies()))
                .build();
    }

    private Set<TechnologyInfo> mapToTechnologyInfo(Set<CandidateUseTechnology> candidateUseTechnology) {
        return candidateUseTechnology.stream()
                .map(cut -> technologyRepository.findById(cut.getTechnology().getId())
                        .map(technology -> TechnologyInfo.builder()
                                .id(technology.getId())
                                .name(technology.getName())
                                .level(cut.getLevel())
                                .note(cut.getNote())
                                .build()
                        )
                        .orElseThrow(() ->
                                new TechnologyNotFound(T_WITH_ID + cut.getTechnology().getId() + NOT_FOUND))
                )
                .collect(Collectors.toSet());
    }

    private Set<CandidateTechnologyLevel> mapToCandidateTechnologyLevel(Set<CandidateUseTechnology> candidateUseTechnology) {
        return candidateUseTechnology.stream()
                .map(cut -> technologyRepository.findById(cut.getTechnology().getId())
                        .map(technology -> CandidateTechnologyLevel.builder()
                                .name(technology.getName())
                                .level(cut.getLevel())
                                .build()
                        )
                        .orElseThrow(() ->
                                new TechnologyNotFound(T_WITH_ID + cut.getTechnology().getId() + NOT_FOUND))
                )
                .collect(Collectors.toSet());
    }
}
