package com.heavenhr.recruiting.module.recruiting.events.publisher;

import com.heavenhr.recruiting.module.recruiting.events.JobApplicationStatusChangeEvent;
import com.heavenhr.recruiting.module.recruiting.events.JobApplicationStatusChangeEventDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationStatusChangeEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public JobApplicationStatusChangeEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishJobApplicationStatusChangeEvent(Object source, JobApplicationStatusChangeEventDetails details){
        JobApplicationStatusChangeEvent event = new JobApplicationStatusChangeEvent(source, details);
        eventPublisher.publishEvent(event);
    }
}
