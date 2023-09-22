package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.candidate.CandidateDetailsResponse;
import com.tsayvyac.task.dto.candidate.CandidateRequest;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyRequest;
import com.tsayvyac.task.exception.CandidateNotFound;
import com.tsayvyac.task.model.Candidate;
import com.tsayvyac.task.model.CandidateTechnologyKey;
import com.tsayvyac.task.repository.CandidateRepository;
import com.tsayvyac.task.service.mapper.CandidateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tsayvyac.task.util.Constant.C_WITH_ID;
import static com.tsayvyac.task.util.Constant.NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final TechnologyService technologyService;
    private final CandidateUseTechnologyService cutService;

    public void deleteCandidate(Long id) {
        Optional<Candidate> candidateToDelete = candidateRepository.findById(id);
        if (candidateToDelete.isPresent()) {
            candidateRepository.delete(candidateToDelete.get());
            log.info("{}{} was deleted!", C_WITH_ID, id);
        }  else throw new CandidateNotFound(C_WITH_ID + id + NOT_FOUND);
    }

    public void updateCandidate(Long id, CandidateRequest candidateRequest) {
        candidateRepository.save(
                candidateRepository.findById(id)
                        .map(candidate -> {
                            candidate.setFirstName(candidateRequest.getFirstName());
                            candidate.setLastName(candidateRequest.getLastName());
                            candidate.setAge(candidateRequest.getAge());

                            candidateRequest.getTechnologies().forEach(ctr -> {
                                Long technologyId = technologyService.getTechnologyId(ctr.getName());
                                cutService.updateCUT(new CandidateTechnologyKey(technologyId, id), ctr);
                            });

                            log.info("{}{} was saved!", C_WITH_ID, candidate.getId());
                            return candidate;
                        })
                        .orElseThrow(() -> new CandidateNotFound(C_WITH_ID + id + NOT_FOUND))
        );
    }

    public CandidateDetailsResponse getCandidateDetails(Long id) {
        return candidateRepository.findById(id)
                .map(candidateMapper::mapToDetailsResponse)
                .orElseThrow(() -> new CandidateNotFound(C_WITH_ID + id + NOT_FOUND));
    }

    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(candidateMapper::mapToCandidateResponse)
                .toList();
    }

    public void addCandidate(CandidateRequest candidateRequest) {
        Candidate candidate = Candidate.builder()
                .firstName(candidateRequest.getFirstName())
                .lastName(candidateRequest.getLastName())
                .age(candidateRequest.getAge())
                .build();

        candidateRepository.save(candidate);
        cutService.addToAssociativeTable(candidate, candidateRequest.getTechnologies());
        log.info("{}{} was saved!", C_WITH_ID, candidate.getId());
    }

    public void addNewCandidateTechnology(Long id, List<CandidateTechnologyRequest> candidateTechnologyRequests) {
        Candidate candidate = candidateRepository.findById(id)
                .map(c -> {
                    cutService.addToAssociativeTable(c, candidateTechnologyRequests);
                    return c;
                }).orElseThrow(() -> new CandidateNotFound(C_WITH_ID + id + NOT_FOUND));
        candidateRepository.save(candidate);
    }
}
