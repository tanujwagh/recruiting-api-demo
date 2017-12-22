package com.heavenhr.recruiting.module.recruiting.service;

import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferAlreadyExistsException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferNotFoundException;
import com.heavenhr.recruiting.module.recruiting.domain.dto.JobOfferRequest;
import com.heavenhr.recruiting.module.recruiting.domain.modal.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobOfferService {
    Page<JobOffer> getAllJobOffer(Pageable pageable);
    JobOffer getJobOfferById(Long id) throws JobOfferNotFoundException;
    JobOffer createJobOffer(JobOfferRequest jobOfferRequest) throws JobOfferAlreadyExistsException;
    void deleteJobOfferById(Long id) throws JobOfferNotFoundException;
}
