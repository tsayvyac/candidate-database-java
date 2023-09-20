package com.tsayvyac.task.controller;

import com.tsayvyac.task.dto.CandidateRequest;
import com.tsayvyac.task.dto.CandidateResponse;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateResponse> getAllCandidates() {
        // TODO: Implement getAllCandidates
        return candidateService.getAllCandidates();
    }

//    TODO: How to get details? What is it supposed to look like?
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public Candidate getCandidateDetailsById(@PathVariable Long id) {
//
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCandidate(@RequestBody CandidateRequest candidate) {
        // TODO: Implement addCandidate
        candidateService.addCandidate(candidate);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest candidate) {
        // TODO: Implement updateCandidate
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidate(@PathVariable Long id) {
        // TODO: Implement deleteCandidate
    }
}
