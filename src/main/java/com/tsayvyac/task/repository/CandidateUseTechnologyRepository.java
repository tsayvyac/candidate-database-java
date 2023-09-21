package com.tsayvyac.task.repository;

import com.tsayvyac.task.model.CandidateTechnologyKey;
import com.tsayvyac.task.model.CandidateUseTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateUseTechnologyRepository extends JpaRepository<CandidateUseTechnology, CandidateTechnologyKey> {
}
