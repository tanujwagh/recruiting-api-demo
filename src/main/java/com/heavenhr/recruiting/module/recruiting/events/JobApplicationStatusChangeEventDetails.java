package com.heavenhr.recruiting.module.recruiting.events;

import com.heavenhr.recruiting.module.recruiting.common.JobApplicationStatus;

import java.util.Date;

public class JobApplicationStatusChangeEventDetails {
    private Long jobApplicationId;
    private String candidateEmail;
    private JobApplicationStatus jobApplicationOldStatus;
    private JobApplicationStatus jobApplicationNewStatus;
    private Date timestamp;

    public JobApplicationStatusChangeEventDetails() {
    }

    public Long getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplicationId(Long jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public JobApplicationStatus getJobApplicationOldStatus() {
        return jobApplicationOldStatus;
    }

    public void setJobApplicationOldStatus(JobApplicationStatus jobApplicationOldStatus) {
        this.jobApplicationOldStatus = jobApplicationOldStatus;
    }

    public JobApplicationStatus getJobApplicationNewStatus() {
        return jobApplicationNewStatus;
    }

    public void setJobApplicationNewStatus(JobApplicationStatus jobApplicationNewStatus) {
        this.jobApplicationNewStatus = jobApplicationNewStatus;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
