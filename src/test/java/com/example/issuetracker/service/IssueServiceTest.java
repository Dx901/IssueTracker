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

/**
 * Unit tests for the IssueServiceImplementation class.
 */
public class IssueServiceTest {

    // Mock for simulating interactions with the database
    @Mock
    private IssueRepository issueRepository;

    // Object under test, which is the IssueServiceImplementation
    @InjectMocks
    private IssueServiceImplementation issueService;

    // Setting up mocks before each test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for creating a new issue.
     */
    @Test
    public void testCreateIssue() {
        // Creating a sample issue
        Issue issue = new Issue();
        issue.setTitle("Sample Issue");
        issue.setPriority(Priority.HIGH);

        // Mocking the behavior of the repository save method
        when(issueRepository.save(any(Issue.class))).thenReturn(issue);

        // Performing the createIssue operation
        Issue createdIssue = issueService.createIssue(issue);

        // Asserting that the created issue has the expected values
        assertEquals("Sample Issue", createdIssue.getTitle());
        assertEquals(Priority.HIGH, createdIssue.getPriority());
    }

    /**
     * Test case for updating an existing issue.
     */
    @Test
    public void testUpdateIssue() {
        // Creating an existing issue with low priority
        Issue existingIssue = new Issue();
        existingIssue.setId(1L);
        existingIssue.setTitle("Existing Issue");
        existingIssue.setPriority(Priority.LOW);

        // Creating an updated issue with medium priority
        Issue updatedIssue = new Issue();
        updatedIssue.setTitle("Updated Issue");
        updatedIssue.setPriority(Priority.MEDIUM);

        // Mocking the behavior of the repository findById and save methods
        when(issueRepository.findById(1L)).thenReturn(java.util.Optional.of(existingIssue));
        when(issueRepository.save(any(Issue.class))).thenReturn(updatedIssue);

        // Performing the updateIssue operation
        Issue result = issueService.updateIssue(existingIssue.getId(), updatedIssue);

        // Asserting that the updated issue has the expected values
        assertEquals("Updated Issue", result.getTitle());
        assertEquals(Priority.MEDIUM, result.getPriority());
    }

    /**
     * Test case for retrieving an issue by its ID.
     */
    @Test
    public void testGetIssueById() {
        // Creating a test issue with medium priority
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Test Issue");
        issue.setPriority(Priority.MEDIUM);

        // Mocking the behavior of the repository findById method
        when(issueRepository.findById(1L)).thenReturn(java.util.Optional.of(issue));

        // Performing the getIssueById operation
        Issue result = issueService.getIssueById(1L);

        // Asserting that the retrieved issue has the expected values
        assertEquals("Test Issue", result.getTitle());
        assertEquals(Priority.MEDIUM, result.getPriority());
    }
}
