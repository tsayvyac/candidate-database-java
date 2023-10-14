package com.tsayvyac.task.controller;

import com.tsayvyac.task.dto.candidate.CandidateDetailsResponse;
import com.tsayvyac.task.dto.candidate.CandidateRequest;
import com.tsayvyac.task.dto.candidate.CandidateResponse;
import com.tsayvyac.task.dto.candidate.CandidateTechnologyRequest;
import com.tsayvyac.task.model.Candidate;
import com.tsayvyac.task.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @GetMapping(value = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateResponse> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping(value = "/fetchDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CandidateDetailsResponse getCandidateDetailsById(@PathVariable Long id) {
        return candidateService.getCandidateDetails(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate addCandidate(@RequestBody CandidateRequest candidate) {
        return candidateService.addCandidate(candidate);
    }

    @PutMapping(value = "/addTech/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Candidate addNewCandidateTechnology(@PathVariable Long id, @RequestBody List<CandidateTechnologyRequest> candidateTechnologyRequests) {
        return candidateService.addNewCandidateTechnology(id, candidateTechnologyRequests);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Candidate updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest candidateRequest) {
        return candidateService.updateCandidate(id, candidateRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }
}
