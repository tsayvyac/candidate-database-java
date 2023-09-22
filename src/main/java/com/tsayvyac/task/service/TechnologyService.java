package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.technology.TechnologyDetailsResponse;
import com.tsayvyac.task.dto.technology.TechnologyRequest;
import com.tsayvyac.task.dto.technology.TechnologyResponse;
import com.tsayvyac.task.exception.TechnologyNotFound;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.TechnologyRepository;
import com.tsayvyac.task.repository.pojo.TechnologyCount;
import com.tsayvyac.task.service.mapper.TechnologyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.tsayvyac.task.util.Constant.*;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final TechnologyMapper technologyMapper;

    public void deleteTechnology(Long id) {
        Optional<Technology> technologyToDelete = technologyRepository.findById(id);
        if (technologyToDelete.isPresent()) {
            technologyRepository.delete(technologyToDelete.get());
        } else throw new TechnologyNotFound(T_WITH_ID + id + NOT_FOUND);
    }

    public void updateTechnology(Long id, TechnologyRequest technologyRequest) {
        technologyRepository.save(technologyRepository.findById(id)
                .map(technology -> {
                    technology.setName(technologyRequest.getName());
                    return technology;
                })
                .orElseThrow(() -> new TechnologyNotFound(T_WITH_ID + id + NOT_FOUND))
        );
        log.info("{}{} was saved!", T_WITH_NAME, technologyRequest.getName());
    }

    public TechnologyDetailsResponse getTechnologyDetails(Long id) {
        return technologyRepository.findById(id)
                .map(technologyMapper::mapToDetailsResponse)
                .orElseThrow(() -> new TechnologyNotFound(T_WITH_ID + id + NOT_FOUND));
    }

    public List<TechnologyResponse> getAllTechnologies() {
        return technologyRepository.findAll()
                .stream()
                .map(technologyMapper::mapToResponse)
                .toList();
    }

    public void addTechnology(TechnologyRequest technologyRequest) {
        Technology technology = Technology.builder()
                .name(technologyRequest.getName())
                .build();

        technologyRepository.save(technology);
        log.info("{}{} is saved!", T_WITH_NAME, technology.getName());
    }

    public List<TechnologyCount> getCountOfUsingTechnology() {
        return technologyRepository.getCountOfUsingTechnology();
    }

    Long getTechnologyId(String name) {
        return technologyRepository.findByName(name)
                .map(Technology::getId)
                .orElseThrow(() -> new TechnologyNotFound(T_WITH_NAME + name + NOT_FOUND));
    }
}
