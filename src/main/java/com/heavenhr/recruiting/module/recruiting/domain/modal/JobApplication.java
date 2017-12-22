package com.heavenhr.recruiting.module.recruiting.domain.modal;

import com.heavenhr.recruiting.module.common.domain.auditing.Auditable;
import com.heavenhr.recruiting.module.recruiting.common.JobApplicationStatus;

import javax.persistence.*;

@Entity
@Table(name="job_application",
        uniqueConstraints = @UniqueConstraint(columnNames={"job_offer_id", "candidate_email"}))
public class JobApplication extends Auditable<String> {
    private Long id;
    private Long relatedJobOfferId;
    private String candidateEmail;
    private String resumeText;
    private JobApplicationStatus applicationStatus;

    public JobApplication() {
    }

    @Id
    @GeneratedValue
    @Column(name="job_application_id", unique=true, nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="job_offer_id", nullable=false)
    public Long getRelatedJobOfferId() {
        return relatedJobOfferId;
    }

    public void setRelatedJobOfferId(Long relatedJobOfferId) {
        this.relatedJobOfferId = relatedJobOfferId;
    }

    @Column(name="candidate_email", nullable=false)
    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    @Lob
    @Column(name="resume_text", nullable=false)
    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="application_status", nullable=false)
    public JobApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(JobApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
