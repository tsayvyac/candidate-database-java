package com.tsayvyac.task.repository;

import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.pojo.TechnologyCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PageableTechnologyRepository extends PagingAndSortingRepository<Technology, Long> {

    @Query("SELECT new com.tsayvyac.task.repository.pojo.TechnologyCount(t.name, count(cut.technology.id)) FROM Technology AS t " +
            "LEFT JOIN CandidateUseTechnology AS cut ON t.id = cut.technology.id GROUP BY t.name")
    List<TechnologyCount> getCountOfUsingTechnology(Pageable pageable);
}
