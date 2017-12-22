package com.heavenhr.recruiting.module.recruiting.rest.controller;

import com.heavenhr.recruiting.module.recruiting.domain.dto.JobApplicationRequest;
import com.heavenhr.recruiting.module.recruiting.domain.dto.JobApplicationStatusUpdateRequest;
import com.heavenhr.recruiting.module.recruiting.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobApplications(@RequestParam(value = "offerId", required = false) Long offerId, Pageable pageable){
        if(offerId != null){
            return ResponseEntity.ok(jobApplicationService.getAllJobApplicationsByOfferId(offerId, pageable));
        }
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications(pageable));
    }

    @RequestMapping(value= "/{jobApplicationId:[\\d]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getJobApplicationById(@PathVariable("jobApplicationId") Long id){
        return ResponseEntity.ok(jobApplicationService.getJobApplicationById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJobApplication(@Valid @RequestBody JobApplicationRequest jobApplicationRequest){
        return new ResponseEntity(jobApplicationService.createJobApplication(jobApplicationRequest), HttpStatus.CREATED);
    }

    @RequestMapping(value= "/{jobApplicationId:[\\d]+}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateJobApplication(@PathVariable("jobApplicationId") Long id, @Valid @RequestBody JobApplicationStatusUpdateRequest jobApplicationStatusUpdateRequest){
        return ResponseEntity.ok(jobApplicationService.updateJobApplicationStatus(id, jobApplicationStatusUpdateRequest));
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteJobApplication(@PathVariable("jobApplicationId") Long id){
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.ok().build();
    }
}
