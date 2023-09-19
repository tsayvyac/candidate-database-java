package com.tsayvyac.task.controller;

import com.tsayvyac.task.dto.TechnologyRequest;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/tech")
@RequiredArgsConstructor
public class TechnologyController {
    private final TechnologyService technologyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Technology> getAllTechnologies() {
        // TODO: Implement getAllTechnologies
        return Collections.emptyList();
    }

//    TODO: How to get details? What is it supposed to look like?
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public Technology getTechnologyDetailsById(@PathVariable Long id) {
//
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTechnology(@RequestBody TechnologyRequest technology) {
        // TODO: Implement addTechnology
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateTechnology(@PathVariable Long id, @RequestBody TechnologyRequest technology) {
        // TODO: Implement updateTechnology
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTechnology(@PathVariable Long id) {
        // TODO: Implement deleteTechnology
    }
}
