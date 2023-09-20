package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.TechnologyRequest;
import com.tsayvyac.task.dto.TechnologyResponse;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    // TODO: Implement TechnologyService...

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
                .useTechnologies(technology.getUseTechnologies())
                .build();
    }

    public void addTechnology(TechnologyRequest technologyRequest) {
        Technology technology = Technology.builder()
                .name(technologyRequest.getName())
                .build();

        technologyRepository.save(technology);
        log.info("Technology {} is saved!", technology.getName());
    }
}
