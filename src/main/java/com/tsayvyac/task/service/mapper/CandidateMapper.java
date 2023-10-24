package com.tsayvyac.task.service.mapper;

import com.tsayvyac.task.dto.candidate.CandidateDetailsResponse;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyLevel;
import com.tsayvyac.task.dto.candidate.TechnologyInfo;
import com.tsayvyac.task.exception.TechnologyException;
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
        return new CandidateResponse(
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                mapToCandidateTechnologyLevel(candidate.getUseTechnologies())
        );
    }

    public CandidateDetailsResponse mapToDetailsResponse(Candidate candidate) {
        return new CandidateDetailsResponse(
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getAge(),
                mapToTechnologyInfo(candidate.getUseTechnologies())
        );
    }

    private Set<TechnologyInfo> mapToTechnologyInfo(Set<CandidateUseTechnology> candidateUseTechnology) {
        return candidateUseTechnology.stream()
                .map(cut -> technologyRepository.findById(cut.getTechnology().getId())
                        .map(technology -> new TechnologyInfo(
                                        technology.getId(),
                                        technology.getName(),
                                        cut.getLevel(),
                                        cut.getNote()
                                )
                        )
                        .orElseThrow(() ->
                                new TechnologyException(T_WITH_ID + cut.getTechnology().getId() + NOT_FOUND))
                )
                .collect(Collectors.toSet());
    }

    private Set<CandidateTechnologyLevel> mapToCandidateTechnologyLevel(Set<CandidateUseTechnology> candidateUseTechnology) {
        return candidateUseTechnology.stream()
                .map(cut -> technologyRepository.findById(cut.getTechnology().getId())
                        .map(technology -> new CandidateTechnologyLevel(
                                        technology.getName(),
                                        cut.getLevel()
                                )
                        )
                        .orElseThrow(() ->
                                new TechnologyException(T_WITH_ID + cut.getTechnology().getId() + NOT_FOUND))
                )
                .collect(Collectors.toSet());
    }
}
