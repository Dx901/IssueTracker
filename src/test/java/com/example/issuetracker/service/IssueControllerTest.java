package com.example.issuetracker.service;

import com.example.issuetracker.controller.IssueController;
import com.example.issuetracker.models.Issue;
import com.example.issuetracker.services.IssueService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateIssue() {
        Issue issue = new Issue();
        when(issueService.createIssue(any(Issue.class))).thenReturn(issue);

        ResponseEntity<Issue> response = (ResponseEntity<Issue>) issueController.createIssue(issue);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(issue, response.getBody());
    }

    @Test
    public void testCreateIssueBadRequest() {
        when(issueService.createIssue(any(Issue.class))).thenThrow(new IllegalArgumentException("Invalid issue"));

        ResponseEntity<?> response = issueController.createIssue(new Issue());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid issue", response.getBody());
    }



    @Test
    public void testUpdateIssueNotFound() {
        Long issueId = 1L;
        when(issueService.updateIssue(eq(issueId), any(Issue.class)))
                .thenThrow(new IllegalArgumentException("Issue not found"));

        ResponseEntity<String> response = issueController.updateIssue(issueId, new Issue());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Issue not found", response.getBody());
    }

    @Test
    public void testGetIssueById() {
        Long issueId = 1L;
        Issue issue = new Issue();
        when(issueService.getIssueById(eq(issueId))).thenReturn(issue);

        ResponseEntity<Issue> response = (ResponseEntity<Issue>) issueController.getIssueById(issueId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(issue, response.getBody());
    }

    @Test
    public void testGetIssueByIdBadRequest() {
        Long issueId = 1L;
        when(issueService.getIssueById(eq(issueId)))
                .thenThrow(new IllegalArgumentException("Invalid issue ID"));

        ResponseEntity<?> response = issueController.getIssueById(issueId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid issue ID", response.getBody());
    }


}
