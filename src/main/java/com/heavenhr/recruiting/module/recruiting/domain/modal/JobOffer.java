package com.heavenhr.recruiting.module.recruiting.domain.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heavenhr.recruiting.module.common.domain.auditing.Auditable;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="job_offer")
public class JobOffer extends Auditable<String> implements Serializable{
    private Long id;
    private String jobTitle;
    private Date startDate;
    private Integer numberOfApplications = new Integer(0);

    public JobOffer(){}

    @Id
    @GeneratedValue
    @Column(name="job_offer_id", unique=true, nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="job_title", unique=true, nullable=false)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="job_start_date", nullable=false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Formula("(select count(*) from job_application application where application.job_offer_id = job_offer_id)")
    public Integer getNumberOfApplications() {
        return numberOfApplications;
    }

    public void setNumberOfApplications(Integer numberOfApplications) {
        this.numberOfApplications = numberOfApplications;
    }
}
