package com.heavenhr.recruiting.module.recruiting.service;

import com.heavenhr.recruiting.module.recruiting.domain.dto.JobApplicationRequest;
import com.heavenhr.recruiting.module.recruiting.domain.dto.JobApplicationStatusUpdateRequest;
import com.heavenhr.recruiting.module.recruiting.domain.modal.JobApplication;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobApplicationNotFoundException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferAlreadyExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobApplicationService {
    Page<JobApplication> getAllJobApplications(Pageable pageable);
    Page<JobApplication> getAllJobApplicationsByOfferId(Long offerId, Pageable pageable);
    JobApplication getJobApplicationById(Long id) throws JobApplicationNotFoundException;
    JobApplication createJobApplication(JobApplicationRequest jobApplicationRequest) throws JobOfferAlreadyExistsException;
    JobApplication updateJobApplicationStatus(Long id, JobApplicationStatusUpdateRequest jobApplicationStatusUpdateRequest) throws JobApplicationNotFoundException;
    void deleteJobApplication(Long id) throws JobApplicationNotFoundException;
}
