package com.example.issuetracker.controller;

import com.example.issuetracker.constants.Priority;
import com.example.issuetracker.models.Issue;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;


//@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class issueControllerIntegrationTest {

    @Autowired
    public TestRestTemplate restTemplate;

    @LocalServerPort
    public int port;

    @Test
    public void testCreateIssueIntegration() {
        String baseUrl = "http://localhost:" + port + "/api/issues";

        // Test Case: Create Operation
        // Steps:
        // 1. Send a POST request to create an issue.
        // 2. Verify that the response status is OK.
        // 3. Verify that the created issue has the expected title and priority.
        Issue issue = createTestIssue("Integration Test Issue", Priority.LOW);
        ResponseEntity<Issue> createResponse = restTemplate.postForEntity(baseUrl, issue, Issue.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Issue createdIssue = createResponse.getBody();
        assertEquals("Integration Test Issue", createdIssue.getTitle());
        assertEquals(Priority.LOW, createdIssue.getPriority());
    }

    @Test
    public void testUpdateIssueIntegration() {
        String baseUrl = "http://localhost:" + port + "/api/issues";

        // Test Case: Update Operation
        // Steps:
        // 1. Create an issue.
        // 2. Send a PUT request to update the created issue.
        // 3. Verify that the response status is OK.
        // 4. Verify that the updated issue has the expected title and priority.
        Issue issue = createTestIssue("Integration Test Issue", Priority.LOW);
        ResponseEntity<Issue> createResponse = restTemplate.postForEntity(baseUrl, issue, Issue.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        Issue createdIssue = createResponse.getBody();
        String updateUrl = baseUrl + "/" + createdIssue.getId();
        Issue updatedIssue = createTestIssue("Updated Integration Test Issue", Priority.MEDIUM);
        HttpEntity<Issue> requestUpdate = new HttpEntity<>(updatedIssue);
        ResponseEntity<Issue> updateResponse = restTemplate.exchange(updateUrl, HttpMethod.PUT, requestUpdate, Issue.class);
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());

        Issue result = updateResponse.getBody();
        assertEquals("Updated Integration Test Issue", result.getTitle());
        assertEquals(Priority.MEDIUM, result.getPriority());
    }



    public Issue createTestIssue(String title, Priority priority) {
        Issue issue = new Issue();
        issue.setTitle(title);
        issue.setDescription("Test Description");
        issue.setPriority(priority);
        issue.setAssignee("Test Assignee");  // Set a valid assignee value
        return issue;
    }




}
