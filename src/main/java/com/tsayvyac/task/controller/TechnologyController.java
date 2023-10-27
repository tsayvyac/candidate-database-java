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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Technology controller")
@RestController
@RequestMapping("/api/tech")
@RequiredArgsConstructor
public class TechnologyController {
    private final TechnologyService technologyService;

    @Operation(summary = "Gets all technologies")
    @GetMapping(value = "/fetchAll/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TechnologyResponse>> getAllTechnologies(@PathVariable Integer page) {
        return ResponseEntity.ok(technologyService.getAllTechnologies(page));
    }

    @Operation(
            summary = "Gets technology details by ID",
            description = "Technology must exist"
    )
    @GetMapping(value = "/fetchDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TechnologyDetailsResponse> getTechnologyDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(technologyService.getTechnologyDetails(id));
    }

    @Operation(summary = "Gets a list with the name of technology and the count of candidates using it.")
    @GetMapping(value = "/stats/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TechnologyCount>> getCountOfUsingTechnology(@PathVariable Integer page) {
        return ResponseEntity.ok(technologyService.getCountOfUsingTechnology(page));
    }

    @Operation(summary = "Adds new technology")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Technology> addTechnology(@RequestBody TechnologyRequest technology) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(technologyService.addTechnology(technology));
    }

    @Operation(
            summary = "Updates technology by ID",
            description = "Technology must exist"
    )
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Technology> updateTechnology(@PathVariable Long id, @RequestBody TechnologyRequest technologyRequest) {
        return ResponseEntity.ok(technologyService.updateTechnology(id, technologyRequest));
    }

    @Operation(
            summary = "Deletes technology by ID",
            description = "Technology must exist"
    )
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        technologyService.deleteTechnology(id);
        return ResponseEntity.ok().build();
    }
}
