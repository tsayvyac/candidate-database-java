package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.candidate.CandidateTechnologyRequest;
import com.tsayvyac.task.exception.AssociationNotFound;
import com.tsayvyac.task.exception.TechnologyException;
import com.tsayvyac.task.model.Candidate;
import com.tsayvyac.task.model.CandidateTechnologyKey;
import com.tsayvyac.task.model.CandidateUseTechnology;
import com.tsayvyac.task.repository.CandidateUseTechnologyRepository;
import com.tsayvyac.task.repository.TechnologyRepository;
import com.tsayvyac.task.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.tsayvyac.task.util.Constant.*;

import java.util.List;

@Service
@RequiredArgsConstructor
class CandidateUseTechnologyService {
    private final CandidateUseTechnologyRepository cutRepository;
    private final TechnologyRepository technologyRepository;

    void updateCUT(CandidateTechnologyKey key, CandidateTechnologyRequest ctr) {
        cutRepository.save(
                cutRepository.findById(key)
                        .map(candidateUseTechnology -> {
                            candidateUseTechnology.setLevel(Helper.checkLevelBounds(ctr.getLevel()));
                            candidateUseTechnology.setNote(ctr.getNote());
                            return candidateUseTechnology;
                        })
                        .orElseThrow(() ->
                                new AssociationNotFound("Relation between IDs " + key.getCandidateId() + " and " + key.getTechnologyId() + NOT_FOUND))
        );
    }

    void addToAssociativeTable(Candidate candidate, List<CandidateTechnologyRequest> candidateTechnologyRequests) {
        candidateTechnologyRequests.forEach(ctr -> {
            CandidateUseTechnology cut = technologyRepository.findByName(ctr.getName()).map(technology ->
                            CandidateUseTechnology.builder()
                                    .candidate(candidate)
                                    .technology(technology)
                                    .level(Helper.checkLevelBounds(ctr.getLevel()))
                                    .note(ctr.getNote())
                                    .build()
                    )
                    .orElseThrow(() ->
                            new TechnologyException(T_WITH_NAME + ctr.getName() + NOT_FOUND));
            cutRepository.save(cut);
        });
    }
}
