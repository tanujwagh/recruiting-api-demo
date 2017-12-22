package com.heavenhr.recruiting;

import com.heavenhr.recruiting.container.RecruitingApiWebApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecruitingApiWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobApplicationRestApiIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() throws Exception {
        headers.add("content-type", "application/json");
        String jobOfferRequestJSON = "{\n" +
                "\t\"jobTitle\": \"Job Offer 1\",\n" +
                "\t\"startDate\": \"2017-12-13\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(jobOfferRequestJSON, headers);

        restTemplate.exchange(
                createURLWithPort("/offer"),
                HttpMethod.POST, entity, String.class);
    }

    @Test
    public void testAEmptyJobApplicationResponse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/application"),
                HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());

        Assert.assertEquals(0, jsonObject.get("totalElements"));
    }

    @Test
    public void testBCreateNewJobApplication() throws JSONException, ParseException {

        headers.add("content-type", "application/json");
        String jobOfferRequestJSON = "{\n" +
                "\t\"relatedOfferId\": 1,\n" +
                "\t\"candidateEmail\": \"sample@abc.com\",\n" +
                "\t\"resumeText\": \"This is a sample resume\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(jobOfferRequestJSON, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/application"),
                HttpMethod.POST, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());

        Assert.assertEquals("sample@abc.com", jsonObject.getString("candidateEmail"));
        Assert.assertEquals("This is a sample resume", jsonObject.getString("resumeText"));
        Assert.assertEquals(1, jsonObject.getLong("id"));
        Assert.assertEquals("APPLIED", jsonObject.get("applicationStatus"));
    }

    @Test
    public void testCCreateNewJobApplicationWithDuplicateCandidateEmail() throws JSONException, ParseException, IOException {

        headers.add("content-type", "application/json");
        String jobOfferRequestJSON = "{\n" +
                "\t\"relatedOfferId\": 1,\n" +
                "\t\"candidateEmail\": \"sample@abc.com\",\n" +
                "\t\"resumeText\": \"This is a sample resume\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(jobOfferRequestJSON, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/application"),
                HttpMethod.POST, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());

        Assert.assertEquals("Job Application with candidate email : sample@abc.com already exists.", jsonObject.get("message"));
    }

    @Test
    public void testDUpdateJobApplicationStatus() throws JSONException, ParseException, IOException {

        headers.add("content-type", "application/json");
        String jobOfferRequestJSON = "{\n" +
                "\t\"applicationStatus\": \"INVITED\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(jobOfferRequestJSON, headers);

        ResponseEntity<String> oldResponse = restTemplate.exchange(
                createURLWithPort("/application/1"),
                HttpMethod.GET, entity, String.class);

        ResponseEntity<String> newResponse = restTemplate.exchange(
                createURLWithPort("/application/1"),
                HttpMethod.PUT, entity, String.class);

        JSONObject jsonObjectOld = new JSONObject(oldResponse.getBody());
        JSONObject jsonObjectNew = new JSONObject(newResponse.getBody());

        Assert.assertNotEquals(jsonObjectOld.getString("applicationStatus"), jsonObjectNew.getString("applicationStatus"));
        Assert.assertEquals("APPLIED", jsonObjectOld.getString("applicationStatus"));
        Assert.assertEquals("INVITED", jsonObjectNew.getString("applicationStatus"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api/v1" +  uri;
    }
}
