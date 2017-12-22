package com.heavenhr.recruiting.module.recruiting.domain.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class JobOfferRequest {

    @NotNull
    private String jobTitle;

    @NotNull
    private Date startDate;

    public JobOfferRequest() {
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
