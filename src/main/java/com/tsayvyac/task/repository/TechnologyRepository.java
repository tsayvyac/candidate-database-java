package com.tsayvyac.task.repository;

import com.tsayvyac.task.model.Technology;
import com.tsayvyac.task.repository.pojo.TechnologyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findByName(String name);

    @Query("SELECT new com.tsayvyac.task.repository.pojo.TechnologyCount(t.name, count(cut.technology.id)) FROM Technology AS t " +
            "LEFT JOIN CandidateUseTechnology AS cut ON t.id = cut.technology.id GROUP BY t.name")
    List<TechnologyCount> getCountOfUsingTechnology();
}
