package com.heavenhr.recruiting.module.recruiting.validation;

import com.heavenhr.recruiting.module.recruiting.domain.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JobOfferValidator implements ConstraintValidator<ValidOffer, Long> {

    private final JobOfferRepository jobOfferRepository;

    @Autowired
    public JobOfferValidator(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @Override
    public void initialize(ValidOffer validOffer) {

    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return jobOfferRepository.findByIdEquals(id).isPresent();
    }
}