package com.heavenhr.recruiting.module.recruiting.rest.controller;


import com.heavenhr.recruiting.module.recruiting.domain.dto.JobOfferRequest;
import com.heavenhr.recruiting.module.recruiting.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/offer")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @Autowired
    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobOffers(Pageable pageable){
        return ResponseEntity.ok(jobOfferService.getAllJobOffer(pageable));
    }

    @RequestMapping(value= "/{jobOfferId:[\\d]+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllJobOfferById(@PathVariable("jobOfferId") Long jobOfferId){
        return ResponseEntity.ok(jobOfferService.getJobOfferById(jobOfferId));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createJobOffer(@Valid @RequestBody JobOfferRequest jobOfferRequest){
        return new ResponseEntity(jobOfferService.createJobOffer(jobOfferRequest), HttpStatus.CREATED);
    }

    @RequestMapping(value= "/{jobOfferId:[\\d]+}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteJobOfferById(@PathVariable("jobOfferId") Long jobOfferId){
        jobOfferService.deleteJobOfferById(jobOfferId);
        return ResponseEntity.ok().build();
    }
}
