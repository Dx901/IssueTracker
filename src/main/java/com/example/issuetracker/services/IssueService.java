package com.example.issuetracker.services;

import com.example.issuetracker.models.Issue;

public interface IssueService {
    Issue createIssue(Issue issue);

    Issue updateIssue(Long issueId, Issue updatedIssue);

    Issue getIssueById(Long issueId);

    Iterable<Issue> getAllIssues();

    void deleteIssue(Long issueId);
}
