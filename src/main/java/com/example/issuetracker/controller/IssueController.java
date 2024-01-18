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

    // Service for handling Issue-related operations
    public IssueService issueService;

    @Autowired
    // Constructor-based dependency injection for IssueService
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    // Create a new Issue
    public ResponseEntity<?> createIssue(@Valid @RequestBody Issue issue) {
        try {
            // Attempt to create the issue
            Issue createdIssue = issueService.createIssue(issue);
            // Return a success response with the created issue
            return ResponseEntity.status(HttpStatus.OK).body(createdIssue);
        } catch (IllegalArgumentException e) {
            // Handle validation or business rule violation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions with an appropriate status code
            return ResponseEntity.status(getStatusCodeForException()).body("Error creating issue: " + e.getMessage());
        }
    }

    @PutMapping("/{issueId}")
    // Update an existing Issue by ID
    public ResponseEntity<String> updateIssue(@PathVariable Long issueId, @RequestBody Issue updatedIssue) {
        try {
            // Attempt to update the issue
            Issue updated = issueService.updateIssue(issueId, updatedIssue);
            // Return a success response with the updated issue
            return ResponseEntity.ok(String.valueOf(updated));
        } catch (IllegalArgumentException e) {
            // Handle not found errors
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions with an appropriate status code
            return ResponseEntity.status(getStatusCodeForException()).body("Error updating issue: " + e.getMessage());
        }
    }

    @GetMapping("/{issueId}")
    // Retrieve an Issue by ID
    public ResponseEntity<?> getIssueById(@PathVariable Long issueId) {
        try {
            // Attempt to retrieve the issue
            Issue issue = issueService.getIssueById(issueId);
            // Return a success response with the retrieved issue
            return ResponseEntity.ok(issue);
        } catch (IllegalArgumentException e) {
            // Handle validation or business rule violation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions with an appropriate status code
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving issue: " + e.getMessage());
        }
    }

    @GetMapping
    // Retrieve all Issues
    public ResponseEntity<Iterable<Issue>> getAllIssues() {
        // Retrieve all issues from the service
        Iterable<Issue> issues = issueService.getAllIssues();
        // Return a success response with the list of issues
        return ResponseEntity.ok(issues);
    }

    @DeleteMapping("/{issueId}")
    // Delete an Issue by ID
    public ResponseEntity<String> deleteIssue(@PathVariable Long issueId) {
        try {
            // Attempt to delete the issue
            issueService.deleteIssue(issueId);
            // Return a success response with a message
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Issue deleted successfully");
        } catch (IllegalArgumentException e) {
            // Handle not found or validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions with an appropriate status code
            return ResponseEntity.status(getStatusCodeForException()).body("Error deleting issue: " + e.getMessage());
        }
    }

    // Helper method to get the HTTP status code for internal server errors
    private int getStatusCodeForException() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
