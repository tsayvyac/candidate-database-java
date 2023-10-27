package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.candidate.CandidateDetailsResponse;
import com.tsayvyac.task.dto.candidate.CandidateRequest;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyRequest;
import com.tsayvyac.task.exception.CandidateNotFound;
import com.tsayvyac.task.model.Candidate;
import com.tsayvyac.task.model.CandidateTechnologyKey;
import com.tsayvyac.task.repository.CandidateRepository;
import com.tsayvyac.task.dto.mapper.CandidateMapper;
import com.tsayvyac.task.repository.PageableCandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.tsayvyac.task.util.Constant.C_WITH_ID;
import static com.tsayvyac.task.util.Constant.NOT_FOUND;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
class StandardCandidateService implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final PageableCandidateRepository pageableCandidateRepository;
    private final CandidateMapper candidateMapper;
    private final StandardTechnologyService technologyService;
    private final CandidateUseTechnologyService cutService;

    @Override
    public void deleteCandidate(Long id) {
        Optional<Candidate> candidateToDelete = candidateRepository.findById(id);
        if (candidateToDelete.isPresent()) {
            candidateRepository.delete(candidateToDelete.get());
            log.info("{}{} was deleted!", C_WITH_ID, id);
        }  else throw new CandidateNotFound(C_WITH_ID + id + NOT_FOUND);
    }

    @Override
    public Candidate updateCandidate(Long id, CandidateRequest candidateRequest) {
        return candidateRepository.save(
                candidateRepository.findById(id)
                        .map(candidate -> {
                            candidate.setFirstName(candidateRequest.firstName());
                            candidate.setLastName(candidateRequest.lastName());
                            candidate.setAge(candidateRequest.age());

                            candidateRequest.technologies().forEach(ctr -> {
                                Long technologyId = technologyService.getTechnologyId(ctr.name());
                                cutService.updateCUT(new CandidateTechnologyKey(technologyId, id), ctr);
                            });

                            log.info("{}{} was saved!", C_WITH_ID, candidate.getId());
                            return candidate;
                        })
                        .orElseThrow(() -> new CandidateNotFound(C_WITH_ID + id + NOT_FOUND))
        );
    }

    @Override
    public CandidateDetailsResponse getCandidateDetails(Long id) {
        return candidateRepository.findById(id)
                .map(candidateMapper::mapToDetailsResponse)
                .orElseThrow(() -> new CandidateNotFound(C_WITH_ID + id + NOT_FOUND));
    }

    @Override
    public List<CandidateResponse> getAllCandidates(Integer page) {
        Pageable allCandidates = PageRequest
                .of(page - 1, 16, Sort.by("lastName"));
        return pageableCandidateRepository.findAll(allCandidates)
                .stream()
                .map(candidateMapper::mapToCandidateResponse)
                .toList();
    }

    @Override
    public Candidate addCandidate(CandidateRequest candidateRequest) {
        Candidate candidate = Candidate.builder()
                .firstName(candidateRequest.firstName())
                .lastName(candidateRequest.lastName())
                .age(candidateRequest.age())
                .build();

        candidateRepository.save(candidate);
        cutService.addToAssociativeTable(candidate, candidateRequest.technologies());
        log.info("{}{} was saved!", C_WITH_ID, candidate.getId());
        return candidate;
    }

    @Override
    public Candidate addNewCandidateTechnology(Long id, List<CandidateTechnologyRequest> candidateTechnologyRequests) {
         Candidate candidate = candidateRepository.findById(id)
                .map(c -> {
                    cutService.addToAssociativeTable(c, candidateTechnologyRequests);
                    return c;
                }).orElseThrow(() -> new CandidateNotFound(C_WITH_ID + id + NOT_FOUND));
        return candidateRepository.save(candidate);
    }
}
