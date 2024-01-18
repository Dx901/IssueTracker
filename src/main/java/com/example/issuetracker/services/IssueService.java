package com.example.issuetracker.services;

import com.example.issuetracker.models.Issue;

// Service interface defining operations for Issue entities
public interface IssueService {

    // Create a new issue
    Issue createIssue(Issue issue);

    // Update an existing issue by ID
    Issue updateIssue(Long issueId, Issue updatedIssue);

    // Retrieve an issue by ID
    Issue getIssueById(Long issueId);

    // Retrieve all issues
    Iterable<Issue> getAllIssues();

    // Delete an issue by ID
    void deleteIssue(Long issueId);
}
