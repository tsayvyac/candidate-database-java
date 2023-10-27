package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.technology.TechnologyDetailsResponse;
import com.tsayvyac.task.dto.technology.TechnologyRequest;
import com.tsayvyac.task.dto.technology.TechnologyResponse;
import com.tsayvyac.task.exception.TechnologyException;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.PageableTechnologyRepository;
import com.tsayvyac.task.repository.TechnologyRepository;
import com.tsayvyac.task.repository.pojo.TechnologyCount;
import com.tsayvyac.task.dto.mapper.TechnologyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tsayvyac.task.util.Constant.*;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
class StandardTechnologyService implements TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final PageableTechnologyRepository pageableTechnologyRepository;
    private final TechnologyMapper technologyMapper;

    @Override
    public void deleteTechnology(Long id) {
        Optional<Technology> technologyToDelete = technologyRepository.findById(id);
        if (technologyToDelete.isPresent()) {
            technologyRepository.delete(technologyToDelete.get());
        } else throw new TechnologyException(T_WITH_ID + id + NOT_FOUND);
    }

    @Override
    public Technology updateTechnology(Long id, TechnologyRequest technologyRequest) {
        return technologyRepository.save(technologyRepository.findById(id)
                .map(technology -> {
                    technology.setName(technologyRequest.name());
                    log.info("{}{} was saved!", T_WITH_NAME, technologyRequest.name());
                    return technology;
                })
                .orElseThrow(() -> new TechnologyException(T_WITH_ID + id + NOT_FOUND))
        );
    }

    @Override
    public TechnologyDetailsResponse getTechnologyDetails(Long id) {
        return technologyRepository.findById(id)
                .map(technologyMapper::mapToDetailsResponse)
                .orElseThrow(() -> new TechnologyException(T_WITH_ID + id + NOT_FOUND));
    }

    @Override
    public List<TechnologyResponse> getAllTechnologies(Integer page) {
        Pageable allTechnologies = PageRequest
                .of(page - 1, 16, Sort.by("name"));
        return pageableTechnologyRepository.findAll(allTechnologies)
                .stream()
                .map(technologyMapper::mapToResponse)
                .toList();
    }

    @Override
    public Technology addTechnology(TechnologyRequest technologyRequest) {
        if (technologyRepository.findByName(technologyRequest.name()).isPresent())
            throw new TechnologyException(T_WITH_NAME + technologyRequest.name() + " already exist in database");

        Technology technology = Technology.builder()
                .name(technologyRequest.name())
                .build();
        log.info("{}{} is saved!", T_WITH_NAME, technology.getName());
        return technologyRepository.save(technology);
    }

    @Override
    public List<TechnologyCount> getCountOfUsingTechnology(Integer page) {
        Pageable allTechnologiesCounts = PageRequest
                .of(page - 1, 16, Sort.by("name"));
        return pageableTechnologyRepository.getCountOfUsingTechnology(allTechnologiesCounts);
    }

    Long getTechnologyId(String name) {
        return technologyRepository.findByName(name)
                .map(Technology::getId)
                .orElseThrow(() -> new TechnologyException(T_WITH_NAME + name + NOT_FOUND));
    }
}
