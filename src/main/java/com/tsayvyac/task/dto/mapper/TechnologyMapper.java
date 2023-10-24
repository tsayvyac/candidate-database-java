package com.tsayvyac.task.dto.mapper;

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
        return new TechnologyDetailsResponse(
                technology.getId(),
                technology.getName(),
                mapToCandidateInfo(technology.getUseTechnologies())
        );
    }

    public TechnologyResponse mapToResponse(Technology technology) {
        return new TechnologyResponse(
                technology.getId(),
                technology.getName()
        );
    }

    private Set<CandidateInfo> mapToCandidateInfo(Set<CandidateUseTechnology> candidateUseTechnology) {
        return candidateUseTechnology.stream().map(cut ->
                        candidateRepository.findById(cut.getCandidate().getId()).map(candidate ->
                                                new CandidateInfo(
                                                        candidate.getId(),
                                                        candidate.getFirstName(),
                                                        candidate.getLastName(),
                                                        cut.getLevel(),
                                                        cut.getNote()
                                                )
                                )
                                .orElseThrow(() ->
                                        new CandidateNotFound(C_WITH_ID + cut.getCandidate().getId() + NOT_FOUND)))
                .collect(Collectors.toSet());
    }
}
