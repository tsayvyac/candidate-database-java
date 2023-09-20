package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.candidate.CandidateRequest;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyRequest;
import com.tsayvyac.task.exception.CandidateNotFound;
import com.tsayvyac.task.exception.LevelBoundsException;
import com.tsayvyac.task.exception.TechnologyNotFound;
import com.tsayvyac.task.model.Candidate;
import com.tsayvyac.task.model.CandidateUseTechnology;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.CandidateRepository;
import com.tsayvyac.task.repository.CandidateUseTechnologyRepository;
import com.tsayvyac.task.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final TechnologyRepository technologyRepository;
    private final CandidateUseTechnologyRepository candidateUseTechnologyRepository;

    public void deleteCandidate(Long id) {
        Optional<Candidate> candidateToDelete = candidateRepository.findById(id);
        if (candidateToDelete.isPresent()) {
            candidateRepository.delete(candidateToDelete.get());
            log.info("Candidate with id {} was deleted!", id);
        }  else throw new CandidateNotFound("Candidate with ID " + id + " not found!");
    }

    public void updateCandidate(Long id, CandidateRequest candidateRequest) {
        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        if (candidateOpt.isPresent()) {
            Candidate candidate = candidateOpt.get();
            candidate.setFirstName(candidateRequest.getFirstName());
            candidate.setLastName(candidateRequest.getLastName());
            candidate.setAge(candidateRequest.getAge());
            // TODO: Updating level and note??
            candidateRepository.save(candidate);
            log.info("Candidate with ID {} was saved!", candidate.getId());
        } else throw new CandidateNotFound("Candidate with ID " + id + " not found!");
    }

    // TODO: Change the design of response candidates
    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CandidateResponse mapToResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .id(candidate.getId())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .age(candidate.getAge())
                .useTechnologies(candidate.getUseTechnologies())
                .build();
    }

    // TODO: Ability to add new technologies for existing candidate ?
    public void addCandidate(CandidateRequest candidateRequest) {
        Candidate candidate = Candidate.builder()
                .firstName(candidateRequest.getFirstName())
                .lastName(candidateRequest.getLastName())
                .age(candidateRequest.getAge())
                .build();

        // TODO: Add candidate even technology does not exist (bug or feature)
        candidateRepository.save(candidate);
        addToAssociate(candidate, candidateRequest.getTechnologies());
        log.info("Candidate with ID {} was saved!", candidate.getId());
    }

    private void addToAssociate(Candidate candidate, List<CandidateTechnologyRequest> candidateTechnologyRequests) {
        candidateTechnologyRequests.forEach(tech -> {
            Optional<Technology> technologyOpt = technologyRepository.findByName(tech.getName());
            if (technologyOpt.isPresent()) {
                CandidateUseTechnology cut = CandidateUseTechnology.builder()
                        .candidate(candidate)
                        .technology(technologyOpt.get())
                        .level(checkLevelBounds(tech.getLevel()))
                        .note(tech.getNote())
                        .build();

                candidateUseTechnologyRepository.save(cut);
            } else throw new TechnologyNotFound("Technology with name " + tech.getName() + " not fount!");
        });
    }

    private Integer checkLevelBounds(Integer level) {
        if (level >= 0 && level < 11)
            return level;
        else throw new LevelBoundsException("Level is out of bounds! The level limits are 0 - 10.");
    }
}
