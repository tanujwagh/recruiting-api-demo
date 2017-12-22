package com.heavenhr.recruiting.module.recruiting.service.impl;

import com.heavenhr.recruiting.module.recruiting.domain.dto.JobOfferRequest;
import com.heavenhr.recruiting.module.recruiting.domain.modal.JobOffer;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferAlreadyExistsException;
import com.heavenhr.recruiting.module.recruiting.exceptions.JobOfferNotFoundException;
import com.heavenhr.recruiting.module.recruiting.service.JobOfferService;
import com.heavenhr.recruiting.module.recruiting.domain.repository.JobApplicationRepository;
import com.heavenhr.recruiting.module.recruiting.domain.repository.JobOfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper mapper;

    @Autowired
    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository, JobApplicationRepository jobApplicationRepository, ModelMapper mapper) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<JobOffer> getAllJobOffer(Pageable pageable) {
        return jobOfferRepository.findAll(pageable);
    }

    @Override
    public JobOffer getJobOfferById(Long id) throws JobOfferNotFoundException {
        return jobOfferRepository.findByIdEquals(id).orElseThrow(() -> new JobOfferNotFoundException("Job Offer Id : " + id + " not found."));
    }

    @Transactional
    @Override
    public JobOffer createJobOffer(JobOfferRequest jobOfferRequest) throws JobOfferAlreadyExistsException {
        JobOffer jobOffer = jobOfferRepository.findByJobTitleEquals(jobOfferRequest.getJobTitle()).orElse(null);
        if(jobOffer != null){
            throw new JobOfferAlreadyExistsException("Job offer with title : " + jobOfferRequest.getJobTitle() + " already exists.");
        }
        jobOffer = new JobOffer();
        mapper.map(jobOfferRequest, jobOffer);
        return jobOfferRepository.saveAndFlush(jobOffer);
    }

    @Transactional
    @Override
    public void deleteJobOfferById(Long id) throws JobOfferNotFoundException {
        JobOffer jobOffer = jobOfferRepository.findByIdEquals(id).orElseThrow(() -> new JobOfferNotFoundException("Job Offer Id : " + id + " not found."));
        jobApplicationRepository.deleteAllByRelatedJobOfferIdEquals(id);
        jobOfferRepository.delete(jobOffer);
    }
}
