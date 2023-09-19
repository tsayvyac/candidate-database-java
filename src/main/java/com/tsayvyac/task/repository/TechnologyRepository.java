package com.tsayvyac.task.repository;

import com.tsayvyac.task.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
