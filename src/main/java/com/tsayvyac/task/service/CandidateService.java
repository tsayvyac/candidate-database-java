package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.CandidateRequest;
import com.tsayvyac.task.dto.CandidateResponse;
import com.tsayvyac.task.dto.CandidateTechnologyRequest;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final TechnologyRepository technologyRepository;
    private final CandidateUseTechnologyRepository candidateUseTechnologyRepository;
    // TODO: Implement CandidateService...

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

    public void addCandidate(CandidateRequest candidateRequest) {
        Candidate candidate = Candidate.builder()
                .firstName(candidateRequest.getFirstName())
                .lastName(candidateRequest.getLastName())
                .age(candidateRequest.getAge())
                .build();
        candidateRepository.save(candidate);

//        addToAssociate(candidate, candidateRequest.getTechnologies());

        log.info("Candidate with id {} is saved!", candidate.getId());
    }

//    TODO: Rewrite this ugly
//    private void addToAssociate(Candidate candidate, List<CandidateTechnologyRequest> technologies) {
//        for (var t : technologies) {
//            Technology tech = technologyRepository.findByName(t.getName()).get();
//            CandidateUseTechnology cut = CandidateUseTechnology
//                    .builder()
//                    .candidate(candidate)
//                    .technology(tech)
//                    .level(t.getLevel())
//                    .note(t.getNote())
//                    .build();
//            candidate.addToSet(cut);
//            tech.addToSet(cut);
//            candidateUseTechnologyRepository.save(cut);
//        }
//    }
}
