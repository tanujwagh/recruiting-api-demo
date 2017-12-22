package com.heavenhr.recruiting.module.recruiting.domain.repository;

import com.heavenhr.recruiting.module.recruiting.domain.modal.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    Optional<JobOffer> findByJobTitleEquals(String jobTitle);
    Optional<JobOffer> findByIdEquals(Long id);
}
