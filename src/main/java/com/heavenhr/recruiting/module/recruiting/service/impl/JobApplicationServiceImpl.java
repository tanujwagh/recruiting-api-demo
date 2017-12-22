package com.heavenhr.recruiting.module.recruiting.service.impl;

import com.heavenhr.recruiting.module.recruiting.domain.dto.JobApplicationStatusUpdateRequest;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobApplicationNotFoundException;
import com.heavenhr.recruiting.module.recruiting.common.JobApplicationStatus;
import com.heavenhr.recruiting.module.recruiting.domain.dto.JobApplicationRequest;
import com.heavenhr.recruiting.module.recruiting.domain.modal.JobApplication;
import com.heavenhr.recruiting.module.recruiting.domain.repository.JobApplicationRepository;
import com.heavenhr.recruiting.module.recruiting.events.JobApplicationStatusChangeEventDetails;
import com.heavenhr.recruiting.module.recruiting.events.publisher.JobApplicationStatusChangeEventPublisher;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobApplicationAlreadyExistException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobApplicationNotFoundException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferAlreadyExistsException;
import com.heavenhr.recruiting.module.recruiting.service.JobApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationStatusChangeEventPublisher eventPublisher;
    private final ModelMapper mapper;

    @Autowired
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, JobApplicationStatusChangeEventPublisher eventPublisher, ModelMapper mapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.eventPublisher = eventPublisher;
        this.mapper = mapper;
    }

    @Override
    public Page<JobApplication> getAllJobApplications(Pageable pageable) {
        return jobApplicationRepository.findAll(pageable);
    }

    @Override
    public Page<JobApplication> getAllJobApplicationsByOfferId(Long offerId, Pageable pageable) {
        return jobApplicationRepository.findAllByRelatedJobOfferIdEquals(offerId, pageable);
    }

    @Override
    public JobApplication getJobApplicationById(Long id) throws JobApplicationNotFoundException {
        return jobApplicationRepository.findByIdEquals(id).orElseThrow(() -> new JobApplicationNotFoundException("Job Application id : " + id + " not found."));
    }

    @Transactional
    @Override
    public JobApplication createJobApplication(JobApplicationRequest jobApplicationRequest) throws JobApplicationAlreadyExistException {
        JobApplication jobApplication = jobApplicationRepository.findByRelatedJobOfferIdEqualsAndCandidateEmailEquals(jobApplicationRequest.getRelatedOfferId(), jobApplicationRequest.getCandidateEmail()).orElse(null);
        if(jobApplication != null){
            throw new JobApplicationAlreadyExistException("Job Application with candidate email : " + jobApplicationRequest.getCandidateEmail() + " already exists.");
        }
        jobApplication = new JobApplication();
        mapper.map(jobApplicationRequest, jobApplication);
        jobApplication.setApplicationStatus(JobApplicationStatus.APPLIED);
        return jobApplicationRepository.saveAndFlush(jobApplication);
    }

    @Transactional
    @Override
    public JobApplication updateJobApplicationStatus(Long id, JobApplicationStatusUpdateRequest jobApplicationStatusUpdateRequest) throws JobApplicationNotFoundException {
        JobApplication jobApplication = jobApplicationRepository.findByIdEquals(id).orElseThrow(() -> new JobApplicationNotFoundException("Job Application id : " + id + " not found."));
        if(jobApplication.getApplicationStatus() != jobApplicationStatusUpdateRequest.getApplicationStatus()){
            JobApplicationStatus jobApplicationApplicationOldStatus = jobApplication.getApplicationStatus();
            jobApplication.setApplicationStatus(jobApplicationStatusUpdateRequest.getApplicationStatus());
            jobApplication = jobApplicationRepository.saveAndFlush(jobApplication);
            publishJobApplicationStatusChangeEvent(jobApplicationApplicationOldStatus, jobApplication);
        }
        return jobApplication;
    }

    @Transactional
    @Override
    public void deleteJobApplication(Long id) throws JobApplicationNotFoundException {
        JobApplication jobApplication = jobApplicationRepository.findByIdEquals(id).orElseThrow(() -> new JobApplicationNotFoundException("Job Application : " + id + " not found."));
        jobApplicationRepository.delete(jobApplication);
    }

    private void publishJobApplicationStatusChangeEvent(JobApplicationStatus jobApplicationApplicationOldStatus, JobApplication jobApplication){
        JobApplicationStatusChangeEventDetails eventDetails = new JobApplicationStatusChangeEventDetails();
        eventDetails.setJobApplicationId(jobApplication.getId());
        eventDetails.setCandidateEmail(jobApplication.getCandidateEmail());
        eventDetails.setJobApplicationOldStatus(jobApplicationApplicationOldStatus);
        eventDetails.setJobApplicationNewStatus(jobApplication.getApplicationStatus());
        eventPublisher.publishJobApplicationStatusChangeEvent(this, eventDetails);
    }
}
