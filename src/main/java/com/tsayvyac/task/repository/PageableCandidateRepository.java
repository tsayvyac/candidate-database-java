package com.tsayvyac.task.repository;

import com.tsayvyac.task.model.Candidate;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageableCandidateRepository extends PagingAndSortingRepository<Candidate, Long> {
}
