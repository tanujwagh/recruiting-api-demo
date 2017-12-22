package com.heavenhr.recruiting.module.recruiting.events;

import org.springframework.context.ApplicationEvent;

public class JobApplicationStatusChangeEvent extends ApplicationEvent {

    private JobApplicationStatusChangeEventDetails eventDetails;

    public JobApplicationStatusChangeEvent(Object source, JobApplicationStatusChangeEventDetails eventDetails) {
        super(source);
        this.eventDetails = eventDetails;
    }

    public JobApplicationStatusChangeEventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(JobApplicationStatusChangeEventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
}
