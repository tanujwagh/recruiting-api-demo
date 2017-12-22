package com.heavenhr.recruiting.module.recruiting.events.listeners;

import com.heavenhr.recruiting.module.recruiting.events.JobApplicationStatusChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationStatusChangeEventListener implements ApplicationListener<JobApplicationStatusChangeEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationStatusChangeEventListener.class);

    @Override
    public void onApplicationEvent(JobApplicationStatusChangeEvent jobApplicationStatusChangeEvent) {
        final String message = "Status has been updated for Job Application Id : " + jobApplicationStatusChangeEvent.getEventDetails().getJobApplicationId() +
                                "\n" +
                                " Candidate Email : " + jobApplicationStatusChangeEvent.getEventDetails().getCandidateEmail() +
                                "\n" +
                                "Old Application Status : " + jobApplicationStatusChangeEvent.getEventDetails().getJobApplicationOldStatus().toString() +
                                "\n" +
                                "New Application Status : " + jobApplicationStatusChangeEvent.getEventDetails().getJobApplicationNewStatus().toString();
        LOGGER.info(message);
    }
}
