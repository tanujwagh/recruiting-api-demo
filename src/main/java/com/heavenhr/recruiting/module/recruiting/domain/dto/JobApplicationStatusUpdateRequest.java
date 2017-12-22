package com.heavenhr.recruiting.module.recruiting.domain.dto;

import com.heavenhr.recruiting.module.recruiting.common.JobApplicationStatus;

import javax.validation.constraints.NotNull;

public class JobApplicationStatusUpdateRequest {

    @NotNull
    private JobApplicationStatus applicationStatus;

    public JobApplicationStatusUpdateRequest() {
    }

    public JobApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(JobApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
