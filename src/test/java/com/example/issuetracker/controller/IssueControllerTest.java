package com.example.issuetracker.controller;

import com.example.issuetracker.models.Issue;
import com.example.issuetracker.services.IssueService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class IssueControllerTest {

    @Mock
    // Mocked IssueService to simulate behavior without hitting the actual database
    private IssueService issueService;

    @InjectMocks
    // Injecting the mocks into the IssueController
    private IssueController issueController;

    @Before
    // Initializing the mocks
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    // Test for creating an issue and receiving a successful response
    public void testCreateIssue() {
        // Creating a new issue
        Issue issue = new Issue();
        // Mocking the behavior of the issueService to return the created issue
        when(issueService.createIssue(any(Issue.class))).thenReturn(issue);

        // Invoking the createIssue method of the controller
        ResponseEntity<?> response = issueController.createIssue(issue);

        // Asserting that the HTTP status code is OK and the returned issue matches the expected issue
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(issue, response.getBody());
    }

    @Test
    // Test for creating an issue and receiving a bad request response
    public void testCreateIssueBadRequest() {
        // Mocking the behavior of the issueService to throw an exception with a specific message
        when(issueService.createIssue(any(Issue.class))).thenThrow(new IllegalArgumentException("Invalid issue"));

        // Invoking the createIssue method of the controller with an issue that triggers an exception
        ResponseEntity<?> response = issueController.createIssue(new Issue());

        // Asserting that the HTTP status code is BAD REQUEST and the returned message matches the expected message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid issue", response.getBody());
    }

    @Test
    // Test for updating an issue that is not found and receiving a not found response
    public void testUpdateIssueNotFound() {
        // Setting up the issueId and mocking the behavior of the issueService to throw an exception with a specific message
        Long issueId = 1L;
        when(issueService.updateIssue(eq(issueId), any(Issue.class)))
                .thenThrow(new IllegalArgumentException("Issue not found"));

        // Invoking the updateIssue method of the controller with an issueId that triggers an exception
        ResponseEntity<String> response = issueController.updateIssue(issueId, new Issue());

        // Asserting that the HTTP status code is NOT FOUND and the returned message matches the expected message
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Issue not found", response.getBody());
    }

    @Test
    // Test for retrieving an issue by ID and receiving a successful response
    public void testGetIssueById() {
        // Setting up the issueId, creating a new issue, and mocking the behavior of the issueService to return the issue
        Long issueId = 1L;
        Issue issue = new Issue();
        when(issueService.getIssueById(eq(issueId))).thenReturn(issue);

        // Invoking the getIssueById method of the controller
        ResponseEntity<?> response = issueController.getIssueById(issueId);

        // Asserting that the HTTP status code is OK and the returned issue matches the expected issue
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(issue, response.getBody());
    }

    @Test
    // Test for retrieving an issue by ID and receiving a bad request response
    public void testGetIssueByIdBadRequest() {
        // Setting up the issueId and mocking the behavior of the issueService to throw an exception with a specific message
        Long issueId = 1L;
        when(issueService.getIssueById(eq(issueId)))
                .thenThrow(new IllegalArgumentException("Invalid issue ID"));

        // Invoking the getIssueById method of the controller with an issueId that triggers an exception
        ResponseEntity<?> response = issueController.getIssueById(issueId);

        // Asserting that the HTTP status code is BAD REQUEST and the returned message matches the expected message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid issue ID", response.getBody());
    }
}
