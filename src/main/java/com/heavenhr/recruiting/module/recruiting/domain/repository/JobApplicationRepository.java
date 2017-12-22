package com.heavenhr.recruiting.module.recruiting.domain.repository;

import com.heavenhr.recruiting.module.recruiting.domain.modal.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Page<JobApplication> findAllByRelatedJobOfferIdEquals(Long offerId, Pageable pageable);
    Optional<JobApplication> findByRelatedJobOfferIdEqualsAndCandidateEmailEquals(Long relatedJobId, String candidateEmail);
    Optional<JobApplication> findByIdEquals(Long id);
    void deleteAllByRelatedJobOfferIdEquals(Long id);
}
