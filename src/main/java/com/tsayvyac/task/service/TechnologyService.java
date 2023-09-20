package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.technology.TechnologyRequest;
import com.tsayvyac.task.dto.technology.TechnologyResponse;
import com.tsayvyac.task.exception.TechnologyNotFound;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    public void deleteTechnology(Long id) {
        Optional<Technology> technologyToDelete = technologyRepository.findById(id);
        if (technologyToDelete.isPresent()) {
            technologyRepository.delete(technologyToDelete.get());
        } else throw new TechnologyNotFound("Technology with ID " + id + " not found!");
    }

    public void updateTechnology(Long id, TechnologyRequest technologyRequest) {
        Optional<Technology> technologyOpt = technologyRepository.findById(id);
        if (technologyOpt.isPresent()) {
            Technology technology = technologyOpt.get();
            technology.setName(technologyRequest.getName());
            technologyRepository.save(technology);
            log.info("Technology with ID {} was saved!", technology.getId());
        } else throw new TechnologyNotFound("Technology with ID " + id + " not found!");
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
                .usesThisTechnologies(technology.getUseTechnologies())
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
