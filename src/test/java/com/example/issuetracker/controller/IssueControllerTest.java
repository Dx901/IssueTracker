package com.example.issuetracker.controller;

import com.example.issuetracker.models.Issue;
import com.example.issuetracker.services.IssueService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(IssueController.class)
public class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateIssue() {
        Issue issue = new Issue();
        when(issueService.createIssue(any(Issue.class))).thenReturn(issue);

        ResponseEntity<?> response = issueController.createIssue(issue);

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
    public void testUpdateIssue() {
        Long issueId = 1L;
        Issue updatedIssue = new Issue();
        when(issueService.updateIssue(eq(issueId), any(Issue.class))).thenReturn(updatedIssue);

        ResponseEntity<String> response = issueController.updateIssue(issueId, new Issue());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(String.valueOf(updatedIssue), response.getBody());
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

        ResponseEntity<?> response = issueController.getIssueById(issueId);

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

    @Test
    public void testGetAllIssues() {
        when(issueService.getAllIssues()).thenReturn(Arrays.asList(new Issue(), new Issue()));

        ResponseEntity<Iterable<Issue>> response = issueController.getAllIssues();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((Iterable<Issue>) response.getBody()).spliterator().getExactSizeIfKnown());
    }

    private IssueController verify(IssueService issueService, VerificationMode times) {
        return null;
    }

}
