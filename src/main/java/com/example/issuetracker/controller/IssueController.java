package com.example.issuetracker.controller;

import com.example.issuetracker.models.Issue;
import com.example.issuetracker.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value = "/issues", produces = "application/json")
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @PostMapping
    public Issue createIssue(@RequestBody Issue issue) {
        return issueService.createIssue(issue);
    }

    @PutMapping("/{issueId}")
    public Issue updateIssue(@PathVariable Long issueId, @RequestBody Issue updatedIssue) {
        return issueService.updateIssue(issueId, updatedIssue);
    }

    @GetMapping("/{issueId}")
    public Issue getIssueById(@PathVariable Long issueId) {
        return issueService.getIssueById(issueId);
    }

    @GetMapping
    public Iterable<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @DeleteMapping("/{issueId}")
    public void deleteIssue(@PathVariable Long issueId) {
        issueService.deleteIssue(issueId);
    }

}
