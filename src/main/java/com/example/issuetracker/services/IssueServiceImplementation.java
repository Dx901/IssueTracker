package com.example.issuetracker.services;

import com.example.issuetracker.models.Issue;
import com.example.issuetracker.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Implementation of the IssueService interface
@Service
public class IssueServiceImplementation implements IssueService {

    // Repository for accessing and managing Issue entities
    private final IssueRepository issueRepository;

    @Autowired
    // Constructor-based dependency injection for IssueRepository
    public IssueServiceImplementation(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    // Create a new issue
    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    // Update an existing issue by ID
    public Issue updateIssue(Long issueId, Issue updatedIssue) {
        // Retrieve the existing issue by ID, or throw an exception if not found
        Issue existingIssue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found for ID: " + issueId));

        // Update the properties of the existing issue with the values from the updated issue
        existingIssue.setTitle(updatedIssue.getTitle());
        existingIssue.setDescription(updatedIssue.getDescription());
        existingIssue.setPriority(updatedIssue.getPriority());

        // Save and return the updated issue
        return issueRepository.save(existingIssue);
    }

    @Override
    // Retrieve an issue by ID
    public Issue getIssueById(Long issueId) {
        // Retrieve the issue by ID, or throw an exception if not found
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found for ID: " + issueId));
    }

    @Override
    // Retrieve all issues
    public Iterable<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    // Delete an issue by ID
    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }
}
