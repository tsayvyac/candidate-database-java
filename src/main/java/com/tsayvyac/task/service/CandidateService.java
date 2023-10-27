package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.candidate.CandidateDetailsResponse;
import com.tsayvyac.task.dto.candidate.CandidateRequest;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyRequest;
import com.tsayvyac.task.model.Candidate;

import java.util.List;

public interface CandidateService {

    /**
     * Deletes the candidate with ID from the database.
     * @param id ID of the candidate to be deleted.
     */
    void deleteCandidate(Long id);

    /**
     * Updates the information of the candidate with ID using the provided candidate request data.
     *
     * @param id              ID of the candidate to be updated.
     * @param candidateRequest The candidate request object containing the updated information.
     */
    Candidate updateCandidate(Long id, CandidateRequest candidateRequest);

    /**
     * Retrieves detailed information about the candidate with ID.
     *
     * @param id ID of the candidate for which details are to be retrieved.
     * @return An object {@link CandidateDetailsResponse} representing response that contains the detailed information about the candidate.
     */
    CandidateDetailsResponse getCandidateDetails(Long id);

    /**
     * Retrieves all candidates stores in the database.
     *
     * @return A list of {@link CandidateResponse} objects representing the candidates.
     */
    List<CandidateResponse> getAllCandidates(Integer page);

    /**
     * Adds a new candidate to the database using the provided candidate request data.
     *
     * @param candidateRequest The candidate request object containing the candidate's information.
     */
    Candidate addCandidate(CandidateRequest candidateRequest);

    /**
     * Adds new technologies to an existing candidate using the provided candidate ID and technology requests.
     *
     * @param id                       ID of the candidate to whom technologies will be added.
     * @param candidateTechnologyRequests A list of {@link CandidateTechnologyRequest} objects representing the new technologies.
     */
    Candidate addNewCandidateTechnology(Long id, List<CandidateTechnologyRequest> candidateTechnologyRequests);
}
