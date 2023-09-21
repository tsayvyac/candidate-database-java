package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.technology.CandidateListResponse;
import com.tsayvyac.task.dto.technology.TechnologyDetailsResponse;
import com.tsayvyac.task.dto.technology.TechnologyRequest;
import com.tsayvyac.task.dto.technology.TechnologyResponse;
import com.tsayvyac.task.exception.CandidateNotFound;
import com.tsayvyac.task.exception.TechnologyNotFound;
import com.tsayvyac.task.model.CandidateUseTechnology;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.CandidateRepository;
import com.tsayvyac.task.repository.TechnologyRepository;
import com.tsayvyac.task.repository.pojo.TechnologyCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final CandidateRepository candidateRepository;

    public void deleteTechnology(Long id) {
        Optional<Technology> technologyToDelete = technologyRepository.findById(id);
        if (technologyToDelete.isPresent()) {
            technologyRepository.delete(technologyToDelete.get());
        } else throw new TechnologyNotFound("Technology with ID " + id + " not found!");
    }

    public void updateTechnology(Long id, TechnologyRequest technologyRequest) {
        technologyRepository.save(technologyRepository.findById(id)
                .map(technology -> {
                    technology.setName(technologyRequest.getName());
                    return technology;
                })
                .orElseThrow(() -> new TechnologyNotFound("Technology with ID " + id + " not found!"))
        );
        log.info("Technology with name {} was saved!", technologyRequest.getName());
    }

    public TechnologyDetailsResponse getTechnologyDetails(Long id) {
        return technologyRepository.findById(id)
                .map(this::mapToDetailResponse)
                .orElseThrow(() -> new TechnologyNotFound("Technology with ID " + id + " not found!"));
    }

    public TechnologyDetailsResponse mapToDetailResponse(Technology technology) {
        return TechnologyDetailsResponse.builder()
                .id(technology.getId())
                .name(technology.getName())
                .usesThisTechnology(mapToCandidateListResponse(technology.getUseTechnologies()))
                .build();
    }

    public Set<CandidateListResponse> mapToCandidateListResponse(Set<CandidateUseTechnology> candidateUseTechnology) {
        return candidateUseTechnology.stream().map(cut ->
                candidateRepository.findById(cut.getCandidate().getId()).map(candidate ->
                        CandidateListResponse.builder()
                                .id(candidate.getId())
                                .firstName(candidate.getFirstName())
                                .lastName(candidate.getLastName())
                                .level(cut.getLevel())
                                .note(cut.getNote())
                                .build())
                        .orElseThrow(() -> new CandidateNotFound("Candidate with ID " + cut.getCandidate().getId() + " not found!")))
                .collect(Collectors.toSet());
    }

    // TODO: Change the design of response technologies
    public List<TechnologyResponse> getAllTechnologies() {
        return technologyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TechnologyResponse mapToResponse(Technology technology) {
        return TechnologyResponse.builder()
                .id(technology.getId())
                .name(technology.getName())
                .build();
    }

    public void addTechnology(TechnologyRequest technologyRequest) {
        Technology technology = Technology.builder()
                .name(technologyRequest.getName())
                .build();

        technologyRepository.save(technology);
        log.info("Technology {} is saved!", technology.getName());
    }

    public List<TechnologyCount> getCountOfUsingTechnology() {
        return technologyRepository.getCountOfUsingTechnology();
    }
}
