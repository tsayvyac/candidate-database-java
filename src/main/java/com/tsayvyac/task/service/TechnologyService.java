package com.tsayvyac.task.service;

import com.tsayvyac.task.dto.technology.TechnologyDetailsResponse;
import com.tsayvyac.task.dto.technology.TechnologyRequest;
import com.tsayvyac.task.dto.technology.TechnologyResponse;
import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.pojo.TechnologyCount;

import java.util.List;

public interface TechnologyService {

    /**
     * Deletes the technology with ID from the database.
     * @param id ID of the technology to be deleted.
     */
    void deleteTechnology(Long id);

    /**
     * Updates the information of the technology with ID using the provided technology request data.
     *
     * @param id              ID of the technology to be updated.
     * @param technologyRequest The technology request object containing the updated information.
     */
    Technology updateTechnology(Long id, TechnologyRequest technologyRequest);

    /**
     * Retrieves detailed information about the technology with ID.
     *
     * @param id ID of the technology for which details are to be retrieved.
     * @return An object {@link TechnologyDetailsResponse} representing response that contains the detailed information about the technology.
     */
    TechnologyDetailsResponse getTechnologyDetails(Long id);

    /**
     * Retrieves all technologies stores in the database.
     *
     * @return A list of {@link TechnologyResponse} objects representing the technologies.
     */
    List<TechnologyResponse> getAllTechnologies(Integer page);

    /**
     * Adds a new technology to the database using the provided technology request data.
     *
     * @param technologyRequest The technology request object containing the technology's information.
     */
    Technology addTechnology(TechnologyRequest technologyRequest);

    /**
     * Retrieves a list of {@link TechnologyCount} objects representing the count of candidates
     * using each technology in the database.
     *
     * @return A list of technology count information.
     */
    List<TechnologyCount> getCountOfUsingTechnology(Integer page);
}
