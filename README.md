# Recruiting API v1.

This is a Java / Maven / Spring Boot (version 1.5.9.RELEASE) application that has been created for Simple Job Offer and Job Application management System.

## Project Structure
![alt text](https://github.com/tjwau19/o2esports/raw/master/recruiting_project_structure.JPG)

## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:

#### Maven
From the repository base folder run:
```
mvn spring-boot:run
```

#### Java standalone executable
```
mvn clean package
java -jar target/recruiting-api-0.0.1-SNAPSHOT.jar
```

After running the jar you will see the REST Api running on **Port 8080**

```
2017-12-22 18:49:47.059  INFO 6960 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-12-22 18:49:47.064  INFO 6960 --- [           main] c.h.r.c.RecruitingApiWebApplication      : Started RecruitingApiWebApplication in 5.185 seconds (JVM running for 5.826)        : Started Application in 22.285 seconds (JVM running for 23.032)
```

## About the Service

The service is just a simple Job Recruiting REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. After executing the jar file REST api will be available on follwing base URL  ```http://localhost:8080/api/v1``` (see below)


##### Features

* User can to create a job offer and read a single and list all offers.
* Candidate has to be able to apply for an offer.
* User can read one and list all applications per offer.
* User can progress the status of an application.
* User can track the number of applications.
* Application Status change triggers a notification (Logged to the STDOUT)

##### Additional Features
* Paginated results for example ```http://localhost:8080/api/v1/offer?page=0&size=10```
* User can delete Job Offer and Job Applications.

Here are some endpoints you can call:

### Create a Job Offer

```
POST /api/v1/offer
Content-Type: application/json

{
    "jobTitle": "Job Offer 1",
	"startDate": "2017-12-13"
}

RESPONSE: HTTP 201 (Created)
{
    "id": 1,
    "jobTitle": "Job Offer 1",
    "startDate": "2017-12-13",
    "numberOfApplications": 0
}
```

### Retrieve a paginated list Job Offer

```
http://localhost:8080/api/v1/offer?page=0&size=10

Response: HTTP 200
{
    "content": [
        {
            "id": 1,
            "jobTitle": "Job Offer 1",
            "startDate": "2017-12-13",
            "numberOfApplications": 0
        },
        {
            "id": 2,
            "jobTitle": "Job Offer 2",
            "startDate": "2017-12-25",
            "numberOfApplications": 0
        }
    ],
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": null,
    "first": true,
    "numberOfElements": 2
}
```

### Create a Job Application for Job Offer Id 1

```
POST /api/v1/application
Content-Type: application/json

{
	"relatedOfferId": 1,
	"candidateEmail": "sample@abc.com",
	"resumeText": "This is a sample resume"
}

RESPONSE: HTTP 201 (Created)
{
    "id": 1,
    "relatedJobOfferId": 1,
    "candidateEmail": "sample@abc.com",
    "resumeText": "This is a sample resume",
    "applicationStatus": "APPLIED"
}
```

### Retrieve a paginated list of all Job Applications

```
http://localhost:8080/api/v1/application?page=0&size=10

Response: HTTP 200
{
    "content": [
        {
            "id": 1,
            "relatedJobOfferId": 1,
            "candidateEmail": "sample@abc.com",
            "resumeText": "This is a sample resume",
            "applicationStatus": "APPLIED"
        },
        {
            "id": 2,
            "relatedJobOfferId": 2,
            "candidateEmail": "sample@abc.com",
            "resumeText": "This is a sample resume",
            "applicationStatus": "APPLIED"
        }
    ],
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 20,
    "number": 0,
    "sort": null,
    "first": true,
    "numberOfElements": 2
}
```

### Retrieve a paginated list of all Job Applications by related offer id

```
http://localhost:8080/api/v1/application?offerId=1&page=0&size=10

Response: HTTP 200
{
    "content": [
        {
            "id": 1,
            "relatedJobOfferId": 1,
            "candidateEmail": "sample@abc.com",
            "resumeText": "This is a sample resume",
            "applicationStatus": "APPLIED"
        }
    ],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 10,
    "number": 0,
    "sort": null,
    "first": true,
    "numberOfElements": 1
}
```

### Update Job Application Status

```
PUT /api/v1/application/1
Content-Type: application/json

{
	"applicationStatus": "INVITED"
}

RESPONSE: HTTP 200 (OK)
{
    "id": 1,
    "relatedJobOfferId": 1,
    "candidateEmail": "sample@abc.com",
    "resumeText": "This is a sample resume",
    "applicationStatus": "INVITED"
}
```

## Test the Service

The REST Api has been tested using the Spring Junit Framework, There are two integration tests in the project -

* JobOfferRestApiIntegrationTest.java
* JobApplicationRestApiIntegrationTest.java

##### JobOfferRestApiIntegrationTest.java

This test class have the test cases for all the api operations related to Job Offer API Endpoint, It have following test cases -

* testAGetJobOfferResponse() - Checks GET Job Offer Api Endpoint
* testBCreateNewJobOffer()  - Checks create new Job Offer Api Endpoint
* testCCreateNewJobOfferWithDuplicateJobTitle()  - Checks create new Job Offer with duplicate job title

##### JobApplicationRestApiIntegrationTest.java

This test class have the test cases for all the api operations related to Job Application API Endpoint, It have following test cases -

* testAEmptyJobApplicationResponse() - Checks GET Job Application Api Endpoint for Empty page response.
* testBCreateNewJobApplication()  - Checks create new Job Application Api Endpoint
* testCCreateNewJobApplicationWithDuplicateCandidateEmail()  - Checks create new Job Application with duplicate candidate id and related offer id
* testDUpdateJobApplicationStatus()  - Checks update application status Api endpoint

![alt text](https://github.com/tjwau19/o2esports/raw/master/recruiting_project_structure.JPG)