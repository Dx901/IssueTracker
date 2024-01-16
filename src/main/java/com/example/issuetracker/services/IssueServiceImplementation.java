package com.example.issuetracker.services;

import com.example.issuetracker.models.Issue;
import com.example.issuetracker.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImplementation implements IssueService{

    @Autowired
    public IssueRepository issueRepository;

    @Override
    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public Issue updateIssue(Long issueId, Issue updatedIssue) {
        Issue existingIssue;
        existingIssue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found for ID: " + issueId));

        existingIssue.setTitle(updatedIssue.getTitle());
        existingIssue.setDescription(updatedIssue.getDescription());
        existingIssue.setPriority(updatedIssue.getPriority());

        return issueRepository.save(existingIssue);
    }

    @Override
    public Issue getIssueById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found for ID: " + issueId));
    }

    @Override
    public Iterable<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }
}
