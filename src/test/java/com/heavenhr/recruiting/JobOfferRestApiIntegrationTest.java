package com.heavenhr.recruiting;

import com.heavenhr.recruiting.container.RecruitingApiWebApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
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
public class JobOfferRestApiIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testAGetJobOfferResponse() throws JSONException{

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/offer"),
                HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());

        Assert.assertEquals(1, jsonObject.get("totalElements"));
    }

    @Test
    public void testBCreateNewJobOffer() throws JSONException, ParseException {

        headers.add("content-type", "application/json");
        String jobOfferRequestJSON = "{\n" +
                "\t\"jobTitle\": \"Job Offer 2\",\n" +
                "\t\"startDate\": \"2017-12-15\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(jobOfferRequestJSON, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/offer"),
                HttpMethod.POST, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());

        Assert.assertEquals("Job Offer 2", jsonObject.getString("jobTitle"));
        Assert.assertEquals("2017-12-15", jsonObject.getString("startDate"));
        Assert.assertEquals(2, jsonObject.getLong("id"));
    }

    @Test
    public void testCCreateNewJobOfferWithDuplicateJobTitle() throws JSONException, ParseException, IOException {

        headers.add("content-type", "application/json");
        String jobOfferRequestJSON = "{\n" +
                "\t\"jobTitle\": \"Job Offer 1\",\n" +
                "\t\"startDate\": \"2017-12-13\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity(jobOfferRequestJSON, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/offer"),
                HttpMethod.POST, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());

        Assert.assertEquals("Job offer with title : Job Offer 1 already exists.", jsonObject.get("message"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api/v1" + uri;
    }
}

