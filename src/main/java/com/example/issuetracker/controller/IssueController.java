package com.example.issuetracker.controller;

import com.example.issuetracker.models.Issue;
import com.example.issuetracker.services.IssueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/issues", produces = "application/json")
public class IssueController {
    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }



    @PostMapping
    public ResponseEntity<?> createIssue(@Valid @RequestBody Issue issue) {
        try {
            Issue createdIssue = issueService.createIssue(issue);
            return ResponseEntity.status(HttpStatus.OK).body(createdIssue);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(getStatusCodeForException()).body("Error creating issue: " + e.getMessage());
        }
    }

    @PutMapping("/{issueId}")
    public ResponseEntity<String> updateIssue(@PathVariable Long issueId, @RequestBody Issue updatedIssue) {
        try {
            Issue updated = issueService.updateIssue(issueId, updatedIssue);
            return ResponseEntity.ok(String.valueOf(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(getStatusCodeForException()).body("Error updating issue: " + e.getMessage());
        }
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<?> getIssueById(@PathVariable Long issueId) {
        try {
            Issue issue = issueService.getIssueById(issueId);
            return ResponseEntity.ok(issue);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving issue: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Issue>> getAllIssues() {
        Iterable<Issue> issues = issueService.getAllIssues();
        return ResponseEntity.ok(issues);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long issueId) {
        try {
            issueService.deleteIssue(issueId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Issue deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(getStatusCodeForException()).body("Error deleting issue: " + e.getMessage());
        }
    }

    private int getStatusCodeForException() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}