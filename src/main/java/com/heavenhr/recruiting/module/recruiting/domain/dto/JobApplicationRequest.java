package com.heavenhr.recruiting.module.recruiting.domain.dto;

import com.heavenhr.recruiting.module.recruiting.validation.ValidOffer;
import com.heavenhr.recruiting.module.common.validation.ValidEmail;
import com.heavenhr.recruiting.module.recruiting.common.JobApplicationStatus;

import javax.validation.constraints.NotNull;

public class JobApplicationRequest {

    @NotNull
    @ValidOffer
    private Long relatedOfferId;

    @NotNull
    @ValidEmail
    private String candidateEmail;

    @NotNull
    private String resumeText;

    public JobApplicationRequest() {
    }

    public Long getRelatedOfferId() {
        return relatedOfferId;
    }

    public void setRelatedOfferId(Long relatedOfferId) {
        this.relatedOfferId = relatedOfferId;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }
}
