package com.example.issuetracker.service;

import com.example.issuetracker.constants.Priority;
import com.example.issuetracker.models.Issue;
import com.example.issuetracker.repository.IssueRepository;
import com.example.issuetracker.services.IssueServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueServiceImplementation issueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateIssue() {
        Issue issue = new Issue();
        issue.setTitle("Sample Issue");
        issue.setPriority(Priority.HIGH);

        when(issueRepository.save(any(Issue.class))).thenReturn(issue);

        Issue createdIssue = issueService.createIssue(issue);

        assertEquals("Sample Issue", createdIssue.getTitle());
        assertEquals(Priority.HIGH, createdIssue.getPriority());
    }



    @Test
    public void testUpdateIssue() {
        Issue existingIssue = new Issue();
        existingIssue.setId(1L);
        existingIssue.setTitle("Existing Issue");
        existingIssue.setPriority(Priority.LOW);

        Issue updatedIssue = new Issue();
        updatedIssue.setTitle("Updated Issue");
        updatedIssue.setPriority(Priority.MEDIUM);

        when(issueRepository.findById(1L)).thenReturn(java.util.Optional.of(existingIssue));
        when(issueRepository.save(any(Issue.class))).thenReturn(updatedIssue);

        Issue result = issueService.updateIssue(existingIssue.getId(), updatedIssue);

        assertEquals("Updated Issue", result.getTitle());
        assertEquals(Priority.MEDIUM, result.getPriority());
    }

    @Test
    public void testGetIssueById() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Test Issue");
        issue.setPriority(Priority.MEDIUM);

        when(issueRepository.findById(1L)).thenReturn(java.util.Optional.of(issue));

        Issue result = issueService.getIssueById(1L);

        assertEquals("Test Issue", result.getTitle());
        assertEquals(Priority.MEDIUM, result.getPriority());
    }



}
