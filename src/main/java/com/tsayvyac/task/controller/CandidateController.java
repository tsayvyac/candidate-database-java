package com.tsayvyac.task.controller;

import com.tsayvyac.task.dto.candidate.CandidateDetailsResponse;
import com.tsayvyac.task.dto.candidate.CandidateRequest;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyRequest;
import com.tsayvyac.task.model.Candidate;
import com.tsayvyac.task.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Candidate controller")
@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @Operation(summary = "Gets all candidates")
    @GetMapping(value = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateResponse> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @Operation(
            summary = "Gets candidate details by ID",
            description = "Candidate must exist"
    )
    @GetMapping(value = "/fetchDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CandidateDetailsResponse getCandidateDetailsById(@PathVariable Long id) {
        return candidateService.getCandidateDetails(id);
    }

    @Operation(
            summary = "Adds new candidate",
            description = "Technologies must exist"
    )
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate addCandidate(@RequestBody CandidateRequest candidate) {
        return candidateService.addCandidate(candidate);
    }

    @Operation(
            summary = "Adds new technologies to the candidate by ID",
            description = "Technologies and candidate must exist"
    )
    @PutMapping(value = "/addTech/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Candidate addNewCandidateTechnology(@PathVariable Long id, @RequestBody List<CandidateTechnologyRequest> candidateTechnologyRequests) {
        return candidateService.addNewCandidateTechnology(id, candidateTechnologyRequests);
    }

    @Operation(
            summary = "Updates candidate by ID",
            description = "Candidate must exist"
    )
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Candidate updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest candidateRequest) {
        return candidateService.updateCandidate(id, candidateRequest);
    }

    @Operation(
            summary = "Deletes candidate by ID",
            description = "Candidate must exist"
    )
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }
}
