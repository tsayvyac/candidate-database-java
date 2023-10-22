package com.tsayvyac.task.controller;

import com.tsayvyac.task.dto.technology.TechnologyDetailsResponse;
import com.tsayvyac.task.dto.technology.TechnologyRequest;
import com.tsayvyac.task.dto.technology.TechnologyResponse;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.pojo.TechnologyCount;
import com.tsayvyac.task.service.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Technology controller")
@RestController
@RequestMapping("/api/tech")
@RequiredArgsConstructor
public class TechnologyController {
    private final TechnologyService technologyService;

    @Operation(summary = "Gets all technologies")
    @GetMapping(value = "/fetchAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TechnologyResponse> getAllTechnologies() {
        return technologyService.getAllTechnologies();
    }

    @Operation(
            summary = "Gets technology details by ID",
            description = "Technology must exist"
    )
    @GetMapping(value = "/fetchDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TechnologyDetailsResponse getTechnologyDetailsById(@PathVariable Long id) {
        return technologyService.getTechnologyDetails(id);
    }

    @Operation(summary = "Gets a list with the name of technology and the count of candidates using it.")
    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TechnologyCount> getCountOfUsingTechnology() {
        return technologyService.getCountOfUsingTechnology();
    }

    @Operation(summary = "Adds new technology")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Technology addTechnology(@RequestBody TechnologyRequest technology) {
        return technologyService.addTechnology(technology);
    }

    @Operation(
            summary = "Updates technology by ID",
            description = "Technology must exist"
    )
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Technology updateTechnology(@PathVariable Long id, @RequestBody TechnologyRequest technologyRequest) {
        return technologyService.updateTechnology(id, technologyRequest);
    }

    @Operation(
            summary = "Deletes technology by ID",
            description = "Technology must exist"
    )
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
    }
}
